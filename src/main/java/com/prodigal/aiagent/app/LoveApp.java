package com.prodigal.aiagent.app;

import com.alibaba.nacos.api.naming.pojo.healthcheck.impl.Mysql;
import com.prodigal.aiagent.advisor.MyLoggerAdvisor;
import com.prodigal.aiagent.advisor.ReReadingAdvisor;
import com.prodigal.aiagent.chatmemory.FileBasedChatMemory;
import com.prodigal.aiagent.chatmemory.MySQLChatMemory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description 恋爱大师项目
 * @since 2025/4/30
 */
@Slf4j
@Component
public class LoveApp {
    private final ChatClient chatClient;
    //系统角色 提示词
    private final String SYSTEM_PROMPT = "你是一位资深'恋爱大师'，拥有多年情感咨询经验，擅长帮助恋人化解矛盾、增进感情、提升沟通和相处质量。你的风格：温暖、共情、专业，能给出可操作的实用建议";
    private MySQLChatMemory chatMemory; //基于数据库持久化记忆
    /**
     * 初始化 对话 客户端
     * @param dashscopeChatModel
     */
//    public LoveApp(ChatModel dashscopeChatModel) {
//        //初始化基于内存的对话记忆
////        InMemoryChatMemory chatMemory = new InMemoryChatMemory();
//        //基于文件存储持久化的会话记忆
////        String dir = System.getProperty("user.dir") + "/temp/chat-memory";
////        FileBasedChatMemory chatMemory = new FileBasedChatMemory(dir);
//
//        chatClient = ChatClient.builder(dashscopeChatModel)
//                .defaultSystem(SYSTEM_PROMPT)
//                .defaultAdvisors(
//                        new MessageChatMemoryAdvisor(chatMemory),
////                        new SimpleLoggerAdvisor(),
//                        //自定义日志 可按需开启
//                        new MyLoggerAdvisor()
//                        //自定义 Re2 可按需开启
////                        new ReReadingAdvisor()
//                ) //对话记忆 advisor
//                .build();
//    }

    /**
     * 基于Mysql 数据库 持久化会话记忆
     * @param dashscopeChatModel
     * @param chatMemory
     */
    public LoveApp(ChatModel dashscopeChatModel,MySQLChatMemory chatMemory) {
        this.chatMemory = chatMemory;
        chatClient = ChatClient.builder(dashscopeChatModel)
                .defaultSystem(SYSTEM_PROMPT)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),
//                        new SimpleLoggerAdvisor(),
                        //自定义日志 可按需开启
                        new MyLoggerAdvisor()
                        //自定义 Re2 可按需开启
//                        new ReReadingAdvisor()
                ) //对话记忆 advisor
                .build();
    }

    /**
     * AI 基础对话，支持多轮对话
     * @param message
     * @param chatId
     * @return
     */
    public String doChat(String message,String chatId) {
        ChatResponse chatResponse = chatClient.prompt()
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId) //对话id
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10) //关联会话的条数
                )
                .call()
                .chatResponse();
        String content = chatResponse.getResult().getOutput().getText();
        log.info("content:{}",content);
        return content;
    }

    record LoveReport(String title, List<String> suggestions) {
    }

    /**
     * AI 恋爱报告功能（结构化输出）
     * @param message
     * @param chatId
     * @return
     */
    public LoveReport doChatWithReport(String message,String chatId) {
        LoveReport loveReport = chatClient.prompt()
                .system(SYSTEM_PROMPT + "每次对话后都要生成恋爱结果，标题为 {用户名} 的恋爱报告")
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId) //对话id
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10) //关联会话的条数
                )
                .call()
                .entity(LoveReport.class);
        log.info("loveReport:{}",loveReport);
        return loveReport;
    }

    public void clearChatMemory(String chatId) {
        chatMemory.clear(chatId);
    }
}
