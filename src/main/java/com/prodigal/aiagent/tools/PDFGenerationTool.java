package com.prodigal.aiagent.tools;

import cn.hutool.core.io.FileUtil;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.prodigal.aiagent.common.constant.FileConstant;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

/**
 * @author Lang
 * @project prodigal-ai-agent
 * @Version: 1.0
 * @description PDF生成工具类
 * @since 2025/5/23
 */
public class PDFGenerationTool {
    final String fileDir = FileConstant.SAVE_FILE_DIR + "/pdf";

    @Tool(description = "生成PDF文件")
    public String generatePDF(@ToolParam(description = "PDF文件内容") String content,
                              @ToolParam(description = "PDF文件名") String fileName) {
        String filePath = fileDir + "/" + fileName + ".pdf";
        try {
            FileUtil.mkdir(fileDir);//创建目录
            try (
                    //创建pdfwriter 和 pdfDocument 对象
                    PdfWriter writer = new PdfWriter(filePath);
                    PdfDocument pdfDocument = new PdfDocument(writer);
                    Document document = new Document(pdfDocument)) {
                //使用内置中文字体
                PdfFont font = PdfFontFactory.createFont("STSongStd-Light","UniGB-UCS2-H");

                //自定义字体（需下载字体到本地
//              String pdfFont = Paths.get("src/main/resources/fonts/STSongStd-Light.ttf").toAbsolutePath().toString();
//              PdfFont font = PdfFontFactory.createFont(pdfFont, PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);
                document.setFont(font);
                //创建段落
                Paragraph paragraph = new Paragraph(content).setTextAlignment(TextAlignment.CENTER);;
                //将段落添加到文档中
                document.add(paragraph);
                document.close();
            }
            return "PDF generate to " + filePath;
        } catch (Exception e) {
            return "PDF generate failed：" + e.getMessage();
        }
    }
}
