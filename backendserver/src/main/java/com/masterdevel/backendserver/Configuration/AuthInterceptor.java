package com.masterdevel.backendserver.Configuration;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.masterdevel.backendserver.Models.Credential;
import com.masterdevel.backendserver.Repositories.CredentialRepository;
import com.masterdevel.backendserver.Utils.SignatureUtils;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    AuthConfigurations configurations;
    @Autowired
    SignatureUtils signatureUtils;
    @Autowired
    CredentialRepository credentialRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String uri = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
        if (configurations.isUnauthenticatedRoute(uri)) {
            return true;
        } else {
            String key = request.getHeader("x-Key");
            Optional<Credential> credential = credentialRepository.findById(key);
            if (credential.isPresent()) {
                final Map<String, String> pathVariables = (Map<String, String>) request
                        .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                String body = request.getHeader("x-Body");

                String data = buildData(pathVariables, body, request.getHeader("x-Route"));

                if (signatureUtils.isValid(request.getHeader("x-Signature"), credential.get().getSharedSecret(),
                        data)) {
                    return true;
                }
            }
        }

        response.sendError(HttpStatus.FORBIDDEN.value());
        return false;
    }

    private String buildData(Map<String, String> pathVariables, String body, String header) {
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, Object>>() {
        }.getType();

        String reply = "";
        Map<String, String> data = new HashMap<String, String>();
        Map<String, Object> mBody = gson.fromJson(body, mapType);

        data.putAll(pathVariables);

        if (mBody != null) {
            for (String key : mBody.keySet()) {
                if (mBody.get(key).getClass() != String.class) {
                    data.put(key, Arrays.toString(((ArrayList<String>) mBody.get(key)).toArray()));
                } else {
                    data.put(key, mBody.get(key).toString());
                }
            }
        }

        data.put("x-Route", header);

        for (String key : pathVariables.keySet()) {
            reply += key + ':' + data.get(key) + ';';
        }
        if (mBody != null) {
            for (String key : mBody.keySet()) {
                reply += key + ':' + data.get(key) + ';';
            }
        }
        reply += "x-Route" + ":" + header + ";";

        return reply;
    }

}
