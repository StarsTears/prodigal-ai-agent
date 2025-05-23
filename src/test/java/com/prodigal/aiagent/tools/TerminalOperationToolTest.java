package com.prodigal.aiagent.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TerminalOperationToolTest {

    @Test
    void executeCommand() {
    }

    @Test
    void executeWindowsCommand() {
        TerminalOperationTool tool = new TerminalOperationTool();
        String result = tool.executeWindowsCommand("dir /b");
        assertNotNull(result);
        System.out.println(result);
    }
}