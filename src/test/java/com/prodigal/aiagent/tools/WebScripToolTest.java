package com.prodigal.aiagent.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebScripToolTest {

    @Test
    void scrapeWebPage() {
        WebScripTool webScripTool = new WebScripTool();
        String result = webScripTool.scrapeWebPage("https://www.baidu.com");
        assertNotNull(result);
    }
}