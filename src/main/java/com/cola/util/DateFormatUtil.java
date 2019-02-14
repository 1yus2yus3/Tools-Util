package com.cola.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Cola
 * @date: 2019/01/07 17:43
 * @description: 时间转换
 */
public class DateFormatUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static final String TIME_FORMAT_A = "yyyy年M月d日 HH:mm";
    public static final String TIME_FORMAT_B = "M月d日 HH:mm";
    public static final String TIME_FORMAT_BEFORE_YESTERDAY_A = "前天 HH:mm";
    public static final String TIME_FORMAT_BEFORE_YESTERDAY_B = "前天 HH:mm:ss";
    public static final String TIME_FORMAT_YESTERDAY_A = "昨天 HH:mm";
    public static final String TIME_FORMAT_YESTERDAY_B = "昨天 HH:mm:ss";
    public static final String TIME_FORMAT_TODOY_JUST_NOW = "刚刚";
    public static final String TIME_FORMAT_TODOY_JUST_NOW_SECOND = "秒前";
    public static final String TIME_FORMAT_TODOY_JUST_NOW_MINT = "分钟前";
    public static final String TIME_FORMAT_TODOY_A = "今天 HH:mm";
    public static final String TIME_FORMAT_TODOY_B = "今天 HH:mm:ss";
    public static final String TIME_FORMAT_TOMORROW_A = "明天 HH:mm";
    public static final String TIME_FORMAT_TOMORROW_B = "明天 HH:mm:ss";
    public static final String TIME_FORMAT_AFTER_TOMORROW_A = "后天 HH:mm";
    public static final String TIME_FORMAT_AFTER_TOMORROW_B = "后天 HH:mm:ss";

    /***
     * 刚刚 时间跨度区间
     */
    public static final long JUST_NOW_INTERVAL = 5*1000L;

    /***
     * 分钟前 时间跨度区间
     */
    public static final long MINUTE_INTERVAL = 60*1000L;

    /***
     * 小时前 时间跨度区间
     */
    public static final long HOUR_INTERVAL = 60*60*1000L;
    /***
     * 一天耗时
     */
    public static final long DAY_INTERVAL = 24*60*60*1000L;

    /***
     * 自动转换指定时间
     * @param date
     * @return
     */
    public static String getTimeStringDesc(Date date){
        if(date == null){
            return null;
        }
        return getCurrentDate(date);
    }

    private static String getCurrentDate(Date date) {
        long difference = System.currentTimeMillis() - date.getTime();
        Date dateStart = DateUtils.getBeginOfTheDay(new Date());
        Date dateEnd = DateUtils.getEndOfTheDay(new Date());
        Date dateYearStart = DateUtils.getCurrYearFirst();
        Date dateYearEnd = DateUtils.getCurrYearLast();
        //1:未来的时间
        if(date.getTime() > dateEnd.getTime()){
            //1.1：明天 HH:mm
            if((dateStart.getTime() + DAY_INTERVAL) >= date.getTime() && (dateEnd.getTime() + DAY_INTERVAL) < date.getTime()) {
                return DateUtils.date2String(date,TIME_FORMAT_TOMORROW_A);
            }
            //1.2：后天 HH:mm
            else if((dateStart.getTime() + 2 * DAY_INTERVAL) >= date.getTime() && (dateEnd.getTime() + 2 * DAY_INTERVAL) < date.getTime()) {
                return DateUtils.date2String(date,TIME_FORMAT_AFTER_TOMORROW_A);
            }
            //1.3：今年 M月d日 HH:mm
            else if((dateEnd.getTime() + 2 * DAY_INTERVAL) >= date.getTime() && date.getTime() < dateYearEnd.getTime()) {
                return DateUtils.date2String(date,TIME_FORMAT_B);
            }
            //1.4：以后 "yyyy年M月d日 HH:mm"
            else {
                return DateUtils.date2String(date,TIME_FORMAT_A);
            }
        }
        //2:今天
        else if(date.getTime() < dateEnd.getTime() && date.getTime() > dateStart.getTime()){
            //2.1：刚刚
            if(difference <= JUST_NOW_INTERVAL) {
                return TIME_FORMAT_TODOY_JUST_NOW;
            }
            //2.2：ss秒前
            else if(difference > JUST_NOW_INTERVAL && difference <= MINUTE_INTERVAL){
                return difference/1000L + TIME_FORMAT_TODOY_JUST_NOW_SECOND;
            }
            //2.3：mm分前
            else if(difference > MINUTE_INTERVAL && difference <= HOUR_INTERVAL){
                return difference/(60*1000L) + TIME_FORMAT_TODOY_JUST_NOW_MINT;
            }
            //2.4：今天 HH:mm
            else if(difference > HOUR_INTERVAL){
                return DateUtils.date2String(date,TIME_FORMAT_TODOY_A);
            }
        }
        //3:过去的时间
        else {
            //3.1：昨天 HH:mm
            if((dateStart.getTime() - DAY_INTERVAL) <= date.getTime() && (dateEnd.getTime() - DAY_INTERVAL) > date.getTime()) {
                return DateUtils.date2String(date,TIME_FORMAT_YESTERDAY_A);
            }
            //3.2：前天 HH:mm
            else if((dateStart.getTime() - 2 * DAY_INTERVAL) <= date.getTime() && (dateEnd.getTime() - 2 * DAY_INTERVAL) > date.getTime()) {
                return DateUtils.date2String(date,TIME_FORMAT_BEFORE_YESTERDAY_A);
            }
            //3.3：M月d日 HH:mm
            else if((dateEnd.getTime() - 2 * DAY_INTERVAL) >= date.getTime() && date.getTime() > dateYearStart.getTime()) {
                return DateUtils.date2String(date,TIME_FORMAT_B);
            }
            //3.4：yyyy年M月d日 HH:mm
            else {
                return DateUtils.date2String(date,TIME_FORMAT_A);
            }
        }
        return DateUtils.date2String(date,TIME_FORMAT_A);
    }



    public static void main(String[] args) {
        Date date = DateUtils.string2Date("2019-02-15 17:13:00",DateUtils.TIME_FORMAT_A);
        System.out.println(getTimeStringDesc(date));
    }
}
