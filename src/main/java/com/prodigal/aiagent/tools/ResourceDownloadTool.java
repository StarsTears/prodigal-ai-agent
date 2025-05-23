package com.prodigal.aiagent.tools;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import com.prodigal.aiagent.common.constant.FileConstant;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.io.File;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description 资源下载工具类
 * @since 2025/5/23
 */
public class ResourceDownloadTool {
    final String fileDir = FileConstant.SAVE_FILE_DIR+"/download";
    @Tool(description = "Download resource from a given URL")
    public String downloadResource(@ToolParam(description = "URL of the resource to download") String url,
                                   @ToolParam(description = "Name of the file to save the downloaded resource")String fileName) {
        String filePath = fileDir+"/"+fileName;
        try {
            FileUtil.mkdir(fileDir);//创建目录
            HttpUtil.downloadFile(url, new File(filePath));
            return "Resource was successfully downloaded to: " + filePath;
        }catch (Exception e){
            return "Resource download failed: " + e.getMessage();
        }
    }
}
