package com.nado.rlzy;

import com.nado.rlzy.base.BaseTest;
import lombok.var;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @ClassName 有关时间类的操作
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019/7/25 15:58
 * @Version 1.0
 */
public class DateTimeTest extends BaseTest {
    @Test
    public void test1() {

        //当前未格式化的系统时间
        var dateTime = LocalDateTime.now();
        System.out.println(dateTime);

        //构造指定日期时间
        var date = LocalDateTime.of(2017, 7, 7, 12, 22, 35);
        System.out.println(date);
        //向后推两天
        System.out.println(date.plusDays(2));

        //向后推6个月
        System.out.println(date.plusMonths(6));

        //向后推2h
        System.out.println(date.minusHours(2));

        //得到小时
        System.out.println(date.getHour());
        // 时间比较
        var date1 = LocalDateTime.of(2017, 7, 20, 16, 17);
        var date2 = LocalDateTime.of(2017, 6, 20, 16, 17);
        System.out.println(date1.isAfter(date2));
    }

    @Test
    public void testInstant() {
        // UTC 时区
        var now = Instant.now();
        System.out.println(now);

        // 毫秒数
        System.out.println(now.toEpochMilli());

        var offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
    }

    @Test
    public void testDuration() {
        // 时间间隔
        var now = Instant.now();
        var last = Instant.ofEpochMilli(0);
        var duration = Duration.between(now, last);
        System.out.println(duration);
        System.out.println(duration.toDays());

        var prev = LocalTime.of(12, 22);
        var curr = LocalTime.of(14, 23);
        var duration2 = Duration.between(prev, curr);
        System.out.println(duration2);
        System.out.println(duration2.toMinutes());
    }

    @Test
    public void testPeriod() {
        // 日期间隔
        var prev = LocalDate.of(2017, 07, 07);
        var curr = LocalDate.of(2017, 07, 10);
        var period = Period.between(prev, curr);
        System.out.println(period);
        System.out.println(period.getDays());
    }

    @Test
    public void test3() {
        var yearMonth = YearMonth.of(2017, 7);
        System.out.println(yearMonth + " 有 " + yearMonth.lengthOfMonth() + " 天");
        System.out.println(yearMonth.getYear() + "年有" + yearMonth.lengthOfYear() + "天");
        System.out.println(yearMonth.getYear() + " isLeapYear: " + yearMonth.isLeapYear());

        yearMonth = YearMonth.of(2017, 2);
        // 测试29号在2017-02是不是合法的一天
        System.out.println(yearMonth.isValidDay(29));
    }


    @Test
    public void test4() {
        var time = LocalDateTime.now();
        //当月第七天
        var date1 = time.withDayOfMonth(7);
        System.out.println(date1);

        //当年第七天
        var date2 = time.withDayOfYear(7);
        System.out.println(date2);

        //下周三
        var date3 = time.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        System.out.println(date3);

        // 上周一
        LocalDateTime date4 = time.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        System.out.println(date4);

        // 本月最后一个周日
        LocalDateTime date5 = time.with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
        System.out.println(date5);

        // 本月第一个周日
        LocalDateTime date6 = time.with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
        System.out.println(date6);

        // -------------------------------

        LocalDateTime now = LocalDateTime.now();

        // 下周的今天
        LocalDateTime nextWeekToday = now.plus(1, ChronoUnit.WEEKS);
        System.out.println(nextWeekToday);

        // 上周的今天
        LocalDateTime prevWeekToday = now.minus(1, ChronoUnit.WEEKS);
        System.out.println(prevWeekToday);
    }
}