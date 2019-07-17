# JavaPOITest
POI操作word文档和excel表格，以及计算日期和计算交税的Demo。

1.CalculationRate类中主要是计算每一个人要交纳的税。

​	其中一开始的时候首先看到这么多缴纳的范围就会想到使用while -- case 或者if--else if-- else；键盘输入使用的是scanner类。对于数的计算结果我使用DecimalFormat df = new DecimalFormat("#.00");保留两位小数第三位如果大于4会进一位（四舍五入）。

2.DateTimeTest类主要是计算日期

​	同样使用的是键盘输入使用到scanner类，首先在计算日期的过程中分为闰年和平年，对于两个年中的2月进行分别计算，在接下来判断每年的大月和小月。还有在每一年的最后一天在加一天是新的一年；还要判断月份是1-12个月天数是1-30或者1-31天。

3.POIExcelDemo1类主要是使用POI操作Excel

​	1>创建工作簿---->XSSF代表10版的Excel(HSSF是03版的Excel)   XSSFWorkbook wb = new XSSFWorkbook();

​	2>工作表   wb.createSheet

​	3>设置行和设置列   

​	4>设置值，样式

​	5>上面设置好了内容，我们当然是要输出到某个文件的，输出就需要有输出流

4.PoiWordDemo1类主要使用POI操作word

​	使用XWPFDocument document= new XWPFDocument();中提供的方法进行对word文档中的内容和样式进行设置。

5.PoiWordDemo2类主要使用利用模板的方式来操作word

​	1.首先读取模板文档   template = new XWPFDocument(new FileInputStream("D:/format.docx"));

​	2.获得模板文档的整体样式

​	3.修改设置文档样式为静态块中读取到的样式  newStyles.setStyles(wordStyles);

​	4.word写入到文件FileOutputStream fos = new FileOutputStream("D:/poi生成word练习.docx");