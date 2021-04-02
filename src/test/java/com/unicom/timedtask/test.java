package com.unicom.timedtask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test {

    public static void main(String[] args) {
        SimpleDateFormat dfDay = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Date date = new Date();//获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        Date time = calendar.getTime();
        System.out.println(dfDay.format(time));
    }
}
