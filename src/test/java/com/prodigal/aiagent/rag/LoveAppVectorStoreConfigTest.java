package com.prodigal.aiagent.rag;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoveAppVectorStoreConfigTest {
    @Resource
    private LoveAppVectorStoreConfig loveAppVectorStoreConfig;
    @Resource
    private EmbeddingModel dashscopeEmbeddingModel;

    @Test
    void loveAppVectorStore() {
        VectorStore vectorStore = loveAppVectorStoreConfig.loveAppVectorStore(dashscopeEmbeddingModel);
    }
}