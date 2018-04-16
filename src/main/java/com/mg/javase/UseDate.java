package com.mg.javase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;

/**
 *  java.util.Date部分方法的使用
 */
public class UseDate {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);//此处自动调用date的toString方法
        System.out.println(date.toString());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date date1 = new Date();
        System.out.println(date.after(date1));//判断date是否在date1之后
        System.out.println(date.before(date1));//判断date是否在date1之前
        System.out.println(date.compareTo(date1));//比较date和date1,相等返回0,date在date1之前返回负数,date在date1之后返回正数
        System.out.println("date:" + date.getTime() + ",date1:" + date1.getTime());//getTime获取当前时间到1970年1月1日 00:00:00的毫秒数

        //格式化日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        //这一行代码确立了转换的格式，其中 yyyy 是完整的公元年，MM 是月份，dd 是日期，HH:mm:ss 是时、分、秒
        //有的格式大写，有的格式小写，例如 MM 是月份，mm 是分；HH 是 24 小时制，而 hh 是 12 小时制
        System.out.println(simpleDateFormat.format(new Date()));
        //将字符串格式的日期转化为Date类型
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            System.out.println(dateFormat.parse("20171221132534"));
        } catch (ParseException e) {
            System.out.println("请输入正确的时间");
            e.printStackTrace();
        }
        Date date2 = new Date(100000);
        System.out.println(date2);
        System.out.println("========================================================="+"\r\n");


        //Calendar类的使用
        Calendar c = Calendar.getInstance();//默认是当前日期的Calendar的对象
        System.out.println(c);
        System.out.println(c.getTime());
        c.set(2010, 6 - 1,12 );//指定日期2010年6月12
        System.out.println(c.getTime());
        //使用Calendar类字段类型设置时间
        c.set(Calendar.YEAR, 2018);
        c.set(Calendar.MONTH, 8 - 1);//月份从0开始
        c.set(Calendar.DAY_OF_MONTH, 8);
        c.setTime(new Date());
        System.out.println(c.get(Calendar.MONTH));
        // 获得星期几（注意（这个与Date类是不同的）：1代表星期日、2代表星期1、3代表星期二，以此类推）
        System.out.println(c.get(Calendar.DAY_OF_WEEK));
        c.set(Calendar.DAY_OF_WEEK, 0);//0和7都代表着周6
        System.out.println(c.getTime());
    }

}
