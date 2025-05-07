-- 建库
CREATE DATABASE prodigal_ai_agent  CHARACTER SET utf8mb4;
-- 建表 用于存储 会话信息
CREATE TABLE chat_message (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT comment '主键id',
                              conversation_id VARCHAR(64) NOT NULL comment '会话id',
                              role VARCHAR(32) NOT NULL comment '消息类型，角色：user,system,',
                              content TEXT NOT NULL comment '会话内容',
                              create_time DATETIME DEFAULT CURRENT_TIMESTAMP
) comment '会话信息表';