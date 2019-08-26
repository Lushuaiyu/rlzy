package com.nado.rlzy.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @ClassName string 工具类
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/5/24 9:35
 * @Version 1.0
 */
public class StringUtil {

    /**
     * double 转bigDecimal 保留两位小数
     *
     * @return java.lang.String
     * @Author lushuaiyu
     * @Description
     * @Date 12:31 2019/5/26
     * @Param * @param value
     **/
    public static String decimalFormat(Double value) {
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(value);
    }


    /**
     * decimal 转 string
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 10:40 2019/8/26
     * @Param [decimal]
     * @return java.lang.String
     **/
    public static String decimalToString(BigDecimal decimal){
        DecimalFormat format = new DecimalFormat();
        return format.format(decimal);

    }

    public static String decimalFormat2(Double value) {
        DecimalFormat format = new DecimalFormat();
        return format.format(value);
    }


    public static BigDecimal decimal(String str) {
        BigDecimal decimal = new BigDecimal(str);
        return decimal;

    }


    /**
     * 将整形数字转换成String
     *
     * @return java.lang.String
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:51 2019/6/27
     * @Param [value]
     **/
    public static String decimalFormatByInt(Double value) {
        DecimalFormat format = new DecimalFormat();
        return format.format(value);
    }

    /**
     * integer 转 string
     *
     * @return java.lang.String
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:52 2019/6/27
     * @Param [obj]
     **/
    public static String toString(Object obj) {
        return (obj == null) ? null : obj.toString();
    }


    /**
     * 将 stirng 转 int
     *
     * @return int
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 15:54 2019/6/27
     * @Param [obj]
     **/
    public static int toInt(String str) {
        try {
            int a = Integer.parseInt(str);
            return a;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        return 1;
    }


    /**
     * 判断字符串是否为空
     *
     * @return java.lang.String
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 13:09 2019/6/29
     * @Param [str, msg]
     **/
    public static String isBank(String str, String msg) {
        if (StringUtils.isBlank(str)) {
            throw new RuntimeException(msg);
        }
        return str;
    }

    /**
     * 字符串转时间
     *
     * @return java.util.Date
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 16:33 2019/7/4
     * @Param [str]
     **/
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转换成字符串
     *
     * @param date
     * @return str
     */
    public static String DateToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }


    /**
     * 身份证打码
     *
     * @return java.lang.String
     * @Author lushuaiyu
     * @Description //TODO
     * @Date 17:18 2019/7/9
     * @Param [realName]
     **/
    public static String replaceStr(String realName) {
        StringBuffer stringBuffer = new StringBuffer();
        if (StringUtils.isNotBlank(realName)) {
            for (int i = 0; i < realName.length(); i++) {
                stringBuffer.append("*");
            }
        }
        return stringBuffer.toString();
    }

    public static boolean isEmpty(String input) {
        return input == null || input.equals("") || input.matches("value is null");
    }

    public static LocalDateTime strToLocalDateTime(String str){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(str, formatter);
    }

    public static String localdatetimeToStr(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(localDateTime);
        return format;
    }


}