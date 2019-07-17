package com.yjg.dome;


import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
/**
 *  @author jiguo.yan@hand-china.com
 *  @date 2019/7/15
 *  @Description: POI操作Word
 */
public class PoiWordDemo1 {
    public static void main(String[] args){
        XWPFDocument document= new XWPFDocument();

        //Write the Document in file system
        FileOutputStream out = null;
        try{
            out = new FileOutputStream("D:/poi生成word练习.docx");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
       //添加标题一
        XWPFParagraph titleParagraph = document.createParagraph();
        titleParagraph.setSpacingAfter(16);
        titleParagraph.setSpacingBefore(17);
        XWPFRun titleParagraphRun = titleParagraph.createRun();
        titleParagraphRun.setStyle("1");
        titleParagraphRun.setText("我是标题一");
        titleParagraphRun.setBold(true);
        titleParagraphRun.setFontSize(22);

        //添加标题二
        XWPFParagraph titleParagraph2 = document.createParagraph();
        XWPFRun titleParagraphRun2 = titleParagraph2.createRun();
        titleParagraphRun2.setStyle("1");
        titleParagraphRun2.setBold(true);
        titleParagraphRun2.setText("我是标题二");
        titleParagraphRun2.setFontSize(16);
        //段落
        XWPFParagraph firstParagraph = document.createParagraph();
        XWPFRun run = firstParagraph.createRun();
        run.setText("我们都是宋体");
        run.setFontFamily("宋体");
        run.setStyle("1");
        run.setStyle("\r");
        //段落
        XWPFParagraph secondParagraph = document.createParagraph();
        XWPFRun run1 = secondParagraph.createRun();
        run1.setText("我是蓝色加粗的");
        run1.setColor("0000FF");
        run1.setBold(true);
        run1.setStyle("1");

        //基本信息表格
        XWPFTable infoTable = document.createTable();
        //去表格边框
        //infoTable.getCTTbl().getTblPr().unsetTblBorders();

        //列宽自动分割
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

        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);

        //添加页眉
        CTP ctpHeader = CTP.Factory.newInstance();
        CTR ctrHeader = ctpHeader.addNewR();
        CTText ctHeader = ctrHeader.addNewT();
        String headerText = "ctpHeader";
        ctHeader.setStringValue(headerText);
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
        //设置为右对齐
        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
        parsHeader[0] = headerParagraph;
        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);

        //添加页脚
        CTP ctpFooter = CTP.Factory.newInstance();
        CTR ctrFooter = ctpFooter.addNewR();
        CTText ctFooter = ctrFooter.addNewT();
        String footerText = "ctpFooter";
        ctFooter.setStringValue(footerText);
        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
        headerParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFParagraph[] parsFooter = new XWPFParagraph[1];
        parsFooter[0] = footerParagraph;
        policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);
        try{
            document.write(out);
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
