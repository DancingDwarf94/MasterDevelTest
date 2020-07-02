package com.masterdevel.backendserver.Models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Tag {
    @Id
    @Getter
    @Setter
    private String tag;
    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<MessageTags> messageTags;
}