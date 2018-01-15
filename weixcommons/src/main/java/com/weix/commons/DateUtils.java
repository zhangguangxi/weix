package com.weix.commons;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

public  abstract class DateUtils {
    private static String DEFAULT_DATE_SEPARATOR="-";

    /**
     * 字符串 -> 日期对象
     * @param date 日期
     * @param pattern
     * @return
     */
    public static Date parse(String date, String pattern){
        return DateTime.parse(date, DateTimeFormat.forPattern(pattern)).toDate();
    }

    /**
     * 字符串 -> 日期对象
     * @param date 日期
     * @return
     */
    public static Date parse(String date){
        String dateNumbers = date.replaceAll("[^0-9]", "");
        String pattern ="yyyyMMddHHmmssSSS".substring(0,dateNumbers.length());
        return parse(dateNumbers,pattern);
    }


    /**
     * 日期对象 -> 字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String get(Date date,String pattern){
        DateTime dateTime = new DateTime(date);
        return dateTime.toString();
    }

    /**
     * 日期对象 -> 字符串
     * @param date
     * @param separator
     * @return
     */
    public static String  getYmd(Date date,String separator){
        return get(date,new StringBuilder("yyyy").append(separator).append("MM").append("dd").toString());
    }

    /**
     * 日期对象 -> 字符串
     * @param date
     * @return
     */
    public static  String getYmd(Date date){
        return getYmd(date,DEFAULT_DATE_SEPARATOR);
    }

    /**
     * 日期对象 -> 字符串
     * @param separator
     * @return
     */
    public static String getYmd(String separator){
        return getYmd(new Date(),separator);
    }


    /**
     * 日期对象 -> 字符串
     * @return
     */
    public static String getYmd(){
        return getYmd(new Date());
    }

    /**
     * 日期对象 -> 字符串
     * @param date
     * @param separator
     * @return
     */
    public static String getYmdHms(Date date,String separator){
        return get(date,new StringBuilder("yyyy").append(separator).append("MM").append(separator).append("dd").append("HH:mm:ss").toString());
    }


    /**
     * 日期对象 -> 字符串
     * @param date
     * @return
     */
    public static String getYmdHms(Date date){
        return getYmdHms(date,DEFAULT_DATE_SEPARATOR);
    }

    /**
     * 日期对象 -> 字符串
     * @param separator
     * @return
     */
    public static String getYmdHms(String  separator){
        return getYmdHms(new Date(),separator);
    }

    /**
     * 日期对象 -> 字符串
     * @return
     */
    public static String getYmdHms(){
        return getYmdHms(new Date());
    }


}
