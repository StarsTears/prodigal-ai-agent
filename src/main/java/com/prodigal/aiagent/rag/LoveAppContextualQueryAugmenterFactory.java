package com.prodigal.aiagent.rag;

import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.rag.generation.augmentation.ContextualQueryAugmenter;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description 自定义 错误处理机制工厂
 * @since 2025/5/23
 */
public class LoveAppContextualQueryAugmenterFactory {
    public static ContextualQueryAugmenter createInstance() {
        PromptTemplate promptTemplate = new PromptTemplate("""
                你应该输出下面的内容：
                您好！您的问题已超出我的知识范围，对此感到非常抱歉！！！
                有问题可以联系：Lang
                """);
        return ContextualQueryAugmenter.builder()
                .allowEmptyContext(false) //不启用 上下文为空的选项
                .emptyContextPromptTemplate(promptTemplate)
                .build();
    }
}
