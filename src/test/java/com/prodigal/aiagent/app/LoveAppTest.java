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
        String content = loveApp.doChat("你好！我是 Itlang", chatId);
        Assertions.assertNotNull(content);
        //第二轮
        content = loveApp.doChat("我想让我的另一半（小孩儿）更爱我，该如何做？", chatId);
        Assertions.assertNotNull(content);
        //第三轮
        content = loveApp.doChat("我是谁？我的另一半分别叫什么来着?刚和你说过，帮我回忆一下", chatId);
        Assertions.assertNotNull(content);
    }

    @Test
    void doChatWithReport() {
        String chatId = UUID.fastUUID().toString();
        //第一轮
        LoveApp.LoveReport loveReport = loveApp.doChatWithReport("你好！我是lang！我想让我的女朋友每天都开开心心的，但是不知道怎么做?", chatId);
        Assertions.assertNotNull(loveReport);
    }

    @Test
    void doWithRag() {
        String chatId = UUID.fastUUID().toString();
        String message = "怎样知道自己是否喜欢上一个人？";
        String answer = loveApp.doWithRag(message, chatId);
        Assertions.assertNotNull(answer);
    }
}