package com.masterdevel.backendserver.Repositories;

import java.util.List;

import com.masterdevel.backendserver.Models.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findByMessageTags_Tag_Tag(String tag);
    
}