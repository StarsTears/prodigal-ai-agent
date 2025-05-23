package com.prodigal.aiagent.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description 终端操作工具类
 * @since 2025/5/23
 */
public class TerminalOperationTool {
    @Tool(description = "Execute a command in the terminal")
    public String executeCommand(@ToolParam  (description = "Command to execute") String command) {
        StringBuilder output = new StringBuilder();
        // 执行命令并获取输出
        try {
            Process process = Runtime.getRuntime().exec(command);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }
            int exitCode = process.waitFor();
            if (exitCode != 0){
                output.append("execute command failed with exit code： ").append(exitCode);
            }
        } catch (InterruptedException|IOException e) {
            output.append("Error execute command： ").append(e.getMessage());
        }
        // 返回命令的输出结果
        return output.toString();
    }
    @Tool(description = "Execute a command in the terminal of Windows System")
    public String executeWindowsCommand(@ToolParam  (description = "Command to execute") String command) {
        StringBuilder output = new StringBuilder();
        // 执行命令并获取输出
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", command);
            Process process = builder.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }
            int exitCode = process.waitFor();
            if (exitCode != 0){
                output.append("execute command failed with exit code： ").append(exitCode);
            }
        } catch (InterruptedException|IOException e) {
            output.append("Error execute command： ").append(e.getMessage());
        }
        // 返回命令的输出结果
        return output.toString();
    }
}
