package com.prodigal.aiagent.mapper;

import com.prodigal.aiagent.model.entity.ChatMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 35104
* @description 针对表【chat_message(会话信息表)】的数据库操作Mapper
* @createDate 2025-05-07 15:36:08
* @Entity com.prodigal.aiagent.model.entity.ChatMessage
*/
@Mapper
public interface ChatMessageMapper extends BaseMapper<ChatMessage> {
}




