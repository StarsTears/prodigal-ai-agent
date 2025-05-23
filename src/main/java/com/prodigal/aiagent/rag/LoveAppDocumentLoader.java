package com.prodigal.aiagent.rag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.markdown.MarkdownDocumentReader;
import org.springframework.ai.reader.markdown.config.MarkdownDocumentReaderConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description 解析Markdownwen文件
 * @since 2025/5/9
 */
@Slf4j
@Component
public class LoveAppDocumentLoader {
    private final ResourcePatternResolver resourcePatternResolver;

    LoveAppDocumentLoader(ResourcePatternResolver resourcePatternResolver) {
        this.resourcePatternResolver = resourcePatternResolver;
    }

    List<Document> loadMarkdown() {
        List<Document> documentArrayList = new ArrayList<>();
        try {
            //解析 md文件
            Resource[] resources = resourcePatternResolver.getResources("classpath:/document/*.md");
            for (Resource resource : resources) {
                String fileName = resource.getFilename();
                //状态
                String status = fileName.substring(fileName.length() - 6, fileName.length() - 4);
                MarkdownDocumentReaderConfig config = MarkdownDocumentReaderConfig.builder()
                        .withHorizontalRuleCreateDocument(true)
                        .withIncludeCodeBlock(false)
                        .withIncludeBlockquote(false)
                        .withAdditionalMetadata("filename", fileName)
                        .withAdditionalMetadata("status",status)
                        .build();
                MarkdownDocumentReader reader = new MarkdownDocumentReader(resource, config);
                documentArrayList.addAll(reader.read());
            }
        } catch (IOException e) {
            log.error("markdown 加载失败：{}",e);
            throw new RuntimeException(e);
        }
        return documentArrayList;
    }
}
