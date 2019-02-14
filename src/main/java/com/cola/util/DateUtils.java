package com.cola.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/**
 * Copyright (C), 杭州未智科技有限公司
 *
 * @author: Cola
 * @date: 2019/01/07 15:22
 * @description:
 */
public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_A = "yyyyMMdd";
    public static final String TIME_FORMAT_A = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT_B = "yyyyMMddHHmmss";
    public static final String TIME_FORMAT_C = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String TIME_FORMAT_D = "yyyyMMdd";
    public static final String TIME_FORMAT_E = "yyyy年MM月dd日";
    public static final String TIME_FORMAT_F = "yyyyMMddHHmm";
    public static final String TIME_FORMAT_G = "yyyy年MM月dd日HH时mm分ss秒";
    public static final String TIME_FORMAT_H = "yyyy-MM-dd HH:mm";
    public static final String TIME_FORMAT_I = "HH:mm:ss";
    public static final String TIME_FORMAT_J = "yyyyMMddHHmmssSSS";
    public static final String TIME_FORMAT_K = "yy-M-d";
    public static final String TIME_FORMAT_L = "HH:mm";
    public static final String TIME_FORMAT_M = "MM月dd日 HH:mm";
    public static final String DATE_FORMAT_B = "yyyy.MM.dd";
    public static final String YEAR_FORMAT = "yyyy";
    public static final String YEAR_MONTH_FORMAT = "yyyy-MM";
    public static final String MONTH_DAY_FORMAT = "MM-dd";
    public static final String FORMAT_1 = ",##0.00";
    public static final String FORMAT_2 = "0.00";
    public static final String FORMAT_3 = ",###";
    public static final String FIRST_TIME = "1970-01-01 00:00:00";
    public static final String FORMAT_4 = "dd";
    public static final String DATE_FORMAT_N = "MM.dd";
    public static final String DATE_FORMAT_P = "MM.dd HH:mm";


    public DateUtils() {
    }

    public static String today() {
        return getDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String today(String format) {
        return getDateFormat(format).format(new Date());
    }

    public static String time() {
        return getDateFormat("HH:mm:ss").format(new Date());
    }

    public static String todayTime() {
        return getDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 秒级时间戳转日期对象
     *
     * @param timestamp 时间戳
     * @return 时间
     */
    public static Date timestamp2Date(Long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp * 1000);
        return calendar.getTime();
    }

    /***
     * 将Date类型转换成指定format的String类型
     * @param date 时间
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String date2String(Date date, String format) {
        String dateStr = null;

        try {
            if (date != null) {
                SimpleDateFormat ex = new SimpleDateFormat(format);
                dateStr = ex.format(date);
            }
        } catch (Exception var4) {
            logger.error("date to string failure. The detail information is: ", var4);
        }

        return dateStr;
    }

    /***
     * 将String类型的时间按照时间对应的format转换成Date
     * @param dateStr 时间字符串
     * @param format 时间格式
     * @return 时间
     */
    public static Date string2Date(String dateStr, String format) {
        if (dateStr !=null && format != null) {
            Date date = null;

            try {
                SimpleDateFormat ex = new SimpleDateFormat(format);
                date = ex.parse(dateStr);
            } catch (ParseException var4) {
                logger.error("string to date failure. The detail information is ", var4);
            }

            return date;
        } else {
            return null;
        }
    }

    /***
     * 获取指定Date时间当天的第一时间 ###### 00:00:00
     * @param date 时间
     * @return 时间
     */
    public static Date getBeginOfTheDay(Date date) {
        return null == date ? null : string2Date(date2String(date, "yyyy-MM-dd"), "yyyy-MM-dd");
    }

    /***
     * 获取指定Date时间当天的最后时间 ###### 23:59:59:999
     * @param date 时间
     * @return 时间
     */
    public static Date getEndOfTheDay(Date date) {
        if (null == date) {
            return null;
        } else {
            Date beginningOfTheDay = getBeginOfTheDay(date);
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            calendar.setTimeInMillis(beginningOfTheDay.getTime() + 86400000L - 1L);
            return calendar.getTime();
        }
    }
    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }


    /**
     * 获取当年的第一天
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     * @return
     */
    public static Date getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }



    /***
     * 当前时间是否在指定begin和end之间
     * @param begin 开始时间
     * @param end 结束时间
     * @param format 时间格式
     * @return True/False
     */
    public static boolean hasBetweenDate(String begin, String end, String format) {
        try {
            SimpleDateFormat e = new SimpleDateFormat(format);
            Date beginDate = e.parse(begin);
            Date endDate = e.parse(end);
            Date currentDate = new Date();
            Date current = e.parse(e.format(currentDate));
            return current.before(endDate) && current.after(beginDate);
        } catch (ParseException var8) {
            var8.printStackTrace();
            return false;
        }
    }

    /***
     * 获取指定Date时间所在月份的第一天 第一时刻
     * @param date 时间
     * @return 时间
     */
    public static Date setMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        date = calendar.getTime();
        return date;
    }

    /***
     * 获取指定Date时间所在月份的最后一天 最后时刻
     * @param date 时间
     * @return 时间
     */
    public static Date setMonthLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, 1);
        calendar.set(5, 1);
        calendar.add(5, -1);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取指定时间所在月份上的变化month，仅月份加减
     * @param date 时间
     * @param month 个数
     * @return 时间
     */
    public static Date setMonth(Date date, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, month.intValue());
        date = calendar.getTime();
        return date;
    }

    /***
     * 获取指定时间所在年份上的变化 year，仅年份加减
     * @param date 时间
     * @param year 个数
     * @return 时间
     */
    public static Date setYear(Date date, Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(1, year.intValue());
        date = calendar.getTime();
        return date;
    }

    /***
     * 获取指定时间所在天上的变化 num，仅天加减
     * @param date 时间
     * @param num 个数
     * @return 时间
     */
    public static Date setDate(Date date, Integer num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, num.intValue());
        date = calendar.getTime();
        return date;
    }

    /***
     * 获取指定时间所在小时上的变化 num，仅小时加减
     * @param date 时间
     * @param num 个数
     * @return 时间
     */
    public static Date setHour(Date date, Integer num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(11, num.intValue());
        date = calendar.getTime();
        return date;
    }

    /***
     * 获取指定时间所在分钟上的变化 num，仅分钟加减
     * @param date 时间
     * @param num 个数
     * @return 时间
     */
    public static Date setMinute(Date date, Integer num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(12, num.intValue());
        date = calendar.getTime();
        return date;
    }

    /***
     * 指定两个Date 计算相隔月份
     * @param date1 时间
     * @param date2 时间
     * @return
     */
    public static int getMonthDiff(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        int y1 = c1.get(1);
        int m1 = c1.get(2);
        int y2 = c2.get(1);
        int m2 = c2.get(2);
        return Math.abs((y1 - y2) * 12 + (m1 - m2));
    }

    /***
     * 指定两个Date 计算相隔天数 完整的一天时间带有时分秒
     * @param date1 时间
     * @param date2 时间
     * @return 时间
     */
    public static int getDaysDiff(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        Long between_days = Long.valueOf((c2.getTimeInMillis() - c1.getTimeInMillis()) / 86400000L);
        return Math.abs(between_days.intValue());
    }

    /***
     * 指定两个Date 计算相隔天数 抹掉时分秒
     * @param date1 时间
     * @param date2 时间
     * @return 时间
     */
    public static int getDaysDiffWithoutMinSec(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        c1.set(Calendar.HOUR_OF_DAY,0);
        c1.set(Calendar.MINUTE,0);
        c1.set(Calendar.SECOND,0);
        c1.set(Calendar.MILLISECOND,0);
        c2.set(Calendar.HOUR_OF_DAY,0);
        c2.set(Calendar.MINUTE,0);
        c2.set(Calendar.SECOND,0);
        c2.set(Calendar.MILLISECOND,0);
        Long between_days = Long.valueOf((c2.getTimeInMillis() - c1.getTimeInMillis()) / 86400000L);
        return Math.abs(between_days.intValue());
    }

    /***
     * 指定两个Date 计算相隔秒数
     * @param date1 时间
     * @param date2 时间
     * @return 时间
     */
    public static long getMilliSecondsDiff(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        Long milliSeconds = c2.getTimeInMillis() - c1.getTimeInMillis();
        return milliSeconds;
    }

    /***
     * 获取指定时间下一个小时的开始时刻
     * @param date 时间
     * @return 时间
     */
    public static Date getNextHourStart(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11,calendar.get(11)+1);
        calendar.set(12,0);
        calendar.set(13,0);
        calendar.set(14,0);
        return calendar.getTime();
    }

    /***
     * 获取上周 礼拜一 时间(时分秒是取当前时间)
     * @param date 时间
     * @return 时间
     */
    public static Date geLastWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }


    /***
     * 获取指定时间所在的周一
     * @param date 时间
     * @return 时间
     */
    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    private static SimpleDateFormat getDateFormat(String format) {
        return new SimpleDateFormat(format);
    }

    /***
     * 获取指定时间 所在的星期几 中文描述
     * @param date 时间
     * @return 时间
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }
}
