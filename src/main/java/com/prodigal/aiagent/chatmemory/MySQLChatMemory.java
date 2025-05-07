package com.prodigal.aiagent.chatmemory;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.prodigal.aiagent.model.entity.ChatMessage;
import com.prodigal.aiagent.service.ChatMessageService;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description 基于MySQL 持久化存储的会话记忆
 * @since 2025/5/7
 */
@Component
public class MySQLChatMemory implements ChatMemory {

    @Autowired
    private ChatMessageService chatMessageService;

    @Override
    public void add(String conversationId, List<Message> messages) {

        List<ChatMessage> messageList = messages.stream().map(msg -> {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setConversationId(conversationId);
            chatMessage.setContent(msg.getText());
            chatMessage.setRole(msg.getMessageType().getValue());
            return chatMessage;
        }).toList();
        boolean isOk = chatMessageService.saveBatch(messageList);

    }

    @Override
    public List<Message> get(String conversationId, int lastN) {
        if (lastN <= 0) {
            return List.of();
        }
        LambdaQueryWrapper<ChatMessage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatMessage::getConversationId, conversationId);
        List<ChatMessage> chatMessages = chatMessageService.list(queryWrapper);
        return chatMessages.stream()
                .skip(Math.max(0, chatMessages.size() - lastN))
                .map(cm ->{
                    String role = cm.getRole();
                    if(role.equals(MessageType.SYSTEM.getValue())){
                        return new SystemMessage(cm.getContent());
                    } else if (role.equals(MessageType.ASSISTANT.getValue())) {
                        return new AssistantMessage(cm.getContent());
                    }else {
                        return new UserMessage(cm.getContent());
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public void clear(String conversationId) {
        LambdaQueryWrapper<ChatMessage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatMessage::getConversationId, conversationId);
        List<ChatMessage> messageList = chatMessageService.list(queryWrapper);
        chatMessageService.removeBatchByIds(messageList.stream().map(ChatMessage::getConversationId).collect(Collectors.toList()));
    }
}
