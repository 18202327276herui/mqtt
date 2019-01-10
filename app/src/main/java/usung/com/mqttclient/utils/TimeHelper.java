package usung.com.mqttclient.utils;

import android.text.TextUtils;

import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期帮助类
 */
public class TimeHelper {

    public static final String FORMATTER = "yyyy-MM-dd HH:mm";
    public static final String FORMATTER_SHOT = "yyyy-MM-dd";
    public static final String FORMATTER_POINT = "yyyy.MM.dd";
    public static final String FORMATTER_SERVER = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String FORMATTER_FULL = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间日期格式化到年月.
     */
    public static final String FORMATTER_Y_M = "yyyy-MM";
    /**
     * 格式化到时分秒
     */
    public static final String FORMATTER_HOU_MIN_SEC = "HH:mm:ss";
    /**
     * 格式化到分秒
     */
    public static final String FORMATTER_MIN_SEC = "mm:ss";

    public static Date getBeiJingTime() {
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String ee = dff.format(new Date());
        return new Date();
    }


    /**
     * 从网络获取北京时间
     *
     * @return
     * @throws Exception
     */
    public static Date getBeiJingTimeForUrl() throws Exception {
        URL url = new URL("http://www.bjtime.cn");//取得资源对象
        URLConnection uc = url.openConnection();//生成连接对象
        uc.connect(); //发出连接
        long ld = uc.getDate(); //取得网站日期时间
        Date date = new Date(ld); //转换为标准时间对象
        //分别取得时间中的小时，分钟和秒，并输出
        System.out.print(date.getHours() + "时" + date.getMinutes() + "分" + date.getSeconds() + "秒");
        return date;
    }

    /**
     * 获取当前的北京时间
     */
    public static String getSystemTime() {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMATTER);
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 获取当前日期
     */
    public static String getDateTimeShot(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMATTER_SHOT);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取当前日期
     */
    public static String getDateTime_tomin(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMATTER);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取当前日期 yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }


    /**
     * 把字符串转化为日期格式
     *
     * @param strTime
     * @param formatType
     * @return
     */
    public static Date getStringToDate(String strTime, String formatType) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = simpleDateFormat.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取xx月xx日格式
     *
     * @param dateString 当前日期格式(如：2016-03-04)
     * @return xx月xx日
     */
    public static String getMDTimeFormat(String dateString) {
        String ret = "";
        String[] split = dateString.split("-");
        ret = split[1] + "月" + split[2] + "日";
        return ret;
    }

    /**
     * 获取当前日期
     */
    public static String getDateTime(String datetime, String formatString) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatString);
        try {
            Date date = formatter.parse(datetime);
            return formatter.format(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        } catch (NullPointerException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 转化日期格式
     */
    public static String getDateTime(String datetime, String parseString, String formatString) {
        SimpleDateFormat formatter = new SimpleDateFormat(parseString, Locale.getDefault());
        try {
            Date date = formatter.parse(datetime);
            formatter.applyPattern(formatString);
            return formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @param date
     * @param dataforment
     * @return
     */
    public static String formattingTime(Date date, String dataforment) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(dataforment);
            String dateString = formatter.format(date);
            return dateString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getDateByString(String datetime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMATTER_SHOT);
            Date date = sdf.parse(datetime);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通服务器返回的时间获取Date
     */
    public static Date getStringToDate(String datetime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMATTER_SERVER);
            Date date = sdf.parse(datetime);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化服务器带T的时间（年-月-日   时:分）
     *
     * @param time
     * @return
     */
    public static String formatServerTime(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        return time.substring(0, 16).replace("T", " ");
    }

    /**
     * 格式化服务器带T的时间（时:分）
     *
     * @param time
     * @return
     */
    public static String formatGetHourAndMinute(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        return time.substring(11, 16).replace("T", " ");
    }

    /**
     * 格式化服务器带T的时间（年-月-日   时:分:秒）
     *
     * @param time
     */
    public static String formatServerTimeAndSecond(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        return time.substring(0, 19).replace("T", " ");
    }

    /**
     * 格式化服务器带T的时间（只包括年月日）
     *
     * @param time
     */
    public static String formatServerTimeShot(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        return time.substring(0, 10).replace("T", " ");
    }

    /**
     * 格式化服务器带空格的时间（只包括年月日）
     *
     * @param time
     */
    public static String formatServerTimeBySpace(String time) {
        if (null == time || "null".equals(time)) {
            return "";
        }
        return time.split(" ")[0];
    }

    /**
     * 格式化服务器带T的时间（只包括月日）月/日
     *
     * @param time
     */
    public static String formatTimeMonthAndDay(String time) {
        if (null == time || "null".equals(time)) {
            return "";
        }
        String str = time.substring(5, 10).replace("T", " ");
        return str.replace("-", "/");
    }

    public static String formatTimeYearMonth(String time) {
        if (null == time || "null".equals(time) || time.equals("")) {
            return "";
        } else {
            return time.substring(0, 7).replace("-", ".");
        }
    }

    /**
     * 获取当前第几周
     */
    public static int getWeekOfYear() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(FORMATTER_SHOT);
            String _week = formatter.format(new Date());
            Calendar cal = Calendar.getInstance();
            // 这一句必须要设置，否则美国认为第一天是周日，而我国认为是周一，对计算当期日期是第几周会有错误
            cal.setFirstDayOfWeek(Calendar.MONDAY); // 设置每周的第一天为星期一
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 每周从周一开始
            cal.setMinimalDaysInFirstWeek(1); // 设置每周最少为1天
            cal.setTime(formatter.parse(_week));
            return cal.get(Calendar.WEEK_OF_YEAR);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return 0;
    }

    /**
     * 获取当前日期是星期几
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) w = 0;
        return weekDays[w];
    }

    /**
     * 获取当前日期是星期几
     */
    public static String getWeekFromDate(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) w = 0;
        return weekDays[w];
    }

    /**
     * 获取某个日期日期加上几天
     */
    public static String getDateTime(String datetime, String formatString, int i) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        Date date = null;
        try {
            date = sdf.parse(datetime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, i);
        String dateString = sdf.format(calendar.getTime());
        return dateString;
    }

    /**
     * 获取某个日期日期加上几天
     */
    public static String getDateTime(Date date, String formatString, int i) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, i);
        String dateString = sdf.format(calendar.getTime());
        return dateString;
    }

    /**
     * 获取某个日期加上几天
     */
    public static Date getDate(String datetime, int i) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATTER_SHOT);
        Date date = null;
        try {
            date = sdf.parse(datetime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, i);
        return calendar.getTime();
    }


    /**
     * 根据传日的时间字符串返回今天，明天，后天，星期几
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static String getTitleString(String startTime, String endTime) {
        try {

            DateFormat df = new SimpleDateFormat(FORMATTER_SHOT);
            Date startDate = df.parse(startTime);
            Date endDate = df.parse(endTime);
            if (startDate.after(endDate)) {
                Date temp_dateDate = new Date();
                temp_dateDate = startDate;
                startDate = endDate;
                endDate = temp_dateDate;
            }
            Calendar today = Calendar.getInstance();
            Calendar old = Calendar.getInstance();
            today.setTime(startDate);
            today.set(Calendar.HOUR, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            old.setTime(endDate);
            old.set(Calendar.HOUR, 0);
            old.set(Calendar.MINUTE, 0);
            old.set(Calendar.SECOND, 0);
            int t = (int) ((old.getTimeInMillis() - today.getTimeInMillis()) / (24 * 60 * 60 *
                    1000));
            if (t == 0) {
                return "今天";
            } else if (t == 1) {
                return "明天";
            } else if (t == 2) {
                return "后天";
            } else {
                return getWeekOfDate(new SimpleDateFormat(FORMATTER_SHOT).parse(endTime));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据传日的时间字符串返回今天，明天，后天，星期几
     *
     * @param endTime
     */
    public static String getTitleString(String endTime) {
        DateFormat df = new SimpleDateFormat(FORMATTER_SHOT);
        String startTime = df.format(new Date());
        return getTitleString(startTime, endTime);
    }

    /**
     * 获取指定日期的后一天
     *
     * @param specifiedDay
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(FORMATTER_SHOT).parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat(FORMATTER_SHOT).format(c.getTime());
        return dayAfter;
    }

    /**
     * 获取最早到店时间 时间格式2013-12-28T19:00:00+08:00
     *
     * @param latestArrivalTime 最晚到店时间
     */
    public static String getEarliestArrivalTime(String latestArrivalTime) {
        int hour = Integer.parseInt(latestArrivalTime.substring(latestArrivalTime.indexOf("T") +
                1, latestArrivalTime.indexOf("T") + 3));
        if (hour == 6) {
            return latestArrivalTime.substring(0, latestArrivalTime.indexOf("T")) + "T" +
                    "23:59:00+08:00";
        } else {
            return latestArrivalTime.substring(0, latestArrivalTime.indexOf("T")) + "T" + (hour -
                    3) + ":00:00+08:00";
        }
    }

    /**
     * 比较时间
     *
     * @param time1 yyyy-MM-dd HH:mm:ss
     * @param time2 yyyy-MM-dd HH:mm:ss
     * @return =0,time1=time2;   |||    <0,time1<time2;  |||  >0,time1>time2
     */
    public static int compareTime(String time1, String time2) {
        DateFormat df = new SimpleDateFormat(FORMATTER);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(time1));
            c2.setTime(df.parse(time2));
        } catch (ParseException e) {
            System.err.println("格式不正确");
        }
        int kk = c1.compareTo(c2);
        return c1.compareTo(c2);
    }


    /**
     * 计算两个时间点的时间差 （返回天、时、分、秒）
     *
     * @param time1 结束时间（时间字符串）
     * @param time2 开始时间（时间字符串）
     * @throws ParseException
     */
    public static String getTimeDifferenceNum(String time1, String time2) {

        SimpleDateFormat sdf = new SimpleDateFormat(FORMATTER);

        // 将截取到的时间字符串转化为时间格式的字符串
        Date beginTime = null, overTime = null;
        long day = 0, hour = 0, minute = 0, second = 0;
        try {
            beginTime = sdf.parse(time2); // 开始时间
            overTime = sdf.parse(time1); // 结束时间

            // 默认为毫秒，除以1000是为了转换成秒
            long interval = (overTime.getTime() - beginTime.getTime()) / 1000; // 两个时间总共相差的秒数

            day = interval / (24 * 3600); // 天
            hour = interval % (24 * 3600) / 3600; // 小时
            minute = interval % 3600 / 60; // 分钟
            second = interval % 60; // 秒
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return (day + "天" + hour + "小时" + minute + "分");
    }


    /**
     * 计算两个时间差（返回小时数）
     *
     * @param time1 结束时间（时间字符串）
     * @param time2 开始时间（时间字符串）
     * @return
     */
    public static String getTimeDifferenceHourNum(String time1, String time2) {
        String timeHourNum = "";
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATTER);
        try {
            Date beginTime = sdf.parse(time2); // 开始时间
            Date overTime = sdf.parse(time1); // 结束时间
            long diff = overTime.getTime() - beginTime.getTime(); //  这样得到的差值是毫秒级别

            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) /
                    (1000 * 60);

            System.out.println("" + days + "天" + hours + "小时" + minutes + "分");

            timeHourNum = String.valueOf(diff / (1000 * 60 * 60));
        } catch (Exception e) {
        }
        return timeHourNum;
    }

    /**
     * 计算两个时间差（返回分钟数）
     *
     * @param time1 结束时间（时间字符串）
     * @param time2 开始时间（时间字符串）
     * @return
     */
    public static String getTimeDifferenceMiniteNum(String time1, String time2) {
        String timeMiniteNum = "";
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATTER);
        try {
            Date beginTime = sdf.parse(time2); // 开始时间
            Date overTime = sdf.parse(time1); // 结束时间
            long diff = overTime.getTime() - beginTime.getTime(); // 这样得到的差值是毫秒级别

            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) /
                    (1000 * 60);

            System.out.println("" + days + "天" + hours + "小时" + minutes + "分");

            timeMiniteNum = String.valueOf(diff / (1000 * 60));
        } catch (Exception e) {
        }
        return timeMiniteNum;
    }

    /**
     * 计算两个时间差（返回秒数）
     *
     * @param time1 结束时间（时间字符串）
     * @param time2 开始时间（时间字符串）
     * @return
     */
    public static String getTimeDifferenceSecondsNum(String time1, String time2) {
        String timeMinitesNum = "";
        String timeSecondsNum = "";
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATTER);
        try {
            Date beginTime = sdf.parse(time2); // 开始时间
            Date overTime = sdf.parse(time1); // 结束时间
            long diff = overTime.getTime() - beginTime.getTime(); // 这样得到的差值是毫秒级别

            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) /
                    (1000 * 60);
            long seconds = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) -
                    minutes * (1000 * 60)) / (1000);

            System.out.println("" + days + "天" + hours + "小时" + minutes + "分" + seconds + "秒");

            timeMinitesNum = String.valueOf(diff / (1000 * 60));
            timeSecondsNum = String.valueOf(diff / 1000);
        } catch (Exception e) {
        }
        return timeSecondsNum;
    }

    /**
     * 计算两个时间差（返回天数）
     * 计算两个时间点之间的天数
     * （第二个参数传入较大的时间）
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 天数
     */
    public static long getDaysBetween2Date(Date date1, Date date2) {
        long quot;
        if (date1 != null && date2 != null) {
            quot = date2.getTime() - date1.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
            return quot;
        }
        return 0;
    }

    /**
     * 计算两个时间差（返回天数）
     * 计算两个时间点之间的天数
     *
     * @param date1 时间字符串1
     * @param date2 时间字符串2
     * @return
     */
    public static long getDaysBetween2Date(String date1, String date2) {
        long quot;
        Date tempDate1 = getDateByString(date1);
        Date tempDate2 = getDateByString(date2);
        if (tempDate1 != null && tempDate2 != null) {
            quot = tempDate2.getTime() - tempDate1.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
            return Math.abs(quot);
        }
        return 0;
    }

    /**
     * 2016/05/11/huwenbin/获取几个月的今天的日期
     *
     * @param numOfMonth 想要获取几个月前的今天,这个参数输入就可以了
     * @param isForDay   是否需要获取几个月后的今天的昨天
     * @return
     */
    public static String getDateSeveralMonthToday(int numOfMonth, boolean isForDay, boolean
            isZero) {
        Calendar c = Calendar.getInstance();
        int mCurrentDay = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, mCurrentDay + numOfMonth);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        if (isForDay) {
            if (day != 1) {
                day--;
            } else {
                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month
                        == 10 || month == 12) {
                    day = 31;
                } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                    day = 30;
                } else if (month == 2) {
                    if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                        day = 29;
                    } else {
                        day = 28;
                    }
                }
            }
        }

        //如果月份只有一位数的话就在前面加个0
        String mMonth = "";
        if (isZero) {
            mMonth = String.valueOf(month);
            if (mMonth.length() == 1) {
                mMonth = "0" + mMonth;
            }
        }
        return year + "-" + mMonth + "-" + day;
    }

    /**
     * 处理时间显示样式的
     *
     * @param rawDate 联网获取的时间
     * @return 去除符号T，去除：ss
     */
    public static String disposeFormatDate(String rawDate) {
        if (rawDate.length() > 16) {
            rawDate = rawDate.substring(0, 16);
        }
        return rawDate.replace("T", " ");
    }

    /**
     * 计算两个时间差（返回小时和分钟数）
     *
     * @param endDate   结束时间（时间字符串）
     * @param beginDate 开始时间（时间字符串）
     * @return
     */
    public static String getTimeHourMinutes(String endDate, String beginDate) {
        String time = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date beginTime = sdf.parse(beginDate);
            Date overTime = sdf.parse(endDate);
            long diff = overTime.getTime() - beginTime.getTime(); //  这样得到的差值是毫秒级别

            long hours = diff / (1000 * 60 * 60);
            long minutes = (diff - hours * (1000 * 60 * 60)) / (1000 * 60);

            time = String.valueOf(hours) + "小时" + minutes + "分";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 计算两个时间差（返回小时和分钟数）
     *
     * @param time1 结束时间（时间字符串）
     * @param time2 开始时间（时间字符串）
     * @return
     */
    public static long getTimeLong(String time1, String time2) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long diff = 0;
        try {
            Date date_Time1 = sdf.parse(time1);
            Date date_Time2 = sdf.parse(time2);
            diff = date_Time1.getTime() - date_Time2.getTime(); //  这样得到的差值是毫秒级别
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return diff;
    }

    /**
     * 判断是否是当天时间
     *
     * @param t 时间戳
     * @return
     */
    public static boolean timeIsToday(String t) {
        boolean isToday = false;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String today = simpleDateFormat.format(date);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        String time = null;
        try {
            time = simpleDateFormat.format(df.parse(t));
            if (!TextUtils.isEmpty(time) && !TextUtils.isEmpty(today)
                    && today.equals(time)) {
                isToday = true;
            } else {
                isToday = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return isToday;
    }


    /**
     * 取指定日期为星期几.
     *
     * @param strDate  指定日期
     * @param inFormat 指定日期格式
     * @return String   星期几
     */
    public static Integer getWeek(String strDate, String inFormat) {
        Calendar calendar = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat(inFormat);
        try {
            calendar.setTime(df.parse(strDate));
        } catch (Exception e) {
            return 7;
        }
        int intTemp = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return intTemp;
    }

    /**
     * 取指定日期为星期几.
     */
    public static Integer getWeek(Date date) {
        Calendar calendar = new GregorianCalendar();
        try {
            calendar.setTime(date);
        } catch (Exception e) {
            return 0;
        }
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 取得指定年月的当月总天数
     *
     * @return 当月总天数
     */
    public static int getLastDay(String strDate, String inFormat) {
        Calendar calendar = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat(inFormat);
        try {
            calendar.setTime(df.parse(strDate));
        } catch (Exception e) {
            return 7;
        }
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);
        int last = calendar.getActualMaximum(Calendar.DATE);
        return last;
    }

    /**
     * 判断两个日期是否同一天
     */
    public static boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATTER_SHOT, Locale.getDefault());
        String dateStr1, dateStr2;
        try {
            dateStr1 = sdf.format(date1);
            dateStr2 = sdf.format(date2);
        } catch (Exception e) {
            return false;
        }
        return dateStr1.equals(dateStr2);
    }

    /**
     * 获取指定日期所在一周的首尾日期
     *
     * @param targetDate 指定日期
     * @return 长度为2，第一个是星期一的日期，第二个是星期天的日期
     */
    public static String[] getWeekHeadTail(Date targetDate) {
        String[] arr = new String[2];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(targetDate);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 0) {
            calendar.add(Calendar.DAY_OF_MONTH, -6);
        } else {
            calendar.add(Calendar.DAY_OF_MONTH, -week + 1);
        }
        arr[0] = formattingTime(calendar.getTime(), FORMATTER_SHOT);
        calendar.add(Calendar.DAY_OF_MONTH, 6);
        arr[1] = formattingTime(calendar.getTime(), FORMATTER_SHOT);
        return arr;
    }

    /**
     * 获取指定日期所在一月的首尾日期
     *
     * @param targetDate 指定日期
     * @return 长度为2，第一个是第一天的日期，第二个是最后一天的日期
     */
    public static String[] getMonthHeadTail(Date targetDate) {
        String[] arr = new String[2];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(targetDate);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        calendar.add(Calendar.DAY_OF_MONTH, -day + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        arr[0] = formattingTime(calendar.getTime(), FORMATTER_SHOT);
        calendar.add(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 1);
        arr[1] = formattingTime(calendar.getTime(), FORMATTER_SHOT);
        return arr;
    }

    /**
     * 获取指定日期所在一月的首尾日期（当月第一天00:00:00 - 当月最后一天23:59:59）
     *
     * @param targetDate 指定日期
     * @return 长度为2，第一个是第一天的日期，第二个是最后一天的日期
     */
    public static String[] getMonthHeadTailServer(Date targetDate, String formatter) {
        String[] arr = new String[2];
        Calendar calendar = Calendar.getInstance();
        targetDate = getDateByString(formattingTime(targetDate, FORMATTER_SHOT));
        calendar.setTime(targetDate);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        calendar.add(Calendar.DAY_OF_MONTH, -day + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        arr[0] = formattingTime(calendar.getTime(), formatter);
        calendar.add(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.add(Calendar.SECOND, -1);
        arr[1] = formattingTime(calendar.getTime(), formatter);
        return arr;
    }

    /**
     * 获取指定日期的过去一个月的首尾日期
     *
     * @param targetDate 指定日期
     * @return 长度为2，第一个是指定日期一个月前的日期，第二个是指定日期
     */
    public static Date[] getLastMonthDate(Date targetDate) {
        Date[] arr = new Date[2];
        Calendar cal = Calendar.getInstance();
        cal.setTime(TimeHelper.getDateByString(TimeHelper.getDateTimeShot(targetDate)));
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.SECOND, -1);
        arr[1] = cal.getTime();
        cal.add(Calendar.MONTH, -1);
        arr[0] = cal.getTime();
        return arr;
    }

    /**
     * 获取指定日期的过去一个月的首尾日期
     *
     * @param targetDate 指定日期
     * @param formatter  返回格式
     * @return 长度为2，第一个是指定日期一个月前的日期，第二个是指定日期
     */
    public static String[] getLastMonthDay(Date targetDate, String formatter) {
        String[] arr = new String[2];
        Date[] dates = getLastMonthDate(targetDate);
        arr[0] = formattingTime(dates[0], formatter);
        arr[1] = formattingTime(dates[1], formatter);
        return arr;
    }


    /**
     * 获取指定日期所在月份的整月日期集合
     *
     * @return 日期格式为yyyy-MM-dd
     */
    public static ArrayList<String> getMonthDays(Date targetDate) {
        ArrayList<String> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(targetDate);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int maxLength = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < maxLength; i++) {
            list.add(getDateTimeShot(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return list;
    }

    /**
     * 获取指定日期所在月份的整月工作日集合
     *
     * @return 日期格式为yyyy-MM-dd
     */
    public static ArrayList<String> getMonthWorkDays(Date targetDate) {
        ArrayList<String> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(targetDate);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int maxLength = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < maxLength; i++) {
            int week = calendar.get(Calendar.DAY_OF_WEEK);
            if (week != Calendar.SATURDAY && week != Calendar.SUNDAY) {
                list.add(getDateTimeShot(calendar.getTime()));
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return list;
    }

    /**
     * 返回指定月份的工作日
     * <p>
     * //     * @param targetDate 指定月份
     * //     * @param callback   回调，不能为null
     */
    /*public static void getMonthWorkDays(final Context context, final Date targetDate, final GetWorkDaysCallback callback) {
        final String month = formattingTime(targetDate, FORMATTER_Y_M);
        LoadingDialog.getInstance(context).show();
        OkHttpUtils.get().url(APIConstant.GetHoliday)
                .addParams("m", month)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                LoadingDialog.getInstance(context).dismiss();
                ToastUtil.showToast(R.string.http_failure);
            }

            @Override
            public void onResponse(String response) {
                LoadingDialog.getInstance(context).dismiss();
                ArrayList<String> list = getMonthDays(targetDate);
                Map<String, Map<String, String>> map = GsonHelper.getGson().fromJson(response, new TypeToken<Map<String, Map<String, String>>>() {
                }.getType());
                if (map != null) {
                    Map<String, String> days = map.get(formattingTime(targetDate, "yyyyMM"));
                    for (String day : days.keySet()) {
                        String curDate = month + "-" + day;
                        if (list.contains(curDate)) {
                            list.remove(curDate);
                        }
                    }
                }
                callback.onGetWorkDays(list);
            }
        });
    }*/

    public interface GetWorkDaysCallback {
        void onGetWorkDays(ArrayList<String> days);
    }

    /**
     * 计算时间返回yyyy-MM-dd HH:mm:ss格式（目前只支持小于24小时）
     * 注：若小于一定阶段则前面的格式是空，如传入3600000则返回01:00:00，而不会带年月日
     *
     * @param timeMillis 毫秒数
     */
    public static String calcTime(long timeMillis) {
        int time = (int) (timeMillis / 1000);
        String date = "";
        if (time < 60) {
            //ss格式
            date = time < 10 ? "0" + time : "" + time;
        } else if (time < 60 * 60) {
            //mm:ss格式
            int min = time / 60;
            int sec = time - min * 60;
            date = (min < 10 ? "0" + min : "" + min) + ":" + (sec < 10 ? "0" + sec : "" + sec);
        } else if (time < 24 * 60 * 60) {
            //HH:mm:ss格式
            int hour = time / 60 / 60;
            int min = (time - hour * 60 * 60) / 60;
            int sec = time - hour * 60 * 60 - min * 60;
            date = (hour < 10 ? "0" + hour : "" + hour) + ":" + (min < 10 ? "0" + min : "" + min) + ":" + (sec < 10 ? "0" + sec : "" + sec);
        }
        //TODO 待续
        return date;
    }

    /**
     * 获取一个月前的日期
     *
     * @param date 传入的日期
     * @return
     */
    public static String getMonthAgoByDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMATTER_SHOT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        String monthAgo = simpleDateFormat.format(calendar.getTime());
        return monthAgo;
    }

    /**
     * 获取当前日期的一个月前的日期
     *
     * @return
     */
    public static String getMonthAgo(String formatter) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.add(Calendar.MONTH, -1);
        String monthAgo = simpleDateFormat.format(calendar.getTime());
        return monthAgo;
    }
}
