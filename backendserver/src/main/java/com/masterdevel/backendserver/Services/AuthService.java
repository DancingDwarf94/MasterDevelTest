package com.masterdevel.backendserver.Services;

import java.util.Optional;

import com.masterdevel.backendserver.Dtos.CredentialsDTO;
import com.masterdevel.backendserver.Exceptions.DuplicatedRecordException;
import com.masterdevel.backendserver.Models.Credential;
import com.masterdevel.backendserver.Repositories.CredentialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    CredentialRepository credentialRepository;

    public Credential addCredentials(CredentialsDTO dto) throws DuplicatedRecordException {
        Optional<Credential> existingCredential = credentialRepository.findById(dto.getKey());
        if (existingCredential.isEmpty()) {
            Credential credential = new Credential();
            credential.setKey(dto.getKey());
            credential.setSharedSecret(dto.getSharedSecret());

            return credentialRepository.save(credential);
        }

        throw new DuplicatedRecordException();
    }

}