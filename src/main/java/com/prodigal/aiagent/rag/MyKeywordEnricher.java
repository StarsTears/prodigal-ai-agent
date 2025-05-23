package com.prodigal.aiagent.rag;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.KeywordMetadataEnricher;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description 基于AI 文档的元信息增强器（为文档补充元信息）
 * @since 2025/5/22
 */
@Component
public class MyKeywordEnricher {
    @Resource
    private ChatModel dashscopeChatModel;

    public List<Document> enrichDocument(List<Document> documents) {
        KeywordMetadataEnricher keywordMetadataEnricher = new KeywordMetadataEnricher(dashscopeChatModel,5);
        return keywordMetadataEnricher.apply(documents);
    }
}
