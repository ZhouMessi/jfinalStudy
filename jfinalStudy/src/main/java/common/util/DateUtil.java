//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package common.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateUtil extends TagSupport {
    private static final long serialVersionUID = -2312310581852395045L;
    private String value;
    private String pattern;
    public static final String pattern_ymd = "yyyy-MM-dd";
    public static final String pattern_ymd_hms = "yyyy-MM-dd HH:mm:ss";
    public static final String pattern_ymd_hms_s = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String pattern_yyyy_m_d_ch = "yyyy年M月d日";

    public DateUtil() {
    }

    private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {
        return new SimpleDateFormat(parttern);
    }

    private static int getInteger(Date date, int dateType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(dateType);
    }

    private static String addInteger(String date, int dateType, int amount) {
        String dateString = null;
        DateStyle dateStyle = getDateStyle(date);
        if (null != dateStyle) {
            Date myDate = StringToDate(date, dateStyle);
            myDate = addInteger(myDate, dateType, amount);
            dateString = DateToString(myDate, dateStyle);
        }

        return dateString;
    }

    private static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (null != date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }

        return myDate;
    }

    private static Date getAccurateDate(List<Long> timestamps) {
        Date date = null;
        long timestamp = 0L;
        Map<Long, long[]> map = new HashMap();
        List<Long> absoluteValues = new ArrayList();
        if (null != timestamps && timestamps.size() > 0) {
            if (timestamps.size() > 1) {
                for(int i = 0; i < timestamps.size(); ++i) {
                    for(int j = i + 1; j < timestamps.size(); ++j) {
                        long absoluteValue = Math.abs((Long)timestamps.get(i) - (Long)timestamps.get(j));
                        absoluteValues.add(absoluteValue);
                        long[] timestampTmp = new long[]{(Long)timestamps.get(i), (Long)timestamps.get(j)};
                        map.put(absoluteValue, timestampTmp);
                    }
                }

                long minAbsoluteValue = -1L;
                if (!absoluteValues.isEmpty()) {
                    minAbsoluteValue = (Long)absoluteValues.get(0);
                }

                for(int i = 0; i < absoluteValues.size(); ++i) {
                    for(int j = i + 1; j < absoluteValues.size(); ++j) {
                        if ((Long)absoluteValues.get(i) > (Long)absoluteValues.get(j)) {
                            minAbsoluteValue = (Long)absoluteValues.get(j);
                        } else {
                            minAbsoluteValue = (Long)absoluteValues.get(i);
                        }
                    }
                }

                if (minAbsoluteValue != -1L) {
                    long[] timestampsLastTmp = (long[])map.get(minAbsoluteValue);
                    if (absoluteValues.size() > 1) {
                        timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);
                    } else if (1 == absoluteValues.size()) {
                        long dateOne = timestampsLastTmp[0];
                        long dateTwo = timestampsLastTmp[1];
                        if (Math.abs(dateOne - dateTwo) < 100000000000L) {
                            timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);
                        } else {
                            long now = (new Date()).getTime();
                            if (Math.abs(dateOne - now) <= Math.abs(dateTwo - now)) {
                                timestamp = dateOne;
                            } else {
                                timestamp = dateTwo;
                            }
                        }
                    }
                }
            } else {
                timestamp = (Long)timestamps.get(0);
            }
        }

        if (0L != timestamp) {
            date = new Date(timestamp);
        }

        return date;
    }

    public static boolean isDate(String date) {
        boolean isDate = false;
        if (null != date && null != StringToDate(date)) {
            isDate = true;
        }

        return isDate;
    }

    public static boolean isDateForParttern(String date, String parttern) {
        boolean isDate = false;
        if (null != date && null != StringToDate(date, parttern)) {
            isDate = true;
        }

        return isDate;
    }

    public static DateStyle getDateStyle(String date) {
        DateStyle dateStyle = null;
        Map<Long, DateStyle> map = new HashMap();
        List<Long> timestamps = new ArrayList();
        DateStyle[] var4 = DateStyle.values();
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            DateStyle style = var4[var6];
            Date dateTmp = StringToDate(date, style.getValue());
            if (null != dateTmp) {
                timestamps.add(dateTmp.getTime());
                map.put(dateTmp.getTime(), style);
            }
        }

        dateStyle = (DateStyle)map.get(getAccurateDate(timestamps).getTime());
        return dateStyle;
    }

    public static Date StringToDate(String date) {
        DateStyle dateStyle = DateStyle.YYYY_MM_DD_HH_MM_SS;
        return StringToDate(date, dateStyle);
    }

    public static Date StringToDate(String date, String parttern) {
        Date myDate = null;
        if (null != date) {
            try {
                myDate = getDateFormat(parttern).parse(date);
            } catch (Exception var4) {
            }
        }

        return myDate;
    }

    public static Date StringToDate(String date, DateStyle dateStyle) {
        Date myDate = null;
        if (null == dateStyle) {
            List<Long> timestamps = new ArrayList();
            DateStyle[] var4 = DateStyle.values();
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                DateStyle style = var4[var6];
                Date dateTmp = StringToDate(date, style.getValue());
                if (null != dateTmp) {
                    timestamps.add(dateTmp.getTime());
                }
            }

            myDate = getAccurateDate(timestamps);
        } else {
            myDate = StringToDate(date, dateStyle.getValue());
        }

        return myDate;
    }

    public static String DateToString(Date date, String parttern) {
        String dateString = null;
        if (null != date) {
            try {
                dateString = getDateFormat(parttern).format(date);
            } catch (Exception var4) {
            }
        }

        return dateString;
    }

    public static String DateToString(Date date, DateStyle dateStyle) {
        String dateString = null;
        if (null != dateStyle) {
            dateString = DateToString(date, dateStyle.getValue());
        }

        return dateString;
    }

    public static String StringToString(String date, String parttern) {
        return StringToString(date, (String)null, (String)parttern);
    }

    public static String StringToString(String date, DateStyle dateStyle) {
        return StringToString(date, (DateStyle)null, (DateStyle)dateStyle);
    }

    public static String StringToString(String date, String olddParttern, String newParttern) {
        String dateString = null;
        if (null == olddParttern) {
            DateStyle style = getDateStyle(date);
            if (null != style) {
                Date myDate = StringToDate(date, style.getValue());
                dateString = DateToString(myDate, newParttern);
            }
        } else {
            Date myDate = StringToDate(date, olddParttern);
            dateString = DateToString(myDate, newParttern);
        }

        return dateString;
    }

    public static String StringToString(String date, DateStyle olddDteStyle, DateStyle newDateStyle) {
        String dateString = null;
        if (null == olddDteStyle) {
            DateStyle style = getDateStyle(date);
            dateString = StringToString(date, style.getValue(), newDateStyle.getValue());
        } else {
            dateString = StringToString(date, olddDteStyle.getValue(), newDateStyle.getValue());
        }

        return dateString;
    }

    public static String addYear(String date, int yearAmount) {
        return addInteger((String)date, 1, yearAmount);
    }

    public static Date addYear(Date date, int yearAmount) {
        return addInteger((Date)date, 1, yearAmount);
    }

    public static String addMonth(String date, int monthAmount) {
        return addInteger((String)date, 2, monthAmount);
    }

    public static Date addMonth(Date date, int monthAmount) {
        return addInteger((Date)date, 2, monthAmount);
    }

    public static String addDay(String date, int dayAmount) {
        return addInteger((String)date, 5, dayAmount);
    }

    public static Date addDay(Date date, int dayAmount) {
        return addInteger((Date)date, 5, dayAmount);
    }

    public static String addHour(String date, int hourAmount) {
        return addInteger((String)date, 11, hourAmount);
    }

    public static Date addHour(Date date, int hourAmount) {
        return addInteger((Date)date, 11, hourAmount);
    }

    public static String addMinute(String date, int hourAmount) {
        return addInteger((String)date, 12, hourAmount);
    }

    public static Date addMinute(Date date, int hourAmount) {
        return addInteger((Date)date, 12, hourAmount);
    }

    public static String addSecond(String date, int hourAmount) {
        return addInteger((String)date, 13, hourAmount);
    }

    public static Date addSecond(Date date, int hourAmount) {
        return addInteger((Date)date, 13, hourAmount);
    }

    public static int getYear(String date) {
        return getYear(StringToDate(date));
    }

    public static int getYear(Date date) {
        return getInteger(date, 1);
    }

    public static int getMonth(String date) {
        return getMonth(StringToDate(date));
    }

    public static int getMonth(Date date) {
        return getInteger(date, 2) + 1;
    }

    public static int getDay(String date) {
        return getDay(StringToDate(date));
    }

    public static int getDay(Date date) {
        return getInteger(date, 5);
    }

    public static int getHour(String date) {
        return getHour(StringToDate(date));
    }

    public static int getHour(Date date) {
        return getInteger(date, 11);
    }

    public static int getMinute(String date) {
        return getMinute(StringToDate(date));
    }

    public static int getMinute(Date date) {
        return getInteger(date, 12);
    }

    public static int getSecond(String date) {
        return getSecond(StringToDate(date));
    }

    public static int getSecond(Date date) {
        return getInteger(date, 13);
    }

    public static String getDate(String date) {
        return StringToString(date, DateStyle.YYYY_MM_DD);
    }

    public static String getDate(Date date) {
        return DateToString(date, DateStyle.YYYY_MM_DD);
    }

    public static String getTime(String date) {
        return StringToString(date, DateStyle.HH_MM_SS);
    }

    public static String getTime(Date date) {
        return DateToString(date, DateStyle.HH_MM_SS);
    }

    public static Week getWeek(String date) {
        Week week = null;
        DateStyle dateStyle = getDateStyle(date);
        if (null != dateStyle) {
            Date myDate = StringToDate(date, dateStyle);
            week = getWeek(myDate);
        }

        return week;
    }

    public static Week getWeek(Date date) {
        Week week = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekNumber = calendar.get(7) - 1;
        switch(weekNumber) {
            case 0:
                week = Week.SUNDAY;
                break;
            case 1:
                week = Week.MONDAY;
                break;
            case 2:
                week = Week.TUESDAY;
                break;
            case 3:
                week = Week.WEDNESDAY;
                break;
            case 4:
                week = Week.THURSDAY;
                break;
            case 5:
                week = Week.FRIDAY;
                break;
            case 6:
                week = Week.SATURDAY;
        }

        return week;
    }

    public static int getIntervalMonths(Date aDate, Date bDate) {
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(aDate);
        aft.setTime(bDate);
        int result = aft.get(2) - bef.get(2);
        int month = (aft.get(1) - bef.get(1)) * 12;
        return Math.abs(month + result);
    }

    public static int getIntervalDays(String date, String otherDate) {
        return getIntervalDays(StringToDate(date), StringToDate(otherDate));
    }

    public static int getIntervalDays(Date date, Date otherDate) {
        long time = Math.abs(date.getTime() - otherDate.getTime());
        return (int)(time / 86400000L);
    }

    public static int getIntervalHours(Date date, Date otherDate) {
        date = StringToDate(getDate(date));
        long time = Math.abs(date.getTime() - otherDate.getTime());
        return (int)(time / 3600000L);
    }

    public static int getIntervalMinu(Date date, Date otherDate) {
        date = StringToDate(DateToString(date, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
        long time = Math.abs(date.getTime() - otherDate.getTime());
        return (int)(time / 60000L);
    }

    public static long getIntervalSec(Date date, Date otherDate) {
        date = StringToDate(DateToString(date, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
        long time = otherDate.getTime() - date.getTime();
        return time / 1000L;
    }

    public static Date getMinDate(Date date) {
        return StringToDate(DateToString(date, "yyyy-MM-dd") + " 00:00:00");
    }

    public static Date getMaxDate(Date date) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String temp = DateToString(date, "yyyy-MM-dd") + " 23:59:59";

            try {
                return sdf.parse(temp);
            } catch (Exception var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }

    public static Date getSunday(Calendar calendar, Integer intervalWeek) {
        calendar.setFirstDayOfWeek(2);
        calendar.add(4, intervalWeek);
        calendar.set(7, 1);
        return calendar.getTime();
    }

    public static Date getYesterdayBegin(Date date) {
        return StringToDate(DateToString(addDay((Date)date, -1), "yyyy-MM-dd") + " 00:00:00");
    }

    public static Date getYesterdayEnd(Date date) {
        return StringToDate(DateToString(addDay((Date)date, -1), "yyyy-MM-dd") + " 23:59:59");
    }

    public static String showText(Date date) {
        String[] weekDays = new String[]{"日", "一", "二", "三", "四", "五", "六"};
        Calendar.getInstance().setTime(new Date());
        Date lastSunday = getSunday(Calendar.getInstance(), -1);
        Calendar.getInstance().setTime(new Date());
        Date last2Sunday = getSunday(Calendar.getInstance(), -2);
        String weekString = null;
        switch(getIntervalDays(new Date(), date)) {
            case 0:
                return "今天";
            case 1:
                return "昨天";
            case 2:
                return "前天";
            default:
                if (date.after(lastSunday)) {
                    weekString = "星期";
                } else if (date.before(lastSunday) && date.after(last2Sunday)) {
                    weekString = "上周";
                } else if (date.before(last2Sunday)) {
                    return "更早之前";
                }

                String dayString = null;
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int w = cal.get(7) - 1;
                dayString = w < 0 ? weekDays[0] : weekDays[w];
                return weekString + dayString;
        }
    }

    public static int compareTime(Date date, Date otherDate, int type) {
        DateStyle dateStyle = null;
        switch(type) {
            case 1:
                dateStyle = DateStyle.YYYY_MM;
                break;
            case 2:
                dateStyle = DateStyle.YYYY_MM_DD;
                break;
            case 3:
                dateStyle = DateStyle.YYYY_MM_DD_HH;
                break;
            case 4:
                dateStyle = DateStyle.YYYY_MM_DD_HH_MM;
                break;
            default:
                dateStyle = DateStyle.YYYY_MM_DD_HH_MM_SS;
        }

        long time = StringToDate(DateToString(date, dateStyle), dateStyle).getTime();
        long otherTime = StringToDate(DateToString(otherDate, dateStyle), dateStyle).getTime();
        if (time == otherTime) {
            return 0;
        } else {
            return time > otherTime ? 1 : -1;
        }
    }

    public static String getThisMonthFirstDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(5, 1);
        return cal.get(1) + "-" + (cal.get(2) + 1) + "-" + cal.get(5) + " 00:00:00";
    }

    public static String getThisMonthLastDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(5, cal.getActualMaximum(5));
        return cal.get(1) + "-" + (cal.get(2) + 1) + "-" + cal.get(5) + " 23:59:59";
    }

    public static String getLastMonthFirstDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(2, -1);
        cal.set(5, 1);
        return cal.get(1) + "-" + (cal.get(2) + 1) + "-" + cal.get(5) + " 00:00:00";
    }

    public static String getLastMonthLastDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(5, 0);
        return cal.get(1) + "-" + (cal.get(2) + 1) + "-" + cal.get(5) + " 23:59:59";
    }

    public int doStartTag() throws JspException {
        String vv = "" + this.value;
        if (!StringUtil.isNull(this.value)) {
            long time = Long.valueOf(vv);
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(time);
            SimpleDateFormat dateformat = new SimpleDateFormat(this.pattern);
            String s = dateformat.format(c.getTime());

            try {
                this.pageContext.getOut().write(s);
            } catch (IOException var8) {
                var8.printStackTrace();
            }
        }

        return super.doStartTag();
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public static String getThisWeekFirstDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(2);
        calendar.set(7, 2);
        return calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5) + " 00:00:00";
    }

    public static String getThisWeekLastDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(2);
        calendar.set(7, 1);
        return calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5) + " 23:59:59";
    }

    public static int getDayOfWeek(Date... date) {
        Calendar c = Calendar.getInstance();
        if (date != null && date.length > 0) {
            c.setTime(date[0]);
        }

        return (new int[]{0, 7, 1, 2, 3, 4, 5, 6})[c.get(7)];
    }

    public static void main(String[] args) {
        System.out.println(getThisWeekFirstDate());
        System.out.println(getThisWeekLastDate());
    }

    public static boolean between(Date now, Date date1, Date date2) {
        long start = date1.getTime() < date2.getTime() ? date1.getTime() : date2.getTime();
        long end = date1.getTime() < date2.getTime() ? date2.getTime() : date1.getTime();
        return now.getTime() >= start && now.getTime() <= end;
    }
}
