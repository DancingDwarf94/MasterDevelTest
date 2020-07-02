package com.masterdevel.backendserver.Utils;

import java.util.ArrayList;
import java.util.List;

import com.masterdevel.backendserver.Dtos.MessageUiDTO;
import com.masterdevel.backendserver.Models.Message;
import com.masterdevel.backendserver.Models.MessageTags;

import org.springframework.stereotype.Component;

@Component
public class MessageUtils {

    public MessageUiDTO parseToUiDTO(Message message){
        MessageUiDTO dto = new MessageUiDTO();
        dto.setId(message.getId());
        dto.setMsg(message.getMsg());
        List<String> tags = new ArrayList<String>();
        for (MessageTags messageTags : message.getMessageTags()) {
            tags.add(messageTags.getTag().getTag());
        }
        dto.setTags(tags);

        return dto;
    }

	public List<MessageUiDTO> parseToUiDTO(List<Message> messages) {
        List<MessageUiDTO> dtos = new ArrayList<MessageUiDTO>();
        for (Message message : messages) {
            dtos.add(parseToUiDTO(message));
        }
		return dtos;
	}
    
}