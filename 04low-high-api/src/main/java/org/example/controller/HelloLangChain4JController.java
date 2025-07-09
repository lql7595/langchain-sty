package org.example.controller;


import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.internal.chat.Message;
import dev.langchain4j.model.output.Response;
import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.webresources.FileResource;
import org.example.service.ChatAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping(value = "/langchain")
public class HelloLangChain4JController {

    @Value("classpath:images/mi.jpg")
    private org.springframework.core.io.Resource resource;//import org.springframework.core.io.Resource;
    @Resource(name = "chatModelQw")
    private ChatModel qwChat;
    @Resource(name = "chatModelDs")
    private ChatModel dsChat;

    @Resource(name = "highQw")
    private ChatAssistant chatAssistant;

    @GetMapping("/hello/qw")
    public String hello(@RequestParam(value = "req", defaultValue = "你是谁？") String request) {
        return qwChat.chat(request);
    }
    @GetMapping("/hello/ds")
    public String helloDs(@RequestParam(value = "req", defaultValue = "你是谁？") String request) {
        return dsChat.chat(request);
    }
    @GetMapping("/hello/high")
    public String highQw(@RequestParam(value = "req", defaultValue = "你是谁？") String request) throws IOException {

        byte[] contentAsByteArray = resource.getContentAsByteArray();
        String byteStr = Base64.getEncoder().encodeToString(contentAsByteArray);
        UserMessage from = UserMessage.from(TextContent.from(request), ImageContent.from(byteStr, "image/jpg"));
//        UserMessage from = UserMessage.from(TextContent.from(request));
        ChatResponse chat1 = qwChat.chat(from);
        return chat1.aiMessage().text();
    }
    @GetMapping("/hello/high/1")
    public String high1Qw(@RequestParam(value = "req", defaultValue = "你是谁？") String request) throws IOException {

        byte[] contentAsByteArray = resource.getContentAsByteArray();
        String byteStr = Base64.getEncoder().encodeToString(contentAsByteArray);
        AiMessage chat = chatAssistant.chat(TextContent.from(request), ImageContent.from(byteStr, "image/jpg"));
        return chat.text();
    }
}
