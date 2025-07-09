package org.example.service;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.Content;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.output.Response;

import java.util.List;

public interface ChatAssistant {
    String chat(String prompt);
    AiMessage chat(Content... content);
    AiMessage chat(List<Content> content);
}
