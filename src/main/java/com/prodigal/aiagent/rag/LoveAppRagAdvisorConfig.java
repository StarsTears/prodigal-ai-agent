package com.prodigal.aiagent.rag;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetriever;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetrieverOptions;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description 基于阿里云知识库的 RAG 增强检索顾问
 * @since 2025/5/9
 */
@Configuration
public class LoveAppRagAdvisorConfig {
    @Value("${spring.ai.dashscope.api-key}")
    private String dashscopeApiKey;

    @Bean
    public Advisor loveAppRagAdvisor(){
        DashScopeApi dashScopeApi = new DashScopeApi(dashscopeApiKey);
        DocumentRetriever retriever = new DashScopeDocumentRetriever(dashScopeApi,
                                            DashScopeDocumentRetrieverOptions
                                                    .builder()
                                                    .withIndexName("恋爱大师")//知识库的名称
                                                    .withDenseSimilarityTopK(3)
                                                    .build());
        return RetrievalAugmentationAdvisor.builder()
                                            .documentRetriever(retriever)
                                            .build();
    }
}
