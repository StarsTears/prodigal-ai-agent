package com.prodigal.aiagent.app;

import cn.hutool.core.lang.UUID;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class LoveAppTest {

    @Resource
    private LoveApp loveApp;

    @Test
    void testChat() {
        String chatId = UUID.fastUUID().toString();
        //第一轮
        String content = loveApp.doChat("你好！我是 Itlang", chatId);
        assertNotNull(content);
        //第二轮
        content = loveApp.doChat("我想让我的另一半（小孩儿）更爱我，该如何做？", chatId);
        assertNotNull(content);
        //第三轮
        content = loveApp.doChat("我是谁？我的另一半分别叫什么来着?刚和你说过，帮我回忆一下", chatId);
        assertNotNull(content);
    }

    @Test
    void doChatWithReport() {
        String chatId = UUID.fastUUID().toString();
        //第一轮
        LoveApp.LoveReport loveReport = loveApp.doChatWithReport("你好！我是lang！我想让我的女朋友每天都开开心心的，但是不知道怎么做?", chatId);
        assertNotNull(loveReport);
    }

    @Test
    void doWithRag() {
        String chatId = UUID.fastUUID().toString();
        String message = "怎样知道自己是否喜欢上一个人？";
        String answer = loveApp.doWithRag(message, chatId);
        assertNotNull(answer);
    }

    @Test
    void doWithTool() {
        //文件读写
       testMessageByTool("保存我的恋爱文档为文件");
       //测试PDF生成
        testMessageByTool("生成一份‘厦门-七夕约会计划’PDF，包含餐厅预定，活动流程及礼物清单");
        //测试资源下载
        testMessageByTool("直接下载一张关于动漫《仙逆》王林，李慕婉的图片为文件");
        //测试网页抓取
        testMessageByTool("请抓取百度一下，关于“仙逆”的网页，并返回结果");
        //测试联网搜索问题的答案
        testMessageByTool("周末想去厦门游玩，推荐适合情侣约会的小众打卡地");
        //测试终端操作
        testMessageByTool("在终端中，请打开浏览器，执行 java -verison 查看系统Java版本号");
    }

    private void testMessageByTool(String message){
        String chatId = UUID.fastUUID().toString();
        String answer = loveApp.doWithTool(message, chatId);
        assertNotNull(answer);
        log.info("doWithTool-{}:{}", message,answer);
    }
}