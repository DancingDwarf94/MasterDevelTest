package com.masterdevel.backendserver.Utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class SignatureUtils {
    public boolean isValid(String sSignature, String sharedSecret, String sData) throws Exception {
        String secret = sharedSecret;
        String message = sData.replaceAll("\\s+", "");

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
        return hash.equals(sSignature);
    }
}