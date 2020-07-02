package com.masterdevel.backendserver.Repositories;

import com.masterdevel.backendserver.Models.Tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    
}