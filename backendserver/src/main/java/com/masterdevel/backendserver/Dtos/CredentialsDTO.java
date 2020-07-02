package com.masterdevel.backendserver.Dtos;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

public class CredentialsDTO {
    @NotBlank(message = "The key field is required")
    @Getter
    private String key;
    @NotBlank(message = "The shared_secret field is required")
    @Getter
    @JsonProperty("shared_secret")
    private String sharedSecret;
}