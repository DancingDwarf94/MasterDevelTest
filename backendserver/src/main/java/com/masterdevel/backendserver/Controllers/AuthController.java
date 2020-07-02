package com.masterdevel.backendserver.Controllers;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import com.masterdevel.backendserver.Domain.AppResponse;
import com.masterdevel.backendserver.Dtos.CredentialsDTO;
import com.masterdevel.backendserver.Exceptions.DuplicatedRecordException;
import com.masterdevel.backendserver.Services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("credential")
public class AuthController {

    @Autowired
    AuthService authService;

    @PutMapping()
    public ResponseEntity<AppResponse> addAuthKey(@RequestBody @Valid CredentialsDTO body) {
        try {
            authService.addCredentials(body);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (DuplicatedRecordException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}