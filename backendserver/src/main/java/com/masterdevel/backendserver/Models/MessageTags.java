package com.masterdevel.backendserver.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
public class MessageTags {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "message")
    @Getter
    @Setter
    private Message message;
    @ManyToOne
    @JoinColumn(name = "tag")
    @Getter
    @Setter
    private Tag tag;
}