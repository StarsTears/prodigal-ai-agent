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
 * @description spring ai 测试
 * @since 2025/4/30
 */
//@Component //取消注释，可在 Spring Boot 项目启动时执行
public class SpringAiInvoke implements CommandLineRunner {
    @Resource
    private ChatModel chatModel;
    @Override
    public void run(String... args) throws Exception {
        AssistantMessage output = chatModel.call(new Prompt("Hello World! Welcome to Prodigal AI Agent! I`m Lang"))
                .getResult().getOutput();
        System.out.println(output.getText());
    }
}
