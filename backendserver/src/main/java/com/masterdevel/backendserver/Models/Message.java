package com.masterdevel.backendserver.Models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Message {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Getter
    @Setter
    private String msg;
    @OneToMany(mappedBy = "message", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Getter
    @Setter
    private List<MessageTags> messageTags;
}