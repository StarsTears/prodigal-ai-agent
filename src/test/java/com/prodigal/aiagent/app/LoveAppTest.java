package com.prodigal.aiagent.app;

import cn.hutool.core.lang.UUID;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoveAppTest {

    @Resource
    private LoveApp loveApp;

    @Test
    void testChat() {
        String chatId = UUID.fastUUID().toString();
        //第一轮
        String content = loveApp.doChat("你好！我是lang", chatId);
        Assertions.assertNotNull(content);
        //第二轮
        content = loveApp.doChat("我想让我另一半更爱我，该如何做？", chatId);
        Assertions.assertNotNull(content);
        //第三轮
        content = loveApp.doChat("请问我叫什么名字?", chatId);
        Assertions.assertNotNull(content);
    }

    @Test
    void doChatWithReport() {
        String chatId = UUID.fastUUID().toString();
        //第一轮
        LoveApp.LoveReport loveReport = loveApp.doChatWithReport("你好！我是lang！我想让我的女朋友每天都开开心心的，但是不知道怎么做?", chatId);
        Assertions.assertNotNull(loveReport);
    }
}