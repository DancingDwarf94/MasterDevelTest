package com.masterdevel.backendserver.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Credential {
    @Id
    @Getter
    @Setter
    private String key;
    @Getter
    @Setter
    private String sharedSecret;
}