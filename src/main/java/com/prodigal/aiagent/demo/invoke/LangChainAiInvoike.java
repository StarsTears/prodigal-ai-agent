package com.prodigal.aiagent.demo.invoke;

import dev.langchain4j.community.model.dashscope.QwenChatModel;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description LangChain4j 测试
 * @since 2025/4/30
 */
public class LangChainAiInvoike {
    public static void main(String[] args) {
        QwenChatModel qwenChatModel = QwenChatModel.builder().apiKey(ApiKey.API_KEY).modelName("qwen-max").build();
        String chat = qwenChatModel.chat("Hello World! Welcome to Prodigal AI Agent! I`m Lang!!!");
        System.out.println(chat);
    }
}
