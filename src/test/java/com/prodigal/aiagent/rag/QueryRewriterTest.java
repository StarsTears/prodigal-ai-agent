package com.prodigal.aiagent.rag;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class QueryRewriterTest {
    @Resource
    private QueryRewriter queryRewriter;

    @Test
    void doQueryRewriter() {
        String rewriter = queryRewriter.doQueryRewriter("请帮我写一个hello world程序");
        log.info(rewriter);
    }
}