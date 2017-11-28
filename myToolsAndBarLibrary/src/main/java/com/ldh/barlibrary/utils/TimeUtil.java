package com.ldh.barlibrary.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    /**
     * 暂时格式化时间不调这个方法
     * 以秒的单位计算 个性时间显示 服务器端由于没有存储毫秒数
     *
     * @param timestamp 以秒为单位
     * @return
     */
    public static String converTime(long timestamp) {
        long currentSeconds = System.currentTimeMillis() / 1000L;
        long timeGap = currentSeconds - timestamp;// 与现在时间相差秒数
        String timeStr = null;
        if (timeGap > 24 * 60 * 60 * 30 * 365) {// 1年以上
            timeStr = timeGap / (24 * 60 * 60 * 30 * 365) + "年前";
        } else if (timeGap > 24 * 60 * 60 * 30) {// 30天以上
            timeStr = timeGap / (24 * 60 * 60 * 30) + "月前";
        } else if (timeGap > 24 * 60 * 60) {// 1-30天
            timeStr = timeGap / (24 * 60 * 60) + "天前";
        } else if (timeGap > 60 * 60) {// 1小时-24小时
            timeStr = timeGap / (60 * 60) + "小时前";
        } else if (timeGap > 60) {// 1分钟-59分钟
            timeStr = timeGap / 60 + "分钟前";
        } else {// 1秒钟-59秒钟
            timeStr = "1分钟前";
        }
        return timeStr;
    }

    /**
     * 倒计时代码
     *
     * @param startTime 开始时间,一个未来的时间,精度为毫秒
     * @return 返回0则为倒计时结束 显示格式hh:mm:ss
     */
    public static String timeCount(long startTime) {
        long endSecond = (startTime - System.currentTimeMillis()) / 1000L;
        //int d   =	(int)(endSecond/3600/24);
        //int h   =	(int)((endSecond/3600)%24);
        int h = (int) ((endSecond / 3600));
        int m = (int) ((endSecond / 60) % 60);
        int s = (int) (endSecond % 60);

        if (endSecond <= 0) return "0";

        return String.format("%02d:%02d:%02d", h, m, s);
    }


    /**
     * 格式化 当前时间
     *
     * @param form 格式字符串 可为空串
     * @return
     */
    public static String getCurrentDateTime(String form) {
        String localform = form;
        if ("".equals(localform)) {
            localform = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat time = new SimpleDateFormat(localform);
        return time.format(new Date());
    }

    /**
     * 格式化 当前时间
     *
     * @param form 格式字符串 可为空串
     * @return
     */
    public static long getCurrentDateTimeToLong(String form) {
        long time = 0;
        try {
            String localform = form;
            if ("".equals(localform)) {
                localform = "yyyy-MM-dd HH:mm:ss";
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat(localform);
            String timeStr = dateFormat.format(System.currentTimeMillis());
            Date date = dateFormat.parse(timeStr);
            time = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * @param dt   构造Date
     * @param form 格式化字符串 可为空串
     * @return
     */
    public static String dateTime2String(Date dt, String form) {
        String localform = form;
        if ("".equals(localform)) {
            localform = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat time = new SimpleDateFormat(localform);
        return time.format(dt);
    }

    /**
     * 时间戳转换格式化的时间
     *
     * @param ldt  传入值要是毫秒精度
     *             时间戳
     * @param form yyyy-MM-dd HH:mm:ss 格式化字符串 可为空
     * @return
     */
    public static String getTime2String(long ldt, String form) {
        String localform = form;
        if ("".equals(localform)) {
            localform = "yyyy-MM-dd HH:mm:ss";
        }
        Date dt = new Date(ldt);
        SimpleDateFormat time = new SimpleDateFormat(localform);
        return time.format(dt);
    }

    /**
     * 以字串形式的时间进行计算，增加或减少iHouer小时，返回日期时间字串
     *
     * @param dateString 2013-02-03
     * @param iHouer
     * @return
     */
    public static String stringDateTimePlus(String dateString, int iHouer) {
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设定格式
        dateFormat.setLenient(false);
        try {
            java.util.Date timeDate = dateFormat.parse(dateString);// util类型
            // Timestamp类型,timeDate.getTime()返回一个long型
            java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime() + (iHouer * 60 * 60 * 1000L));
            return dateTime.toString().substring(0, 19);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }

    /**
     * 以字串形式的时间进行计算，增加或减少iHouer小时，返回日期时间字串
     *
     * @param format 格式时间的类型
     * @param iHouer 在当前时间下所增加的小时
     * @return
     */
    public static String addCurDateToAHoursTime(String format, int iHouer) {
        return getTime2String(System.currentTimeMillis() + iHouer * 60 * 60 * 1000L, format);
    }

    /**
     * 字符串解析成Date对象
     *
     * @param dataString 要解析字符串
     * @param pattern    要解析的格式 如 yyyy年MM月dd
     * @return Date
     */
    public static Date ParseStringtoDate(String dataString, String pattern) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(pattern);// 设定格式
            try {
                return dateFormat.parse(dataString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 数字补零操作如:01
     *
     * @param obj
     * @return
     */
    public static String Timezerofill(Object obj) {
        String parten = "00";
        DecimalFormat decimal = new DecimalFormat(parten);
        return decimal.format(obj);
    }

    /**
     * 该方法时间戳精度到s
     * 5分钟之内，显示“刚刚”；
     * 今天之内，显示“今天 XX：XX”；
     * 昨天之内，显示“昨天 XX：XX”；
     * 昨天之前的时间段，显示具体日期“xx月XX日”；
     *
     * @param timestamp 以秒为单位
     * @return
     */
    public static String converFromSecondTime(long timestamp) {
        long cuurentMillis = System.currentTimeMillis();
        long currentSeconds = cuurentMillis / 1000;
        long timeGap = currentSeconds - timestamp;// 与现在时间相差秒数
        Calendar targetCalendar = Calendar.getInstance();
        //当前时间
        targetCalendar.setTime(new Date(cuurentMillis));
        int currentDay = targetCalendar.get(Calendar.DAY_OF_YEAR);
        int currentYear = targetCalendar.get(Calendar.YEAR);
        //传递进来的时间
        targetCalendar.setTime(new Date(timestamp * 1000));
        int targetDay = targetCalendar.get(Calendar.DAY_OF_YEAR);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        String timeStr = null;
        if (targetYear < currentYear) { //一年以上
            timeStr = getTime2String(timestamp * 1000, "yyyy年MM月dd日");
        } else if (currentDay - targetDay > 1) {// 2天以上
            timeStr = getTime2String(timestamp * 1000, "MM月dd日");
        } else if (currentDay - targetDay == 1) {
            timeStr = "昨天" + getTime2String(timestamp * 1000, "HH:mm");
        } else if (timeGap > 60 * 5) {// 5分钟之内以后
            timeStr = "今天" + getTime2String(timestamp * 1000, "HH:mm");
        } else {// 5分钟以内
            timeStr = "刚刚";
        }
        return timeStr;
    }


    /**
     * 获取时间  DayOfWeek字符串
     *
     * @param time
     * @param format
     * @return
     */
    public static String getDayOfWeek(long time, String format) {
        Calendar calendar = Calendar.getInstance();
        if (time == 0) {
            time = calendar.getTimeInMillis();
        } else {
            calendar.setTime(new Date(time));
        }
        int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        String dayOfWeek = "%s%s";
        if (TextUtils.isEmpty(format)) format = "星期";
        switch (day) {
            case 1:
                dayOfWeek = String.format(dayOfWeek, format, "一");
                break;

            case 2:
                dayOfWeek = String.format(dayOfWeek, format, "二");
                break;

            case 3:
                dayOfWeek = String.format(dayOfWeek, format, "三");
                break;

            case 4:
                dayOfWeek = String.format(dayOfWeek, format, "四");
                break;

            case 5:
                dayOfWeek = String.format(dayOfWeek, format, "五");
                break;

            case 6:
                dayOfWeek = String.format(dayOfWeek, format, "六");
                break;

            case 7:
                dayOfWeek = String.format(dayOfWeek, format, "日");
                break;

        }
        return dayOfWeek;
    }


    /**
     * 获取当月有多少天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getDayBumbByMonth(int year, int month) {
        switch (month) {
            case 1:
                return "31";
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    return "29";
                } else {
                    return "28";
                }
            case 3:
                return "31";
            case 4:
                return "30";
            case 5:
                return "31";
            case 6:
                return "30";
            case 7:
                return "31";
            case 8:
                return "31";
            case 9:
                return "30";
            case 10:
                return "31";
            case 11:
                return "30";
            case 12:
                return "31";
        }

        return null;
    }

}