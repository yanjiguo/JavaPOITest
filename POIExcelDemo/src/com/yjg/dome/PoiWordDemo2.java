package com.yjg.dome;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import java.awt.image.ImagingOpException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

/**
 *  @author jiguo.yan@hand-china.com
 *  @date 2019/7/16
 *  @Description: POI操作Word
 */
public class PoiWordDemo2 {
    /**
     * word整体样式
     */
    private static CTStyles wordStyles = null;

    /**
     * Word整体样式
     */
    static {
        XWPFDocument template;
        try {
            // 读取模板文档
            template = new XWPFDocument(new FileInputStream("D:/format.docx"));
            // 获得模板文档的整体样式
            wordStyles = template.getStyle();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        // 新建的word文档对象
        XWPFDocument doc = new XWPFDocument();
        // 获取新建文档对象的样式
        XWPFStyles newStyles = doc.createStyles();
        // 关键行// 修改设置文档样式为静态块中读取到的样式
        newStyles.setStyles(wordStyles);

        // 开始内容输入
        // 标题1，1级大纲
        XWPFParagraph para1 = doc.createParagraph();
        // 关键行// 1级大纲
        para1.setStyle("1");
        XWPFRun run1 = para1.createRun();
        // 标题内容
        run1.setText("我是标题一");

        // 标题2
        XWPFParagraph para2 = doc.createParagraph();
        // 关键行// 2级大纲
        para2.setStyle("2");
        XWPFRun run2 = para2.createRun();
        // 标题内容
        run2.setText("我是标题二");

        // 正文
        XWPFParagraph paraX = doc.createParagraph();
        XWPFRun runX = paraX.createRun();
        // 正文内容
        runX.setText("我们都是宋体");
        runX.setFontFamily("宋体");
        runX.setStyle("1");
        runX.setStyle("\r");
        //段落
        XWPFParagraph secondParagraph = doc.createParagraph();
        XWPFRun run = secondParagraph.createRun();
        run.setText("我是蓝色加粗的");
        run.setColor("0000FF");
        run.setBold(true);
        run.setStyle("1");

        //基本信息表格
        XWPFTable infoTable = doc.createTable();
        //去表格边框
        //infoTable.getCTTbl().getTblPr().unsetTblBorders();
        // 列宽自动分割
        CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();
        infoTableWidth.setType(STTblWidth.DXA);
        infoTableWidth.setW(BigInteger.valueOf(9072));

        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("列名1");
        infoTableRowOne.addNewTableCell().setText("列名2");

        //表格第二行
        XWPFTableRow infoTableRowTwo = infoTable.createRow();
        infoTableRowTwo.getCell(0).setText("数据1");
        infoTableRowTwo.getCell(1).setText("数据2");

        // word写入到文件
        FileOutputStream fos = new FileOutputStream("D:/poi生成word练习.docx");
        doc.write(fos);
        fos.close();
    }
}
