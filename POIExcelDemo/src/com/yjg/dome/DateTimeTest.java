package com.yjg.dome;

import java.util.Scanner;

/**
 *  @author jiguo.yan@hand-china.com
 *  @date 2019/7/16
 *  @Description: Java基础练习
 */
public class DateTimeTest {
    /**
     * 定义年、月、日
     */
    static int year;
    static int month;
    static int day;
    static boolean flag = true;

    public static void main(String[] args){
        //声明输入日期的格式
        System.out.println("日期输入的格式为”2015-07-26”形式");
        //创建Scanner对象
        Scanner sc=new Scanner(System.in);
        System.out.print("请输入日期：");
        //输入一个字符串
        String str = sc.next();
        //根据”-“将字符串拆开
        String[] strings = str.split("-");
        //将键盘输入的值进行拆开并赋值
        year = Integer.parseInt(strings[0]);
        month = Integer.parseInt(strings[1]);
        day = Integer.parseInt(strings[2]);
        //平年28天、闰年29天
        if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
            //闰年
            if(month==2){
                //如果是2月
                if(day < 29){
                    day += 1;
                }else if(day==29){
                    day = 1;
                    month += 1;
                }else{
                    System.out.println("输入的日期不存在");
                    flag = false;
                }
            }else if(month == 1|| month == 3 || month == 5||month == 7 || month == 8 || month == 10 || month == 12){
                getMaxMonth();
            }else if(month == 4 || month == 6 || month == 9 || month == 11){
                getMinMonth();
            }else{
                System.out.println("+\"输入的日期不存在\"+");
                flag = false;
            }
            if(flag) {
                getZero();
            }
        }else{
            //平年
            if(month==2){
                //如果是2月
                if(day < 28){
                    day += 1;
                }else if(day == 28){
                    day = 1;
                    month += 1;
                }else{
                    System.out.println("输入的日期不存在");
                    flag = false;
                }
            }else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
                getMaxMonth();
            }else if(month==4||month==6||month==9||month==11){
                getMinMonth();
            }else{
                System.out.println("输入的日期不存在");
                flag = false;
            }
            if(flag) {
                getZero();
            }
        }
    }
    //每年中大月的判断
    public static void getMaxMonth(){
        if(month==12 && day == 31){
            year+=1;
            day = 1;
            month = 1;
        }else{
            //如果是大月
            if(day<31){
                day+=1;
            }else if(day==31){
                day = 1;
                month+=1;
            }else{
                //如果输入的天数大于31
                System.out.println("输入的日期不存在");
                flag = false;
            }
        }
    }
    //每年中小月的判断
    public static void getMinMonth(){
        //如果是小月
        if(day<30){
            day+=1;
        }else if(day==30){
            day = 1;
            month+=1;
        }else{
            //如果输入的天数大于30
            System.out.println("输入的日期不存在");
            flag = false;
        }
    }
    //对输出中0的处理
    public static void getZero(){
        //判断是否要拼接0
        if (month < 10  && day < 10) {
            System.out.println("下一天为" + year + "-0" + month + "-0" + day);
        } else if (month  < 10 || day > 10) {
            System.out.println("下一天为" + year + "-0" + month + "-" + day);
        } else if (month  > 10 || day < 10) {
            System.out.println("下一天为" + year + "-" + month + "-0" + day);
        }
    }
}
