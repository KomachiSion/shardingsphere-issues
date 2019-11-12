package komachi.sion.shard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private static String ymDateFormat = "yyyy-MM";
    
    private static String ymDateFormatCn = "yyyy年MM月";
    
    private static String ymdDateFormat = "yyyy-MM-dd";
    
    private static String ymdDateFormatCn = "yyyy年MM月dd日";
    
    private static String fullDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    
    private static String fullDateTimeFormatCn = "yyyy年MM月dd HH时mm分ss秒";
    
    private static String timeFormat = "HH:mm:ss";
    
    private static String timeFormatCn = "HH时mm分ss秒";
    
    private static final String ALTERNATIVE_ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    
    private static final String YYMMDDHHMMSSS_FORMAT = "yyMMddhhmmsss";
    
    private static final String YEAR_FORMAT = "yyyy";
    
    /**
     * 将时间格式字符串 解析成 时间对象
     *
     * @param dateStr    时间字符串
     * @param dateFormat 解析的 字符串格式
     * @return
     */
    public static Date valueDate(String dateStr, String dateFormat) {
        try {
            return new SimpleDateFormat(dateFormat).parse(dateStr);
        } catch (ParseException e) {
            System.out.println("时间格式解析出错" + e);
        }
        return null;
    }
    
    /**
     * 将时间格式话指定字符串 格式
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static String formatDate(Date date, String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(date);
    }
    
    /**
     * 将时间格式化指定字符串
     *
     * @param dateStr
     * @param dateFormat
     * @return
     */
    public static String formatDate(String dateStr, String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(dateStr);
    }
    
    /**
     * 获取 日历常量  字段值
     *
     * @param date
     * @param field
     * @return
     */
    public static int getField(Date date, int field) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.clear();
        calendar.setTime(date);
        return calendar.get(field);
    }
    
    /**
     * 连续设置字段 值
     *
     * @param calendar
     * @param value
     * @param field
     * @return
     */
    public static Calendar setField(Calendar calendar, int value, int field) {
        calendar.set(field, value);
        return calendar;
    }
    
    /**
     * 将  格式 yyyy-MM 字符串 解析成 时间对象
     *
     * @param dateString yyyy-MM
     * @return
     */
    public static Date valueYMDate(String dateString) {
        return valueDate(dateString, ymDateFormat);
    }
    
    /**
     * @param dateString yyyy年MM月
     * @return
     */
    public static Date valueYMDateCn(String dateString) {
        return valueDate(dateString, ymDateFormatCn);
    }
    
    /**
     * @param dateString yyyy-MM-dd
     * @return
     */
    public static Date valueYMdDate(String dateString) {
        return valueDate(dateString, ymdDateFormat);
    }
    
    /**
     * @param dateString yyyy年MM月dd日
     * @return
     */
    public static Date valueYMdDateCn(String dateString) {
        return valueDate(dateString, ymdDateFormatCn);
    }
    
    /**
     * @param dateStr yyyy-MM-dd'T'HH:mm:ss'Z'
     * @return
     */
    public static Date valueISO8601Date(String dateStr) {
        return valueDate(dateStr, ALTERNATIVE_ISO8601_DATE_FORMAT);
    }
    
    /**
     * @param dateTimeString yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date valueFullDateTime(String dateTimeString) {
        return valueDate(dateTimeString, fullDateTimeFormat);
    }
    
    /**
     * @param dateTimeString yyyy年MM月dd HH时mm分ss秒
     * @return
     */
    public static Date valueFullDateTimeCn(String dateTimeString) {
        return valueDate(dateTimeString, fullDateTimeFormatCn);
    }
    
    /**
     * @param timeString "hh:mm:ss"
     * @return
     */
    public static Date valueTime(String timeString) {
        return valueDate(timeString, timeFormat);
    }
    
    /**
     * @param timeString "hh:mm:ss"
     * @return
     */
    public static Date valueTimeCn(String timeString) {
        return valueDate(timeString, timeFormatCn);
    }
    
    /**
     * @param date yyyy-MM-dd'T'HH:mm:ss'Z'
     * @return
     */
    public static String formatISO8601Date(Date date) {
        return formatDate(date, ALTERNATIVE_ISO8601_DATE_FORMAT);
    }
    
    /**
     * @param date yyyy-MM
     * @return
     */
    public static String formatYMDate(Date date) {
        return formatDate(date, ymDateFormat);
    }
    
    /**
     * @param date yyyy年MM月
     * @return
     */
    public static String formatYMDateCn(Date date) {
        return formatDate(date, ymDateFormatCn);
    }
    
    /**
     * @param date yyyy-MM-dd
     * @return
     */
    public static String formatYMDDate(Date date) {
        return formatDate(date, ymdDateFormat);
    }
    
    /**
     * 返回 yyyy-MM-dd字符串
     *
     * @param dateStr yyyy-MM-dd
     * @return
     */
    public static String formatYMDDate(String dateStr) {
        return formatDate(dateStr, ymdDateFormat);
    }
    
    /**
     * @param date yyyy年MM月dd日
     * @return
     */
    public static String formatYMDDateCn(Date date) {
        return formatDate(date, ymdDateFormatCn);
    }
    
    /**
     * @param date yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, fullDateTimeFormat);
    }
    
    /**
     * @param date yyyy年MM月dd HH时mm分ss秒
     * @return
     */
    public static String formatDateTimeCn(Date date) {
        return formatDate(date, fullDateTimeFormatCn);
    }
    
    /**
     * @param date HH:mm:ss
     * @return
     */
    public static String formatTime(Date date) {
        return formatDate(date, timeFormat);
    }
    
    /**
     * @param date HH时mm分ss秒
     * @return
     */
    public static String formatTimeCn(Date date) {
        return formatDate(date, timeFormatCn);
    }
    
    /**
     * 获取星期几
     *
     * @param date
     * @return
     */
    public static int weekday(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.clear();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    
    /**
     * 判断是是否是工作日
     *
     * @param date true 工作日
     * @return
     */
    public static boolean isWorkDay(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.clear();
        calendar.setTime(date);
        Integer day = calendar.get(Calendar.DAY_OF_WEEK);
        return day >= 2 && day <= 6;
    }
    
    /**
     * 是否是星期 六
     *
     * @param date
     * @return 如果是返回true
     */
    public static boolean isSaturday(Date date) {
        return weekday(date) == 7;
    }
    
    /**
     * 是否是星期 日
     *
     * @param date
     * @return 如果是返回true
     */
    public static boolean isSunday(Date date) {
        return weekday(date) == 1;
    }
    
    /**
     * 是否是星期天
     *
     * @param date
     * @return 如果是返回true
     */
    public static boolean isWeekend(Date date) {
        return (isSaturday(date)) || (isSunday(date));
    }
    
    /**
     * 获取年数
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.clear();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
    
    /**
     * 获取月数
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.clear();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }
    
    /**
     * 获取天数
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.clear();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * 添加月数
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date changeDate(Date date, int offset, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(field, calendar.get(field) + offset);
        return calendar.getTime();
    }
    
    /**
     * 添加月数
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date addMonths(Date date, int offset) {
        return changeDate(date, offset, Calendar.MONTH);
    }
    
    /**
     * 添加天数
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date addDays(Date date, int offset) {
        return changeDate(date, offset, Calendar.DAY_OF_MONTH);
    }
    
    /**
     * 添加小时数
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date addHours(Date date, int offset) {
        return changeDate(date, offset, Calendar.HOUR_OF_DAY);
    }
    
    /**
     * 添加分钟数
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date addMinetes(Date date, int offset) {
        return changeDate(date, offset, Calendar.MINUTE);
    }
    
    /**
     * 添加秒数
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date addSeconds(Date date, int offset) {
        return changeDate(date, offset, Calendar.SECOND);
    }
    
    /**
     * 缩减天数
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date reduceDays(Date date, int offset) {
        return changeDate(date, -offset, Calendar.DAY_OF_MONTH);
    }
    
    /**
     * 缩减小时
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date reduceHours(Date date, int offset) {
        return changeDate(date, -offset, Calendar.HOUR_OF_DAY);
    }
    
    /**
     * 缩减分钟
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date reduceMinute(Date date, int offset) {
        return changeDate(date, -offset, Calendar.MINUTE);
    }
    
    /**
     * 缩减秒数
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date reduceSeconds(Date date, int offset) {
        return changeDate(date, -offset, Calendar.SECOND);
    }
    
    /**
     * 缩减月数
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date reduceMonth(Date date, int offset) {
        return changeDate(date, -offset, Calendar.MONTH);
    }
    
    /**
     * 获取分钟数
     *
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.clear();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }
    
    /**
     * 获取小时数
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.clear();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    
    /**
     * 获取秒数
     *
     * @param date
     * @return
     */
    public static int getSeconds(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.clear();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }
    
    /**
     * 获取 2个时间的年参数差
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getYearsBetween(Date startDate, Date endDate) {
        Calendar end = Calendar.getInstance(Locale.CHINA);
        end.clear();
        end.setTime(endDate);
        int endYear = end.get(Calendar.YEAR);
        Calendar start = Calendar.getInstance(Locale.CHINA);
        start.clear();
        start.setTime(startDate);
        int startYear = start.get(Calendar.YEAR);
        return endYear - startYear;
    }
    
    /**
     * 获取 2个时间的月参数差
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getMonthsBetween(Date startDate, Date endDate) {
        Calendar end = Calendar.getInstance(Locale.CHINA);
        end.clear();
        end.setTime(endDate);
        int endYear = end.get(Calendar.YEAR);
        int endMonth = end.get(Calendar.MONTH);
        Calendar start = Calendar.getInstance(Locale.CHINA);
        start.clear();
        start.setTime(startDate);
        int startYear = start.get(Calendar.YEAR);
        int startMonth = start.get(Calendar.MONTH);
        return (endYear * 12 + endMonth) - (startYear * 12 + startMonth);
    }
    
    /**
     * 获取 2个时间的天参数差
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long getDaysBetween(Date startDate, Date endDate) {
        return Long.valueOf((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000L));
    }
    
    /**
     * 获取相差得 小时数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long getHoursBetween(Date startDate, Date endDate) {
        return Long.valueOf((endDate.getTime() - startDate.getTime()) / (60 * 60 * 1000L));
    }
    
    /**
     * 获取相差得 分钟数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long getMinutessBetween(Date startDate, Date endDate) {
        return Long.valueOf((endDate.getTime() - startDate.getTime()) / (60 * 1000L));
    }
    
    /**
     * 获取相差得 小时数
     *
     * @param starth
     * @param endh
     * @return
     */
    public static int getHoursBetween(int starth, int endh) {
        if (endh < starth) {
            return 24 + endh - starth;
        }
        return endh - starth;
    }
    
    /**
     * 获取相差得 分钟数
     *
     * @param startm
     * @param endm
     * @return
     */
    public static int getMinutessBetween(int startm, int endm) {
        if (endm < startm) {
            return 60 + endm - startm;
        }
        return endm - startm;
    }
    
    /**
     * 获取当月第一天
     */
    public static String getMonthFirst(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        String monthStart = formatYMDDate(c.getTime());
        return monthStart;
    }
    
    /**
     * 获取当月最后一天
     */
    public static String getMonthLast(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        String monthLast = formatYMDDate(c.getTime());
        return monthLast;
    }
    
    /**
     * 获取当周第一天
     */
    public static String getWeekFirst(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        String weekStart = formatYMDate(c.getTime());
        return weekStart;
    }
    
    /**
     * 获取当周最后一天
     */
    public static String getWeekLast(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
        String weekEnd = formatYMDate(c.getTime());
        return weekEnd;
    }
    
    /**
     * 获取当前时间毫秒数
     *
     * @return
     */
    public static Long getCurrentDateMillisecond() {
        return System.currentTimeMillis();
    }
    
    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }
    
    /**
     * 是否在时间段内
     */
    public static boolean inTheTimePeriod(Date startTime, Date endTime, Date tempTime) {
        return (tempTime.getTime() >= startTime.getTime() && endTime.getTime() >= tempTime.getTime());
    }
    
    /**
     * 获取yyMMddhhmmsss格式的日期字符串
     *
     * @return
     */
    public static String getymdhmsFormat() {
        return formatDate(new Date(System.currentTimeMillis()), YYMMDDHHMMSSS_FORMAT);
    }
    
    /**
     * 获取年
     *
     * @return "yyyy"年
     */
    public static String getYear() {
        return formatDate(new Date(System.currentTimeMillis()), YEAR_FORMAT);
    }
}
