package com.prodigal.aiagent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.prodigal.aiagent.model.entity.ChatMessage;
import com.prodigal.aiagent.service.ChatMessageService;
import com.prodigal.aiagent.mapper.ChatMessageMapper;
import org.springframework.stereotype.Service;

/**
* @author 35104
* @description 针对表【chat_message(会话信息表)】的数据库操作Service实现
* @createDate 2025-05-07 15:36:08
*/
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage> implements ChatMessageService{

}




