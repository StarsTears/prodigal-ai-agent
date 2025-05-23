package com.prodigal.aiagent.rag;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description 切分文档
 * @since 2025/5/22
 */
@Component
public class MyTokenTextSplitter {
   public List<Document> splitDocument(List<Document> documents){
       TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
       return tokenTextSplitter.apply(documents);
   }
    public List<Document> splitCustomized(List<Document> documents){
        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter(1000,200,10,5000,true);
        return tokenTextSplitter.apply(documents);
    }
}
