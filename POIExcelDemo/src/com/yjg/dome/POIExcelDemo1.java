package com.yjg.dome;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
/**
 *  @author jiguo.yan@hand-china.com
 *  @date 2019/7/15
 *  @Description: POI操作Excel表格
 */
public class POIExcelDemo1 {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        //创建工作簿---->XSSF代表10版的Excel(HSSF是03版的Excel)
        XSSFWorkbook wb = new XSSFWorkbook();
        //工作表
        XSSFSheet sheet2 = wb.createSheet("第一个sheet页");
        XSSFSheet sheet = wb.createSheet("第二个sheet页");
        //设置行，2代表第三行
        XSSFRow header2=sheet2.createRow(4);
        //设置列，1代表第三行第二列
        XSSFCell cell2=header2.createCell(2);
        cell2.setCellValue("我是循环写入的数据头");
        //设置循环
        for (int i = 1;i< 6;i++){
            sheet2.createRow(i+4).createCell(2).setCellValue(i);
        }
        //设置行，2代表第三行
        XSSFRow header=sheet.createRow(2);
        //设置列，1代表第三行第二列
        XSSFCell cell=header.createCell(1);
        cell.setCellValue("我是合并单元格，宋体十八号");
        XSSFFont font = wb.createFont();
        //设置字体的颜色
        font.setColor(Font.COLOR_RED);
        //设置字体的样式
        font.setFontName("宋体");
        //设置大小
        font.setFontHeightInPoints((short)18);
        //创建单元格格式CellStyle
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFont(font);
        //字体作用单元格
        cell.setCellStyle(cellStyle);
        //合并单元格
        CellRangeAddress cra=new CellRangeAddress(2, 2, 1, 3);
        //在sheet里增加合并单元格
        sheet.addMergedRegion(cra);
        //设置列的宽度
        //getPhysicalNumberOfCells()代表这行有多少包含数据的列
        for(int i=0;i<header.getPhysicalNumberOfCells();i++){
         //POI设置列宽度时比较特殊，它的基本单位是1/255个字符大小，
         //因此我们要想让列能够盛的下20个字符的话，就需要用255*20
         sheet.setColumnWidth(i, 255*20);
        }
        //设置行高，行高的单位就是像素，因此30就是30像素的意思
        header.setHeightInPoints(30);
        //上面设置好了内容，我们当然是要输出到某个文件的，输出就需要有输出流
        FileOutputStream fos= new FileOutputStream("d:/poi生成excel练习.xlsx");
        //向指定文件写入内容
        wb.write(fos);
        fos.close();
    }
}

