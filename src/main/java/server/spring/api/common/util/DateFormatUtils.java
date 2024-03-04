package server.spring.api.common.util;


import static server.spring.api.common.enums.DatePattern.DATE_TIME_PATTERN;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Optional;
import org.springframework.util.StringUtils;



public class DateFormatUtils {

  // ZonedDateTime : 타임존 또는 시차 개념이 필요한 날짜와 시간 정보
  // 시차 계산
  public static Date ldt2Date(LocalDateTime ldt) {
    // instance of ZonedDateTime from a local date-time.
    ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());  // Asia/Seoul  = ZoneId.of("Asia/Seoul");
    GregorianCalendar cal = GregorianCalendar.from(zdt);
    return cal.getTime();
  }

  public static LocalDateTime date2Ldt(Date dt) {
    // ex. (기준시 UTC, GMT)2022-05-21T08:21:07.333584Z
    Instant instant = dt.toInstant();
    // Asia/Seoul  = ZoneId.of("Asia/Seoul");
    ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
    return zonedDateTime.toLocalDateTime();
  }

  public static String instant2Str(Instant instant) {
    if (Objects.nonNull(instant)) {
      ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
      return zonedDateTime.format(formatter);
    }
    return null;
  }

  // Date Pattern Format
  public static LocalDateTime str2Ldt(String str, String pattern) {
    if (!BaseUtils.isNullOrEmpty(StringUtils.trimAllWhitespace(str))) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
      return LocalDateTime.parse(str, formatter);
    }
    return null;
  }

  public static LocalDate str2Ld(String str, String pattern) {

    if (!BaseUtils.isNullOrEmpty(StringUtils.trimAllWhitespace(str))) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
      return LocalDate.parse(str, formatter);
    }
    return null;
  }

  public static String ld2Str(LocalDate localDate, String pattern) {

    if (Objects.nonNull(localDate)) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
      return localDate.format(formatter);
    }
    return null;
  }

  public static String ldt2Str(LocalDateTime localDateTime, String pattern) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    return Optional.ofNullable(localDateTime).map(formatter::format).orElse(null);
  }

  public static Integer getWeekNumber(LocalDate date, DayOfWeek dayOfWeek) {
    LocalDate firstMondayOfMonth = date.with(TemporalAdjusters.firstInMonth(dayOfWeek));

    // 첫 요일이면 바로 리턴
    if (firstMondayOfMonth.isEqual(date)) {
      return 1;
    }

    if (date.isAfter(firstMondayOfMonth)) {
      // 첫 요일 이후일 때
      int diffFromFirstMonday = date.getDayOfMonth() - firstMondayOfMonth.getDayOfMonth();
      int weekNumber = (int) Math.ceil(diffFromFirstMonday / 7.0);
      if (date.getDayOfWeek() == dayOfWeek) weekNumber += 1;
      return weekNumber;
    }
    // 첫 요일 이전이면 회귀식으로 전 달 마지막 주차를 구함
    return getWeekNumber(date.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()), dayOfWeek);
  }


  // 그 달의 N번째 주(1~7일 1주, 8~14일 2주, ...)
  public static int getWeekNumberFromLocalDate(LocalDate localDate) {
    return localDate.get(ChronoField.ALIGNED_WEEK_OF_MONTH);
  }


  // 클래스 TemporalAdjusters
  // 해당 달의 첫 번째 또는 마지막 날 찾기
  // 다음 달의 첫날 찾기
  // 해당 연도의 첫날 또는 마지막 날 찾기
  // 내년의 첫날을 찾아라
  // "6월의 첫 번째 수요일"과 같이 한 달 내 첫 번째 또는 마지막 요일 찾기
  // "다음 목요일"과 같이 다음 또는 이전 요일 찾기
  public static LocalDate localDateOnNthDayOfWeekOfMonth(
      LocalDate date, int ordinal, DayOfWeek dayOfWeek) {
    return date.with(TemporalAdjusters.dayOfWeekInMonth(ordinal, dayOfWeek));
  }
}
