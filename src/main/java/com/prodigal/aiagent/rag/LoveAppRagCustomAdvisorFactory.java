package com.prodigal.aiagent.rag;

import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description 自定义 Rag 检索增强顾问的工厂
 * @since 2025/5/23
 */
public class LoveAppRagCustomAdvisorFactory {
    /**
     * 自定义 Rag 检索增强过滤器
     * @param vectorStore
     * @param status
     * @return
     */
    public static Advisor createLoveAppRagCustomAdvisor(VectorStore vectorStore, String status) {
        Filter.Expression expression = new FilterExpressionBuilder()
                .eq("status", status)
                .build();

        DocumentRetriever documentRetriever = VectorStoreDocumentRetriever.builder()
                .vectorStore(vectorStore)
                .filterExpression(expression) // 添加过滤条件
                .similarityThreshold(0.5) // 设置相似度阈值
                .topK(3) // 返回文档数量
                .build();
        return RetrievalAugmentationAdvisor.builder()
                .documentRetriever(documentRetriever)
                .queryAugmenter(LoveAppContextualQueryAugmenterFactory.createInstance())
                .build();
    }
}

