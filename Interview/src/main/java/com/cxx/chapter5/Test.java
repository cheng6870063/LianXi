package com.cxx.chapter5;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Test {
    public static void main(String ... args) {

        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");
        //时间转为字符串
        LocalDateTime date = LocalDateTime.now();
        String str = date.format(f);  // 2014-11-07 14:10:36
        System.out.println(str);
        //字符串转为时间
        date = LocalDateTime.parse(str, f);
/*
        LocalDateTime localDateTime = LocalDateTime.of(date.minusYears(4).getYear(),01,01,00,00,00);
        System.out.println(localDateTime);
        try {
            getMonthBetween(localDateTime,date);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        LocalDate now = LocalDate.now();
        //LocalDate now = LocalDate.of(2018,06,01);
        LocalDate start = LocalDate.of(now.minusYears(4).getYear(),01,01);
        LocalDate start1 = now.minusMonths(1).plusDays(1);
        try {
            List<String> monthlist = getMonthBetween(start,now);
            List<String> daylist = getDayBetween(start1,now);
            System.out.println(monthlist);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> getMonthBetween(LocalDate minDate, LocalDate maxDate) throws Exception {
        List<String> result = new ArrayList<String>();
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM");
        while(minDate.isBefore(maxDate)) {
            result.add(minDate.format(sdf));
            minDate = minDate.plusMonths(1);
            //当当前日期为1号的时候，能获取到当前月
            if(minDate.isEqual(maxDate)){
                result.add(maxDate.format(sdf));
            }
        }
        return result;
    }

    private static List<String> getDayBetween(LocalDate minDate, LocalDate maxDate)throws Exception {
        List<String> result = new ArrayList<String>();
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while(minDate.isBefore(maxDate)) {
            result.add(minDate.format(sdf));
            minDate = minDate.plusDays(1);
            //当当前日期为1号的时候，能获取到当前月
            if(minDate.isEqual(maxDate)){
                result.add(maxDate.format(sdf));
            }
        }
        return result;
    }


}
