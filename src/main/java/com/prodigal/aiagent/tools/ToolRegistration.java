package com.prodigal.aiagent.tools;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbacks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description 注册工具类 便于 Spring AI调用
 * @since 2025/5/23
 */
@Configuration
public class ToolRegistration {
    @Value("${search-api.api-key}")
    private String searchApiKey;
    @Bean
    public ToolCallback[] allTools(){
        FileOperationTool fileOperationTool = new FileOperationTool();
        PDFGenerationTool pdfGenerationTool = new PDFGenerationTool();
        TerminalOperationTool terminalOperationTool = new TerminalOperationTool();
        WebSearchTool webSearchTool = new WebSearchTool(searchApiKey);
        WebScripTool webScripTool = new WebScripTool();
        ResourceDownloadTool resourceDownloadTool = new ResourceDownloadTool();
        return ToolCallbacks.from(fileOperationTool, pdfGenerationTool, terminalOperationTool, webSearchTool, webScripTool, resourceDownloadTool);
    }
}
