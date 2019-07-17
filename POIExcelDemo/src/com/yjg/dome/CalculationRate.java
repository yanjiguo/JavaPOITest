package com.yjg.dome;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *  @author jiguo.yan@hand-china.com
 *  @date 2019/7/16
 *  @Description: Java基础练习
 */
public class CalculationRate {

    public static void main(String[] args) {
        //创建Scanner对象
        System.out.println("****  系统温馨提示如果退出系统请输入-1  ****");
        //System.in表示标准化输出，也就是键盘输出
        System.out.println("请输入你的身份（1：代表本国籍人2：代表外籍人）:");
        Scanner sc = new Scanner(System.in);
        double flag  = sc.nextDouble();
        Double str = 0.0;
        while(str != -1){
            System.out.print("请输入工资:");
            str = sc.nextDouble();
            //保留两位小数第三位如果大于4会进一位（四舍五入）
            DecimalFormat df = new DecimalFormat("#.00");
            if(flag==1){
                if(str > 3500){
                    System.out.println("所需要缴纳的税费为"+df.format(getTollage(str,flag)));
                }else if(str==-1){
                    System.out.println("系统已退出");
                }else{
                    System.out.println("不好意思你还没有达到起征点,请重新输入");
                }
            }else if(flag==2){
                flag = str;
                if(str > 4500){
                    System.out.println("所需要缴纳的税费为"+df.format(getTollage(str,flag)));
                }else if(str==-1){
                    System.out.println("系统已退出");
                }else{
                    System.out.println("不好意思你还没有达到起征点,请重新输入");
                }
            }
        }
    }
    /**
     * 工资个人所得税计算程序
     * @param tollage
     * @return
     */
    public static Double getTollage(Double tollage,Double flag){
        //定义一个需要缴纳费用的字段
        double ratepaying = 0;
        double balance = 0;
        if(flag==1) {
            //余额 = 收入—起征点
            balance = tollage - 3500;
        }else if(flag==1){
            balance = tollage - 4500;
        }
        if(balance<=1500){
            //在相应的范围内乘以税率
            ratepaying = balance*0.03;
        }else if(balance > 1500 && balance <= 4500){
            ratepaying = balance*0.10;
        }else if(balance > 4500 && balance <= 9000){
            ratepaying = balance*0.20;
        }else if(balance > 9000 && balance<=35000){
            ratepaying = balance*0.25;
        }else if(balance > 35000 && balance <= 55000){
            ratepaying = balance*0.30;
        }else if(balance > 5500 && balance <= 80000){
            ratepaying = balance*0.35;
        }else if(balance > 80000){
            ratepaying = balance*0.45;
        }
        return ratepaying;
    }
}
