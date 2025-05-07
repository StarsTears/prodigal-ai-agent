package com.prodigal.aiagent.demo.invoke.spring;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description ollama大模型测试
 * @since 2025/5/7
 */
//@Component //取消注释，可在 Spring Boot 项目启动时执行
public class OllamaAiInvoke implements CommandLineRunner {
    @Resource
    private ChatModel ollamaChatModel;

    @Override
    public void run(String... args) throws Exception {
        AssistantMessage output = ollamaChatModel.call(new Prompt("hello! 我是lang,nice to meet you!"))
                .getResult().getOutput();
        System.out.println(output.getText());
    }
}
