package com.masterdevel.backendserver.Utils;

import java.util.Optional;

import com.masterdevel.backendserver.Models.Tag;
import com.masterdevel.backendserver.Repositories.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagUtils {

    @Autowired
    TagRepository tagRepository;

    public Tag GetTag(String tag){
        Optional<Tag> existingTag = tagRepository.findById(tag);
        if (existingTag.isPresent()) {
            return existingTag.get();
        }

        Tag newTag = new Tag();
        newTag.setTag(tag);
        return tagRepository.save(newTag);
    }
    
}