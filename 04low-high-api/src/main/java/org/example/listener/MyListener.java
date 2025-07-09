package org.example.listener;

import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
public class MyListener implements ChatModelListener {
    @Override
    public void onRequest(ChatModelRequestContext requestContext) {
        ChatModelListener.super.onRequest(requestContext);
        log.info("接收到请求了：" + requestContext.toString());
    }

    @Override
    public void onResponse(ChatModelResponseContext responseContext) {
        ChatModelListener.super.onResponse(responseContext);

        log.info("收到响应了：" + responseContext.toString());
    }

    @Override
    public void onError(ChatModelErrorContext errorContext) {
        ChatModelListener.super.onError(errorContext);
        log.info("异常了：" + errorContext.toString());

    }
}
