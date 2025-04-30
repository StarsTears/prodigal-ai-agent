package com.prodigal.aiagent.demo.invoke.qwen;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.prodigal.aiagent.demo.invoke.ApiKey;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description 通义千问Api测试
 * @since 2025/4/30
 */
public class HttpAiInvoke {
    // API 地址
    private static final String API_URL =
            "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";

    public static void main(String[] args) {
        // 构造 messages 数组
        JSONObject systemMsg = new JSONObject()
                .put("role", "system")
                .put("content", "You are a helpful assistant.");
        JSONObject userMsg = new JSONObject()
                .put("role", "user")
                .put("content", "你是谁？");
        JSONArray messages = new JSONArray()
                .put(systemMsg)
                .put(userMsg);

        // 构造 input 和 parameters
        JSONObject input = new JSONObject().put("messages", messages);
        JSONObject parameters = new JSONObject().put("result_format", "message");

        // 最终的请求体
        JSONObject requestBody = new JSONObject()
                .put("model", "qwen-plus")
                .put("input", input)
                .put("parameters", parameters);

        // 发送 HTTP POST 请求
        HttpResponse response = HttpRequest
                .post(API_URL)
                .header("Authorization", "Bearer " + ApiKey.API_KEY)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                // 根据需要可以设置超时时间
                .timeout(20_000)
                .execute();

        // 打印返回结果
        System.out.println(response.body());
    }
}
