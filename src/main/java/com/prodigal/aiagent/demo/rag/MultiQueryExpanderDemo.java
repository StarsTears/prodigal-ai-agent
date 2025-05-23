package com.prodigal.aiagent.demo.rag;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.preretrieval.query.expansion.MultiQueryExpander;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description 查询扩展器
 * @since 2025/5/22
 */
@Component
public class MultiQueryExpanderDemo {
    private final ChatClient.Builder chatClientBuilder;
    MultiQueryExpanderDemo(ChatModel dashscopeChatModel){
        this.chatClientBuilder = ChatClient.builder(dashscopeChatModel);
    }
    public List<Query> expand(String query){
        MultiQueryExpander multiQueryExpander = MultiQueryExpander.builder()
                .chatClientBuilder(chatClientBuilder)
                .numberOfQueries(3)
                .build();
        List<Query> queries = multiQueryExpander.expand(new Query(query));
        return queries;
    }
}
