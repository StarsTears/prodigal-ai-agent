package com.prodigal.aiagent.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.preretrieval.query.transformation.QueryTransformer;
import org.springframework.ai.rag.preretrieval.query.transformation.RewriteQueryTransformer;
import org.springframework.stereotype.Component;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description 查询重写器
 * @since 2025/5/22
 */
@Component
public class QueryRewriter {
    private QueryTransformer queryTransformer;
    public QueryRewriter(ChatModel dashscopeChatModel){
        ChatClient.Builder chatClientBuilder = ChatClient.builder(dashscopeChatModel);
        // 创建查询重写转换器
        queryTransformer = RewriteQueryTransformer.builder()
                .chatClientBuilder(chatClientBuilder)
                .build();
    }

    public String doQueryRewriter(String prompt){
        // 创建一个模拟用户学习AI的查询场景
        Query query = new Query(prompt);
        // 执行查询重写
        Query transformedQuery = queryTransformer.transform(query);
        // 输出重写后的查询
        System.out.println(transformedQuery.text());
        return transformedQuery.text();
    }
}
