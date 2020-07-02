package com.masterdevel.backendserver.Dtos;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

public class MessageDTO {
    @NotBlank(message = "The msg field is required")
    @Getter
    private String msg;
    @Getter
    private List<String> tags;
}