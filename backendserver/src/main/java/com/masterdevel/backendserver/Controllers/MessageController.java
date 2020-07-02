package com.masterdevel.backendserver.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masterdevel.backendserver.Domain.AppResponse;
import com.masterdevel.backendserver.Dtos.MessageDTO;
import com.masterdevel.backendserver.Services.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping()
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping(value = "message")
    public ResponseEntity<AppResponse> addMessage(@RequestBody MessageDTO body) {
        return ResponseEntity.ok(new AppResponse("Message created", messageService.addMessage(body).getId()));
    }

    @GetMapping(value="message/{id}")
    public ResponseEntity<AppResponse> getMessageById(@PathVariable Long id) {
        return ResponseEntity.ok(new AppResponse("Message retreived", messageService.getMessageById(id)));
    }

    @GetMapping(value="messages/{tag}")
    public ResponseEntity<AppResponse> getMessagesByTag(@PathVariable String tag) {
        return ResponseEntity.ok(new AppResponse("Message retreived", messageService.getMessagesByTag(tag)));
    }
}