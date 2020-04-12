//package Utils;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.ZoneId;
//import java.time.ZoneOffset;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoUnit;
//import java.time.temporal.TemporalAdjusters;
//import java.util.Date;
//import java.util.TimeZone;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class TimeUtils {
//
//    private final static Logger LOGGER = LoggerFactory.getLogger(TimeUtils.class);
//
//    public static final String FORMAT_MONTH = "YYYYMM";
//    public static final String FORMAT_DAY = "YYYYMMdd";
//    public static final String FORMAT_TIME = "MMddHHmmss";
//    public static final String FORMAT_DAY_TIME = "YYYYMMddHHmmss";
//
//    public static final String FORMAT_TO_SECOND = "yyyyMMddHHmmss";
//    public static final String FORMAT_TO_SECOND_READABLE = "yyyy-MM-dd HH:mm:ss";
//    public static final String FORMAT_TO_DAY = "yyyyMMdd";
//    /**
//     * 获取当前北京时间
//     * @return
//     */
//    public static LocalDateTime getChinaDateNow(){
//        return LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
//    }
//
//    public static String formatDate(LocalDateTime dateTime, String format){
//        return dateTime.format(DateTimeFormatter.ofPattern(format));
//    }
//
//    public static String formatLocalDate(LocalDate ldt, String format){
//        return ldt.format(DateTimeFormatter.ofPattern(format));
//    }
//
//    public static String getMonth(){
//        LocalDateTime dateTime = getChinaDateNow();
//        return dateTime.format(DateTimeFormatter.ofPattern(FORMAT_MONTH));
//    }
//
//    public static String getDay(){
//        LocalDateTime dateTime = getChinaDateNow();
//        return dateTime.format(DateTimeFormatter.ofPattern(FORMAT_DAY));
//    }
//
//    public static String getDay(String pattern){
//        LocalDateTime dateTime = getChinaDateNow();
//        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
//    }
//
//    public static String getDayTime(){
//        LocalDateTime dateTime = getChinaDateNow();
//        return dateTime.format(DateTimeFormatter.ofPattern(FORMAT_DAY_TIME));
//    }
//
//    public static String getDayTimeInChineseTimeUnit(){
//        String dayTime = getDay();
//        return dayTime.substring(0,4) + "年" + dayTime.substring(4,6)+ "月" + dayTime.substring(6) + "日";
//    }
//
//    public static String getWeTime(){
//        LocalDateTime dateTime = getChinaDateNow();
//        return TimeUtils.formatDate(dateTime,FORMAT_TIME);
//    }
//
//    public static Long getCurrentTimeStamp(){
//        return getChinaDateNow().toInstant(ZoneOffset.of("+8")).toEpochMilli();
//    }
//
//    public static Long getCurrentTimeSeconds(){
//        return getChinaDateNow().toEpochSecond(ZoneOffset.of("+8"));
//    }
//
//    public static LocalDateTime timestampToDatetime(long timestamp){
//        Instant instant = Instant.ofEpochSecond(timestamp);
//        return LocalDateTime.ofInstant(instant, ZoneOffset.of("+8"));
//    }
//
//    public static long datetimeToTimestamp(LocalDateTime ldt) {
//      return ldt.toEpochSecond(ZoneOffset.of("+8"));
//    }
//
//    public static Date convertLdtToDate(LocalDateTime ldt){
//        ZonedDateTime zdt = ldt.atZone(ZoneId.of("Asia/Shanghai"));
//        return Date.from(zdt.toInstant());
//    }
//
//    public static Date stringDateToUdate(String date) {
//        try{
//            SimpleDateFormat sdf= new SimpleDateFormat(FORMAT_TO_DAY);
//            return sdf.parse(date);
//        }catch(ParseException e){
//            LOGGER.error("stringDateToUdate error - {}",e.getMessage());
//        }
//        return null;
//    }
//
//    public static LocalDateTime dateToLocalDateTime(Date date) {
//        Instant instant = date.toInstant();
//        return LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Shanghai"));
//    }
//
//    public static String uDateToStr(Date date, String format) {
//        SimpleDateFormat sdf = new SimpleDateFormat(format);
//        return sdf.format(date);
//    }
//
//    public static long calcDaysBetweenDates(LocalDate ldt1, LocalDate ldt2){
//        return ldt1.toEpochDay() - ldt2.toEpochDay();
//    }
//
//    public static LocalDateTime strToLdt(String yearDateTime){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//        return LocalDateTime.parse(yearDateTime, formatter);
//    }
//
//    public static LocalDate strToLocalDate(String yearDate){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//        return LocalDate.parse(yearDate, formatter);
//    }
//
//    public static Long calMillisBetweenLdt(LocalDateTime startTime, LocalDateTime  endTime){
//        java.time.Duration duration = java.time.Duration.between( startTime, endTime);
//        return duration.toMillis();
//    }
//
//    /**
//     * 根据微众请求头中交易日期和交易时间获取work_date
//     * @param reqDate
//     * @param reqTime
//     * @return
//     */
//    public static String calWorkDate(String reqDate,String reqTime){
//
//        if (StringUtils.isAnyEmpty(reqDate,reqTime)){
//            return null;
//        }
//        if (reqDate.length() != 8 || reqTime.length() != 10){
//            return null;
//        }
//
//        try{
//            String subReqDate = reqDate.substring(0, 4);
//            DateTimeFormatter secondFormatter = DateTimeFormatter.ofPattern(FORMAT_TO_SECOND);
//            StringBuffer orderTimeBf = new StringBuffer(subReqDate);
//            orderTimeBf.append(reqTime);
//
//            LocalDateTime parseLocalTime = LocalDateTime.parse(orderTimeBf.toString(), secondFormatter);
//            LocalDateTime plusDays = parseLocalTime;
//
//            int hour = parseLocalTime.getHour();
//            int minute = parseLocalTime.getMinute();
//            int second = parseLocalTime.getSecond();
//            if (hour >= 16 && (minute + second) >= 0){
//                plusDays = parseLocalTime.plusDays(1);
//            }
//            DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern(FORMAT_TO_DAY);
//            return plusDays.format(dayFormatter);
//        }catch (Exception e){
//            LOGGER.error("format reqDate = {} and reqTime = {} to workDate failed",reqDate,reqTime,e);
//            return null;
//        }
//    }
//
//    /**
//     *
//     * @param dayTime yyyyMMdd
//     * @return  yyyyMMdd
//     */
//    public static String beforeOneDay(String dayTime){
//
//        DateTimeFormatter parseDayTime = DateTimeFormatter.ofPattern(FORMAT_TO_DAY);
//
//        LocalDate localDate = LocalDate.parse(dayTime, parseDayTime);
//
//        String result = localDate.minusDays(1).format(parseDayTime);
//
//        return result;
//    }
//
//    public static String afterOneDay(String dayTime){
//
//        DateTimeFormatter parseDayTime = DateTimeFormatter.ofPattern(FORMAT_TO_DAY);
//
//        LocalDate localDate = LocalDate.parse(dayTime, parseDayTime);
//
//        return localDate.plusDays(1).format(parseDayTime);
//
//    }
//
//    public static String getLastMonth(){
////        // format as basic ISO date format (20140220)
////        String asBasicIsoDate = getChinaDateNow().format(DateTimeFormatter.BASIC_ISO_DATE);
////        return asBasicIsoDate.substring(0,4);
//
//        LocalDateTime dateTime = getChinaDateNow().minusMonths(1);
//        return dateTime.format(DateTimeFormatter.ofPattern(FORMAT_MONTH));
//    }
//
//    public static Long dateStrToTimestamp(String date){
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_TO_DAY);//SimpleDateFormat 用小写yyyy
//            dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//            return dateFormat.parse(date).getTime();
//        } catch(Exception e) {
//            LOGGER.error("dateStrToTimestamp error - {}",e);
//        }
//        return null;
//    }
//
//    public static Long dateTimeStrToTimestamp(String date){
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_TO_SECOND);//SimpleDateFormat 用小写yyyy
//            dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//            return dateFormat.parse(date).getTime();
//        } catch(Exception e) {
//            LOGGER.error("dateTimeStrToTimestamp error - {}",e);
//        }
//        return null;
//    }
//
//    public static Date parse(String formatterDate){
//        DateFormat format = new SimpleDateFormat(FORMAT_TO_SECOND);
//
//        try {
//            Date date = format.parse(formatterDate);
//            return date;
//        } catch (ParseException e) {
//            LOGGER.error("{} format failed ",formatterDate,e);
//        }
//        return null;
//    }
//
//    public static Integer getRemainSecondsOneDay(LocalDateTime ldt) {
//        LocalDateTime midnight = ldt.plusDays(1).withHour(0).withMinute(0)
//                .withSecond(0).withNano(0);
//        LocalDateTime currentDateTime = getChinaDateNow();
//        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
//        return (int) seconds;
//    }
//
//    /**
//     * 计算当前日期到当月底的日期差,返回单位（天）
//     */
//    public static int calcDayLeftOfMonth() {
//        LocalDate now = TimeUtils.getChinaDateNow().toLocalDate();
//        LocalDate endOfMonth = LocalDate
//            .of(now.getYear(), now.plusMonths(1).getMonth(), 1);
//        return (int) TimeUtils.calcDaysBetweenDates(endOfMonth, now)
//            - 1;
//    }
//
//    /**
//     * 计算当前日期到当月底的日期时间差，返回单位（秒）
//     */
//    public static int calcSecondsLeftOfMonth(){
//        LocalDateTime now = getChinaDateNow();
//        LocalDateTime endTimeOfMonth = now.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
//        long seconds = ChronoUnit.SECONDS.between(now, endTimeOfMonth);
//        return (int) seconds;
//    }
//
//}
