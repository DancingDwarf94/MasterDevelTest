package com.masterdevel.backendserver.Dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class MessageUiDTO {
    @Getter
    @Setter
    private long id;
    @Getter
    @Setter
    private String msg;
    @Getter
    @Setter
    private List<String> tags;

    
}