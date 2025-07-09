package org.example.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.example.listener.MyListener;
import org.example.service.ChatAssistant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class LLMConfig {

    @Bean("chatModelQw")
    public ChatModel chatModelQwen() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("al-qw-api"))
                .modelName("qwen-vl-max").
                baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .logRequests(true).logResponses(true).listeners(Arrays.asList(new MyListener()))
                .maxRetries(1).maxTokens(8192).maxCompletionTokens(Integer.MAX_VALUE).timeout(Duration.ofSeconds(300))
//                .timeout(Duration.ofMillis(100L))
                .build();

    }

    @Bean("chatModelDs")
    public ChatModel chatModelDs() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("ds-api-v"))
                .modelName("deepseek-chat").
                baseUrl("https://api.deepseek.com")
                .build();

    }

    @Bean("highQw")
    public ChatAssistant chatAssistant(@Qualifier("chatModelQw") ChatModel chatModel){
        ChatAssistant chatAssistant = AiServices.create(ChatAssistant.class, chatModel);
        return chatAssistant;
    }
}
