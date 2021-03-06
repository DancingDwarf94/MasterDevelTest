package com.masterdevel.backendserver.Repositories;

import com.masterdevel.backendserver.Models.Credential;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, String> {
    
}