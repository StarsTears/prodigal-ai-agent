package com.prodigal.aiagent.rag;

import jakarta.annotation.Resource;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description 初始化向量数据库并保存文档
 * @since 2025/5/9
 */
@Configuration
public class LoveAppVectorStoreConfig {
    @Resource
    private LoveAppDocumentLoader loveAppDocumentLoader;
    @Resource
    private MyTokenTextSplitter myTokenTextSplitter;
    @Resource
    private MyKeywordEnricher myKeywordEnricher;
    @Bean
    VectorStore loveAppVectorStore(EmbeddingModel dashscopeEmbeddingModel) {
        SimpleVectorStore simpleVectorStore = SimpleVectorStore
                                                .builder(dashscopeEmbeddingModel)//大语言模型
                                                .build();
        //加载文档
        List<Document> documents = loveAppDocumentLoader.loadMarkdown();
        //自主切分文档
        List<Document> splitDocuments = myTokenTextSplitter.splitCustomized(documents);
        //自动补充关键词元信息
        List<Document> enrichDocuments = myKeywordEnricher.enrichDocument(documents);
        simpleVectorStore.add(enrichDocuments);
        return simpleVectorStore;
    }
}
