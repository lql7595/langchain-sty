package org.example.service;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.Content;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.output.Response;

import java.util.List;

public interface ChatAssistant {

    String chat(String prompt);

    /**
     * 方法本质是将传递的参数 tojsonString 后再传递给模型，而不是当做content传入过去的，因此在解析非string的msg时，是和chatmodel有区别的。
     * chatmodel 的 msg结构体
     *   "messages" : [ {
     *     "role" : "user",
     *     "content" : [ {
     *       "type" : "text",
     *       "text" : "从下面图片种获取来源网站名称，股价走势和5月30号股价"
     *     }, {
     *       "type" : "image_url",
     *       "image_url" : {
     *         "url" : "data:image/jpg;base64,",
     *         "detail" : "low"
     *       }
     *     } ]
     *   } ]
     * -------------------
     *   代理方式的 msg结构体
     *     "messages" : [ {
     *     "role" : "user",
     *     "content" : "[TextContent { text = \"从下面图片种获取来源网站名称，股价走势和5月30号股价\" }, ImageContent { image = Image { url = null, base64Data = \"\", mimeType = \"image/jpg\", revisedPrompt = null } detailLevel = LOW }]"
     *   } ]
     *
     *   综上可以看出content中的内容形式不一致导致返回结果不同。
     *   总结：多模态下或许无法通过文本的值的形式，将图片的base64值放入其中，而获取图片内容。
     * @param content
     * @return
     */
    AiMessage chat(Content... content);
    AiMessage chat(List<Content> content);
}
