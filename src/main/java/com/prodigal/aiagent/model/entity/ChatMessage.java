package com.prodigal.aiagent.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName chat_message
 */
@TableName(value ="chat_message")
@Data
public class ChatMessage implements Serializable {
    private Long id;

    @TableField("conversation_id")
    private String conversationId;

    private String role;

    private String content;

    @TableField("create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

//    @Override
//    public boolean equals(Object that) {
//        if (this == that) {
//            return true;
//        }
//        if (that == null) {
//            return false;
//        }
//        if (getClass() != that.getClass()) {
//            return false;
//        }
//        ChatMessage other = (ChatMessage) that;
//        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
//            && (this.getConversationId() == null ? other.getConversationId() == null : this.getConversationId().equals(other.getConversationId()))
//            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()))
//            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
//            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
//        result = prime * result + ((getConversationId() == null) ? 0 : getConversationId().hashCode());
//        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
//        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
//        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", id=").append(id);
//        sb.append(", conversationId=").append(conversationId);
//        sb.append(", role=").append(role);
//        sb.append(", content=").append(content);
//        sb.append(", createTime=").append(createTime);
//        sb.append(", serialVersionUID=").append(serialVersionUID);
//        sb.append("]");
//        return sb.toString();
//    }
}