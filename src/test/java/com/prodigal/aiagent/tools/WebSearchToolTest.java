package com.prodigal.aiagent.tools;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WebSearchToolTest {
    @Value("${search-api.api-key}")
    private String apiKey;

    @Test
    void searchWeb() {
        WebSearchTool webSearchTool = new WebSearchTool(apiKey);
        String result = webSearchTool.searchWeb("好看的动漫有哪些？");
        assertNotNull(result);
        //{"query":"十大好看的爱情动漫","link":"https://www.baidu.com/s?wd=%E5%8D%81%E5%A4%A7%E5%A5%BD%E7%9C%8B%E7%9A%84%E7%88%B1%E6%83%85%E5%8A%A8%E6%BC%AB&usm=3&ie=utf-8&rsv_pq=f1c51e43008bb2bb&oq=%E5%A5%BD%E7%9C%8B%E7%9A%84%E5%8A%A8%E6%BC%AB%E6%9C%89%E5%93%AA%E4%BA%9B%3F&rsv_t=774enoMy%2B3V7rBiE1M4Xpw80pnNaC7JFHga%2BttFckvdX6qZVhT8K%2BruQD5A&rsf=101631101&rsv_dl=0_prs_28608_1"},{"query":"好看的动漫.","link":"https://www.baidu.com/s?wd=%E5%A5%BD%E7%9C%8B%E7%9A%84%E5%8A%A8%E6%BC%AB.&usm=3&ie=utf-8&rsv_pq=f1c51e43008bb2bb&oq=%E5%A5%BD%E7%9C%8B%E7%9A%84%E5%8A%A8%E6%BC%AB%E6%9C%89%E5%93%AA%E4%BA%9B%3F&rsv_t=c237j3Sa1r6hmKdWkw%2Fn9PeZhDp2A%2FGSlwwb69lhnoHyZvav%2F185BAuegu0&rsf=101631161&rsv_dl=0_prs_28608_2"},{"query":"世界上最火的动漫","link":"https://www.baidu.com/s?wd=%E4%B8%96%E7%95%8C%E4%B8%8A%E6%9C%80%E7%81%AB%E7%9A%84%E5%8A%A8%E6%BC%AB&usm=3&ie=utf-8&rsv_pq=f1c51e43008bb2bb&oq=%E5%A5%BD%E7%9C%8B%E7%9A%84%E5%8A%A8%E6%BC%AB%E6%9C%89%E5%93%AA%E4%BA%9B%3F&rsv_t=c237j3Sa1r6hmKdWkw%2Fn9PeZhDp2A%2FGSlwwb69lhnoHyZvav%2F185BAuegu0&rsf=101631161&rsv_dl=0_prs_28608_3"},{"query":"全网最火的动漫","link":"https://www.baidu.com/s?wd=%E5%85%A8%E7%BD%91%E6%9C%80%E7%81%AB%E7%9A%84%E5%8A%A8%E6%BC%AB&usm=3&ie=utf-8&rsv_pq=f1c51e43008bb2bb&oq=%E5%A5%BD%E7%9C%8B%E7%9A%84%E5%8A%A8%E6%BC%AB%E6%9C%89%E5%93%AA%E4%BA%9B%3F&rsv_t=c237j3Sa1r6hmKdWkw%2Fn9PeZhDp2A%2FGSlwwb69lhnoHyZvav%2F185BAuegu0&rsf=101631161&rsv_dl=0_prs_28608_4"},{"query":"推荐的好看动漫","link":"https://www.baidu.com/s?wd=%E6%8E%A8%E8%8D%90%E7%9A%84%E5%A5%BD%E7%9C%8B%E5%8A%A8%E6%BC%AB&usm=3&ie=utf-8&rsv_pq=f1c51e43008bb2bb&oq=%E5%A5%BD%E7%9C%8B%E7%9A%84%E5%8A%A8%E6%BC%AB%E6%9C%89%E5%93%AA%E4%BA%9B%3F&rsv_t=c237j3Sa1r6hmKdWkw%2Fn9PeZhDp2A%2FGSlwwb69lhnoHyZvav%2F185BAuegu0&rsf=101631161&rsv_dl=0_prs_28608_5"}
    }
}