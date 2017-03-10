package miaoyongjun.autil.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtils {

    private static final String TAG = "DateUtil";

    /**
     * 中文版
     */
    private final static String dayNamesCN[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    /**
     * 英文版
     */
    private final static String dayNamesEN[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
            "Saturday"};
    /**
     * 包含年-秒
     */
    public static final String FOR_YEAR_SECOND = "yyyy-MM-dd HH:mm:ss";
    /**
     * 包含年-日
     */
    public static final String FOR_YEAR_DAY = "yyyy-MM-dd";
    /**
     * 包含月-日
     */
    public static final String FOR_MONTH_DAY = "MM-dd";
    /**
     * 包含月-分
     */
    public static final String FOR_MONTH_MINUTE = "MM-dd HH:mm:ss";
    /**
     * 包含时-秒
     */
    public static final String FOR_HOUR_SECOND = "HH:mm:ss";
    /**
     * 包含时-分
     */
    public static final String FOR_HOUR_MINUTE = "HH:mm";
    /**
     * 包含年-秒，作为文件名
     */
    public static final String FOR_FILENAME = "yyyyMMdd_HHmmssSSS";

    /**
     * 获取制定格式的现在的日期
     */
    @SuppressLint("SimpleDateFormat")
    public static String getNowDateTime(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date curDate = new Date(System.currentTimeMillis());
        String dateString = formatter.format(curDate);
        return dateString;
    }

    public static int getMinute(Date dateTime) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dateTime);

        return calendar.get(Calendar.MINUTE);
    }

    public static int getHour(Date dateTime) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dateTime);

        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getYear(Date dateTime) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dateTime);

        return calendar.get(Calendar.YEAR);
    }

    public static int getAge(Date birthday) {
        if (birthday == null)
            return 0;

        Date now = new Date();

        int b_year = DateUtils.getYear(birthday);
        int n_year = DateUtils.getYear(now);

        int b_month = DateUtils.getMonth(birthday);

        int s = n_year - b_year;

        if (b_month > 6)
            return s - 1;
        else
            return s;
    }

    public static int getDay(Date dateTime) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dateTime);

        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static int getMonth(Date dateTime) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dateTime);

        return calendar.get(Calendar.MONTH);
    }

    public static Date getDate(String dateString) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return sdf.parse(dateString);
    }

    /**
     * 返回当前手机时间
     */
    public static String getDate(Long time) {

        SimpleDateFormat sDateFormat = new SimpleDateFormat(FOR_YEAR_DAY);

        return sDateFormat.format(time);
    }

    /**
     * 返回当前时间
     */
    public static String getTodayDate() {

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sDateFormat.format(new Date());
    }

    public static Date getTime(String dateString) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(FOR_HOUR_MINUTE);

        return sdf.parse(dateString);
    }

    public static String format(Date time, String format) {
        if (time == null)
            return "";
        String result = "";
        try {
            DateFormat df = new SimpleDateFormat(format);
            result = df.format(time);
        } catch (Exception e) {
        }
        return result;
    }

    public static String liveTime(Date time) {
        long now = System.currentTimeMillis();
        long ago = time.getTime();

        long second = (now - ago) / 1000;

        if (second < 60)
            return second + "秒前";

        long min = second / 60;
        if (min < 60)
            return min + "分钟前";

        long hour = min / 60;
        if (hour < 24)
            return hour + "小时前";

        long day = hour / 24;
        if (day < 365)

            return getDate(ago);

        return getDate(ago);
    }

    public static int obtainTimeFromNow(String type, long shift) {
        long selectTime = System.currentTimeMillis() + shift;
        return Integer.valueOf(DateUtils.format(new Date(selectTime), type));
    }

    public static String getFabuTime(String createtime) {

        DateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat d2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date change_timeString = null;
        try {
            change_timeString = d2.parse(createtime);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        String time = "";
        String nowtime = "";
        time = d1.format(change_timeString);
        nowtime = d1.format(new Date());

        DateFormat d3 = new SimpleDateFormat("HH:mm");
        DateFormat d4 = new SimpleDateFormat("MM月dd日  HH:mm");
        String back_time = "";
        if (nowtime.equals(time)) {
            back_time += "今天" + d3.format(change_timeString);
        } else {
            back_time += d4.format(change_timeString);
        }
        return back_time;
    }

    /**
     * 计算二个时间点之间相差的分钟
     */
    public static int parseBetweninHour(String s_time, String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        Date currentDate = null;
        try {
            date = format.parse(time);
            currentDate = format.parse(s_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long orderTime = date.getTime();
        long currentTime = currentDate.getTime();
        long hour = currentTime - orderTime;

        if (orderTime - currentTime > 0) {
            return 2;
        } else {

            if (hour < 24 * 60 * 60 * 1000) {// 小于24小时的
                return 0;
            } else {
                return 1;// 大于24小时的
            }
        }
    }

    /**
     * 计算两个日期型的时间相差多少时间
     */
    public static String twoDateDistance(String s_time, String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse(time);
            endDate = format.parse(s_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (startDate == null || endDate == null) {
            return null;
        }
        long timeLong = endDate.getTime() - startDate.getTime();
        if (timeLong < 60 * 1000)
            return timeLong / 1000 + "秒前";
        else if (timeLong < 60 * 60 * 1000) {
            timeLong = timeLong / 1000 / 60;
            return timeLong + "分钟前";
        } else if (timeLong < 60 * 60 * 24 * 1000) {
            timeLong = timeLong / 60 / 60 / 1000;
            return timeLong + "小时前";
        } else if (timeLong < 60 * 60 * 24 * 1000 * 7) {
            timeLong = timeLong / 1000 / 60 / 60 / 24;
            return timeLong + "天前";
        } else if (timeLong < 60 * 60 * 24 * 1000 * 7 * 4) {
            timeLong = timeLong / 1000 / 60 / 60 / 24 / 7;
            return timeLong + "周前";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
            return sdf.format(startDate);
        }
    }


    public static String obtainTimeFromSet(String time, long between, int type) throws ParseException {

        int hour1 = DateUtils.getHour(DateUtils.getDate(time));
        int min1 = DateUtils.getMinute(DateUtils.getDate(time));

        Date date = DateUtils.getDate(time);
        long grabTime = date.getTime() + between;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(grabTime);
        Date formatDate = cal.getTime();

        int hour2 = DateUtils.getHour(formatDate);
        int min2 = DateUtils.getMinute(formatDate);

        if (between < 0) {
            int temp = hour1;
            hour1 = hour2;
            hour2 = temp;

            temp = min1;
            min1 = min2;
            min2 = temp;
        }

        String yyr = DateUtils.format(DateUtils.getDate(time), "yyyy-MM-dd");

        if (1 == type) {
            yyr = !yyr.equals(DateUtils.format(new Date(), "yyyy-MM-dd")) ? "今天" : "明天";
        }

        String objectTime = yyr + " " + hour1 + ":" + (min1 == 0 ? "00" : min1) + "-" + hour2 + ":"
                + (min2 == 0 ? "00" : min2);
        return objectTime;
    }

    public static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取今天0点开始的秒数
     *
     * @return long
     */
    public static long getTimeNumberToday() {
        Date date = new Date();
        String str = yyyyMMdd.format(date);
        try {
            date = yyyyMMdd.parse(str);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 获取创建的时间
     *
     * @return long
     */
    public static long getTimeNumberToday(String createtime) {
        Date date = new Date();
        String str = yyyyMMdd.format(createtime);
        try {
            date = yyyyMMdd.parse(str);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

}
