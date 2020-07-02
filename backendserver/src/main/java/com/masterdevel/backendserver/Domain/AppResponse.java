package com.masterdevel.backendserver.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class AppResponse {

    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private Object dataObject;
    
}