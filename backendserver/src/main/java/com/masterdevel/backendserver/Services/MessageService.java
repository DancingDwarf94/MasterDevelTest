package com.masterdevel.backendserver.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.masterdevel.backendserver.Dtos.MessageDTO;
import com.masterdevel.backendserver.Dtos.MessageUiDTO;
import com.masterdevel.backendserver.Models.Message;
import com.masterdevel.backendserver.Models.MessageTags;
import com.masterdevel.backendserver.Models.Tag;
import com.masterdevel.backendserver.Repositories.MessageRepository;
import com.masterdevel.backendserver.Utils.MessageUtils;
import com.masterdevel.backendserver.Utils.TagUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    TagUtils tagUtils;
    @Autowired
    MessageUtils messageUtils;

	public Message addMessage(MessageDTO dto) {
        Message message = new Message();
        List<MessageTags> messageTags = new ArrayList<MessageTags>();
        message.setMsg(dto.getMsg());
        for (String sTag : dto.getTags()) {
            Tag tag = tagUtils.GetTag(sTag);
            MessageTags messageTag = new MessageTags();
            messageTag.setMessage(message);
            messageTag.setTag(tag);
            messageTags.add(messageTag);
        }
        message.setMessageTags(messageTags);

        return messageRepository.save(message);
	}

	public MessageUiDTO getMessageById(Long id) {
        Optional<Message> existingMessage = messageRepository.findById(id);
        if (existingMessage.isPresent()) {
            return messageUtils.parseToUiDTO(existingMessage.get());
        }
		return null;
	}

	public List<MessageUiDTO> getMessagesByTag(String tag) {
        List<Message> messages = messageRepository.findByMessageTags_Tag_Tag(tag);

		return messageUtils.parseToUiDTO(messages);
	}
    
}