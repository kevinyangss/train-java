package com.kevin.utils;

import com.kevin.base.BaseApp;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Description 时间工具类
 * @Author kevin.yang
 * @Date 2020-12-11 20:48
 */
public class DateUtil extends BaseApp{

    public final static String yyMMdd = "yyMMdd";
    public final static String yyyyMMdd = "yyyy-MM-dd";
    public final static String yyyyMM = "yyyy-MM";
    public final static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public final static String DATEHOURMIN_PATTERN = "yyyy-MM-dd HH:mm";
    public final static String DATETIME_PATTERN_TRIM = "yyyyMMddHHmmss";
    public final static String TIMESTAMP_PATTERN = "HH:mm:ss";
    public final static String YYYYMMDD = "YYYYMMdd";

    public static void main(String[] args) {
        Date date = new Date();

        println("nowTime", true);
        long nowTime = nowTime();
        System.out.println(nowTime);

        println("nowDate", true);
        Date nowDate = nowDate();
        System.out.println(nowDate);

        println("nowTimestamp", true);
        Date nowTimestamp = nowTimestamp();
        System.out.println(nowTimestamp);

        println("formatyyMMdd", true);
        formatyyMMdd(yyMMdd);

        println("getEndTimeOfDay", true);
        Date endTimeOfDay = getEndTimeOfDay(date);
        System.out.println(endTimeOfDay.getTime());
        System.out.println(format(endTimeOfDay, DATETIME_PATTERN));
    }

    /**
     * 当前时间
     */
    public static long nowTime(){
        return System.currentTimeMillis();
    }

    /**
     * 当前时间
     */
    public static Date nowDate(){
        return new Date(System.currentTimeMillis());
    }

    /**
     * 当前时间
     */
    public static Timestamp nowTimestamp(){
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 格式化日期：yyMMdd
     */
    public static String format(Date date, String formatPattern){
        return new SimpleDateFormat(formatPattern).format(date);
    }

    /**
     * 格式化日期：yyMMdd
     */
    public static String formatyyMMdd(String formatPattern){
        Date date = new Date();
        return new SimpleDateFormat(formatPattern).format(date);
    }

    /**
     * 获取date所在日的23:59:59
     * @param date
     * @return
     */
    public static Date getEndTimeOfDay(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        setCalendarToEndTime(cal);
        return cal.getTime();
    }

    private static void setCalendarToEndTime(Calendar cal) {
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        // 时分秒（毫秒数）
        long millisecond = hour * 60 * 60 * 1000L + minute * 60 * 1000L + second * 1000L;
        // 凌晨00:00:00
        cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);
        // 凌晨23:59:59
        cal.setTimeInMillis(cal.getTimeInMillis() + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
    }
}
