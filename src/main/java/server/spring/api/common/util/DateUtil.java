package server.spring.api.common.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

    /**
     * 현재 Calendar 객체가져오는 메소드
     */
    public static Calendar getCalendar() {
        return Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA);
    }

    /**
     * 현재시간 기준 UnixTimestamp 값 리턴
     */
    public static int getUnixTimestamp() {
        return (int) (getCalendar().getTimeInMillis() / 1000L);
    }

    /**
     * 주어진 파라미터의 Calendar의 UnixTimestamp 값 리턴
     */
    public static int getUnixTimestampByCalendar(Calendar _calendar) {
        return (int) (_calendar.getTimeInMillis() / 1000L);
    }

    /**
     * 현재일 자정 기준 UnixTimestamp 값 리턴
     */
    public static int getUnixTimestampDate() {
        return (int) (getCalendarDate().getTimeInMillis() / 1000L);
    }

    /**
     * 현재월 마지막일 UnixTimestamp 값 리턴
     */
    public static int getUnixTimestampLastDayOfMonth() {
        Calendar cal = getCalendarDate();
        int dayOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return (int) (cal.getTimeInMillis() / 1000L);
    }

    /**
     * 현재월 첫번째일 UnixTimestamp 값 리턴
     */
    public static int getUnixTimestampFirstDayOfMonth() {
        Calendar cal = getCalendarDate();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return (int) (cal.getTimeInMillis() / 1000L);
    }

    /**
     * 주어진 UnixTimestamp값에서 주어진 일수 만큰 더한  UnixTimestamp값 리턴
     *
     * @param orgTimestamp
     * @param day
     * @return
     */
    public static int getUnixTimestampAddDay(int orgTimestamp, int day) {
        return (orgTimestamp + 24 * 60 * 60 * day);
    }

    /**
     * 주어진 UnixTimestamp값을 주어진 패턴 문자열로 리턴
     *
     * @param unixTimestamp
     * @param pattern
     * @return
     */
    public static String getDateStrFromUnixTimestamp(int unixTimestamp, String pattern) {
        Date date = new Date(unixTimestamp * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 현재일 기준 시분초를 자정으로 셋팅한 Calendar 객체를 리턴
     */
    public static Calendar getCalendarDate() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal;
    }

    /**
     * 특정 년월일 시분초를 자정 세팅한 Calendar 객체를 리턴
     *
     * @param year  년
     * @param month 월(0~11)
     * @param date  일
     * @return
     */
    public static Calendar getCalendarDate(int year, int month, int date) {
        Calendar cal = getCalendarDate();
        cal.set(year, month, date);
        return cal;
    }

    /**
     * 특정 년월일이 현재일 기준 도달 또는 지났는지 체크 메소드
     *
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static boolean isEqualOverDate(int year, int month, int date) {
        Calendar c1 = getCalendarDate();
        long t1 = c1.getTimeInMillis();

        Calendar c2 = getCalendarDate(year, month, date);
        long t2 = c2.getTimeInMillis();

        return t1 - t2 > -1;
    }

    /**
     * 현재시간이 주어진 날짜 사이인지 비교
     *
     * @param startDate
     * @param endDate
     * @param format    날짜 포멧( 예 > yyyy-MM-dd HH:mm:ss)
     * @return
     */
    public static boolean isBetweenCurrentDate(String startDate, String endDate, String format) {
        boolean isBetween = false;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date sd, ed, cd = null;

            sd = dateFormat.parse(startDate);
            ed = dateFormat.parse(endDate);
            cd = dateFormat.parse(dateFormat.format(getCalendar().getTime()));

            int compare1 = cd.compareTo(sd);
            int compare2 = cd.compareTo(ed);

            if (compare1 >= 0 && compare2 <= 0) {
                isBetween = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isBetween;
    }

    public static boolean isBetweenCurrentDate(Calendar startDate, Calendar endDate, String format) {
        boolean isBetween = false;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            isBetween = isBetweenCurrentDate(
                    dateFormat.format(startDate.getTime()),
                    dateFormat.format(endDate.getTime()),
                    format
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isBetween;
    }

    /*
     * cal이 startCal과 endCal 사이에 있는지 반환
     *
     * @param cal
     * @param startCal
     * @param endCal
     * @return
     */
    public static boolean isBetween(Calendar cal, Calendar startCal, Calendar endCal) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        System.out.println("[" + df.format(cal.getTime()) + "]");
        long a = cal.getTimeInMillis() / 1000;

        System.out.println("[" + df.format(startCal.getTime()) + "]");
        long b = startCal.getTimeInMillis() / 1000;

        System.out.println("[" + df.format(endCal.getTime()) + "]");
        long c = endCal.getTimeInMillis() / 1000;

        long t1 = a - b;
        System.out.println("t1[" + t1 + "]");
        long t2 = a - c;
        System.out.println("t1[" + t2 + "]");

        return t1 >= 0 && t2 <= 0;
    }

    public static boolean isBetweenTime(LocalTime curTime, LocalTime startTime, LocalTime endTime) {
        boolean isBetween = false;
        try {
            isBetween = curTime.isAfter(startTime) && curTime.isBefore(endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isBetween;
    }

    public static String getCurrentDateString(String dateFormatString) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(dateFormatString);
        format.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        return format.format(date);
    }

    public static int getMartHolidayWeekOfMonth(LocalDate localDate) {
        int day = localDate.getDayOfMonth();

        if (1 <= day && day <= 7) {
            return 1;
        }

        if (8 <= day && day <= 14) {
            return 2;
        }

        if (15 <= day && day <= 21) {
            return 3;
        }

        if (22 <= day && day <= 28) {
            return 4;
        }

        return 5;
    }
}
