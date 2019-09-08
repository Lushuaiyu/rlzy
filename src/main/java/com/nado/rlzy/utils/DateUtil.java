package com.nado.rlzy.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {

    /**
     * 获取当前时间的格式化字符串如：2018-08-31
     *
     * @return
     */
    public static String getCurrentYMD() {
        StringBuilder sb = new StringBuilder("");
        Calendar now = Calendar.getInstance();
        sb.append(now.get(Calendar.YEAR));
        if (now.get(Calendar.MONTH) <= 8) {
            sb.append(0);
        }
        sb.append(now.get(Calendar.MONTH) + 1);
        if (now.get(Calendar.DAY_OF_MONTH) <= 9) {
            sb.append(0);
        }
        sb.append(now.get(Calendar.DAY_OF_MONTH));
        return sb.toString();
    }

    /**
     * 拼接获取兑换订单的订单号
     *
     * @param date
     * @param lineNumber
     * @return
     */
    public static String getEXOrde_Id(String date, int lineNumber) {
        StringBuilder sb = new StringBuilder("EX");
        sb.append(date);
        int len = 14 - (date + lineNumber).length();
        for (int i = 0; i < len; i++) {
            sb.append(0);
        }
        sb.append(lineNumber);
        return sb.toString();
    }

    /**
     * 拼接获取商品主订单号
     *
     * @param date
     * @param
     * @return
     */
    public static String getOMOrde_Id(String date, int memberId, int briefChapterId) {
        StringBuilder sb = new StringBuilder()/*new StringBuilder("OM")*/;
        sb.append(date);
		/*int len = 14 - (date + lineNumber).length();
		for (int i = 0; i < len; i++) {
			sb.append(0);
		}*/
        sb.append("-");
        sb.append(memberId);
        sb.append("-");
        sb.append(briefChapterId);
        sb.append("-");
        long time = System.currentTimeMillis();
        sb.append(time);
        return sb.toString();
    }

    /**
     * 拼接获取顶级订单号id
     *
     * @param date
     * @param lineNumber
     * @return
     */
    public static String getTOOrde_Id(String date, int lineNumber) {
        StringBuilder sb = new StringBuilder("TO");
        sb.append(date);
        int len = 14 - (date + lineNumber).length();
        for (int i = 0; i < len; i++) {
            sb.append(0);
        }
        sb.append(lineNumber);
        return sb.toString();
    }

    public static String getDateOnString(long time) {
        Date date = new Date(time);
        String str = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        str = formatter.format(date);
        return str;
    }

    public static String getCurrentDateOnString() {
        Date date = new Date(System.currentTimeMillis());
        String str = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        str = formatter.format(date);
        return str;
    }

    public static String LoginDateOnString(long time) {
        Date date = new Date(time);
        String str = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        str = formatter.format(date);
        return str;
    }

    public static long getDateLong(String str) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fmt.parse(str);
        } catch (ParseException e) {
            DateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            e.printStackTrace();
            try {
                return fmt2.parse(str).getTime();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }

        }
        return date.getTime();
    }

    public static long getDateHMSLong(String str) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = fmt.parse(str);
        } catch (ParseException e) {
        }
        return date.getTime();
    }

    /**
     * 参数如20170831113300转化为2017-08-31 11：33：00
     *
     * @param str
     * @return
     */
    public static Long getVailTimeToLong(String str) {
        StringBuilder sb = new StringBuilder("");
        sb.append(str.substring(0, 4));
        sb.append("-");
        sb.append(str.substring(4, 6));
        sb.append("-");
        sb.append(str.substring(6, 8));
        sb.append(" ");
        sb.append(str.substring(8, 10));
        sb.append(":");
        sb.append(str.substring(10, 12));
        sb.append(":");
        sb.append(str.substring(12, 14));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(sb.toString());
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 随机获取4个数字
     *
     * @return
     */
    public static String getFourRandom() {
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if (randLength < 4) {
            for (int i = 1; i <= 4 - randLength; i++)
                fourRandom = "0" + fourRandom;
        }
        return fourRandom;
    }


    /**
     * 是否可以签到
     *
     * @param t
     * @return
     */
    public static boolean checkOn(Timestamp t) {
        if (t == null) {
            return true;
        }
        long time = t.getTime();
        Date date = new Date(time);
        Calendar last = Calendar.getInstance();
        last.setTime(date);
        Calendar now = Calendar.getInstance();
        if (now.get(Calendar.YEAR) > last.get(Calendar.YEAR)) {
            return true;
        } else if (now.get(Calendar.YEAR) == last.get(Calendar.YEAR)) {
            if (now.get(Calendar.MONTH) > last.get(Calendar.MONTH)) {
                return true;
            } else if (now.get(Calendar.DAY_OF_MONTH) > last.get(Calendar.DAY_OF_MONTH)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断当前时间和给定时间是否是连续的天数
     *
     * @param t
     * @return
     */
    public static boolean continuityCheckOn(Timestamp t) {
        if (t == null) {
            return false;
        }
        long time = t.getTime();
        Date date = new Date(time);
        Calendar last = Calendar.getInstance();
        last.setTime(date);
        Calendar now = Calendar.getInstance();
        if (last.get(Calendar.YEAR) == now.get(Calendar.YEAR) && now.get(Calendar.MONTH) == last.get(Calendar.MONTH)
                && now.get(Calendar.DAY_OF_MONTH) == last.get(Calendar.DAY_OF_MONTH) + 1) {
            return true;
        }
        return false;
    }

    public static String getNickName() {
        return "新用户" + getFourRandom();
    }

    public static String getCurrentSub(int length) {
        String current = String.valueOf(System.currentTimeMillis());
        if (length >= current.length())
            return "";
        return current.substring(current.length() - length, current.length());

    }

    /**
     * 获取当前日期，如：2017-08-31
     *
     * @return
     */
    public static String getCurrentDay() {
        StringBuilder sb = new StringBuilder("");
        Calendar now = Calendar.getInstance();
        sb.append(now.get(Calendar.YEAR));
        sb.append("-");
        if (now.get(Calendar.MONTH) <= 8) {
            sb.append(0);
        }
        sb.append(now.get(Calendar.MONTH) + 1);
        sb.append("-");
        if (now.get(Calendar.DAY_OF_MONTH) <= 9) {
            sb.append(0);
        }
        sb.append(now.get(Calendar.DAY_OF_MONTH));
        return sb.toString();
    }

    public static String getFirstDayOfMonth() {// 获取当前月份的第一天
        Calendar cal = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        cal.set(Calendar.YEAR, now.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, Calendar.DAY_OF_MONTH + 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());

        return firstDayOfMonth;
    }

    /**
     * 是否是100的倍数
     *
     * @param num
     * @return
     */
    public static int get100bei(int num) {
        int count = num / 100;
        if (num - (count * 100) == 0) {
            return count;
        }
        return 0;
    }

    /**
     * date to localDateTime
     * @Author chengpunan
     * @Description  lushuaiyu
     * @Date 09:20 2019-09-07
     * @Param [dateToConvert]
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert){
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static void main(String[] args) {
        System.out.println(getDateHMSLong("2017-08-01 19:59:11"));
    }

}
