package com.sft.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

	private static final String defaultFormat = "yyyyMMdd";
	
	public static long getNumericDate() throws Exception {
		 
		long numericDate = 0;
		
		try {
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = df.parse(getDate("yyyyMMddHHmmss"));
			numericDate = date.getTime();
		} catch (Exception e) {
			throw e;
		}
		return numericDate;
	}
	
	public static long getNumericDate(String dateTime) throws Exception {
		 
		long numericDate = 0;
		
		try {
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = df.parse(dateTime);
			numericDate = date.getTime();
		} catch (Exception e) {
			throw e;
		}
		return numericDate;
	}
	
	/**
	 * 입력한 yyyyMMdd유형을 문자열에 일자의 지정한 정수값 만큼 이전, 이후 날짜를 반환하는 메소드
	 *
	 * @param strDate	yyyyMMdd유형의 Date문자열
	 * @param intSize	날짜 증감 처리를 위한 정수값
	 * @return
	 */
	public static String getBeforeAfterDate(String strDate, int intSize){
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Integer.parseInt(strDate.substring(0,4)),Integer.parseInt(strDate.substring(4,6))-1,Integer.parseInt(strDate.substring(6,8)));
	    calendar.add(Calendar.DATE,intSize);
	    java.util.Date today = calendar.getTime();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    return sdf.format(today);
	}

    /**
     * 입력한 yyyyMMdd유형을 문자열에 일자의 지정한 정수값 만큼 이전, 이후 날짜를 반환하는 메소드
     *
     * @param strDate	yyyyMMdd유형의 Date문자열
     * @param intSize	날짜 증감처리를 위한 정수값
     * @param format	날짜 포멧  ex) yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getBeforeAfterDate(String strDate, int intSize, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(strDate.substring(0,4)),Integer.parseInt(strDate.substring(4,6))-1,Integer.parseInt(strDate.substring(6,8)));
        calendar.add(Calendar.DATE,intSize);
        java.util.Date today = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(today);
    }

    /**
     * GMT기준시간중의 한국표준시를 반환한다.
     *
     * return GMT+09:00형태의 대한민국표준시
     */
    public static Calendar getCalendar() {
        Calendar calendar = new GregorianCalendar(
        TimeZone.getTimeZone("GMT+09:00"),Locale.CANADA);
        calendar.setTime (new Date());
        return calendar;
    }

    /**
     * Date클래스를 지정한 Format으로 변환하여 반환하는 메소드
     *
     * @param date		날짜 정보를 가지고 있는 java.util.Date클래스 Instance
     * @param format	날짜 포멧  ex) yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getDate( Date date, String format ) {
        format = format.toLowerCase();

        if (format.toUpperCase().indexOf("WEEK") != -1) {
        	format = format.substring(0, format.toUpperCase().indexOf("WEEK"));
        }

        int i = format.indexOf("hh24");
        if(i != -1)
            format = format.substring(0, i) + "HH" + format.substring(i + 4);
        i = format.indexOf("mm");
        if(i != -1)
            format = format.substring(0, i) + "MM" + format.substring(i + 2);
        i = format.indexOf("mi");
        if(i != -1)
            format = format.substring(0, i) + "mm" + format.substring(i + 2);

        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 오늘 날짜를 반환하는 메소드 [default : yyyyMMdd format]
     *
     * @return
     */
    public static String getDate() {
    	return getDate(Calendar.getInstance().getTime(), defaultFormat);
    }

    /**
     * 오늘 날짜를 반환하는 메소드
     *
     * @param p_format	날짜 포멧 ex) yyyyMMdd
     * @return
     */
    public static String getDate(String p_format) {
        return getDate(Calendar.getInstance().getTime(), p_format);
    }

    /**
     * Date클래스를 지정한 Format과 요일을 추가하여 반환하는 메소드
     *
     * @param date		날짜 정보를 가지고 있는 java.util.Date클래스 Instance
     * @param format	날짜 포멧  ex) yyyy-MM-dd HH:mm:ss
     * @param week		Week를 의미하는 상수값 ex) Calendar.MONDAY
     * @return
     */
    public static String getDate( Date date, String format, int week ) {

        format = format.toLowerCase();
        if (format.toUpperCase().indexOf("WEEK") != -1) {
        	format = format.substring(0, format.toUpperCase().indexOf("WEEK"));
        }
        int i = format.indexOf("hh24");
        if(i != -1)
            format = format.substring(0, i) + "HH" + format.substring(i + 4);
        i = format.indexOf("mm");
        if(i != -1)
            format = format.substring(0, i) + "MM" + format.substring(i + 2);
        i = format.indexOf("mi");
        if(i != -1)
            format = format.substring(0, i) + "mm" + format.substring(i + 2);

        String ls_week = null;

        switch(week) {
	        case 1:
	        	ls_week = "(일)";
	        	break;
	        case 2:
	        	ls_week = "(월)";
	        	break;
	        case 3:
	        	ls_week = "(화)";
	        	break;
	        case 4:
	        	ls_week = "(수)";
	        	break;
	        case 5:
	        	ls_week = "(목)";
	        	break;
	        case 6:
	        	ls_week = "(금)";
	        	break;
	        case 7:
	        	ls_week = "(토)";
	        	break;
	        default:
	        	ls_week = "";
        }

       	return new SimpleDateFormat(format).format(date) + ls_week;
    }

    /**
     * 입력한 yyyyMMdd유형을 올바른 날자 유형으로 변경하여 반환하는 메소드
     *
     * @param strDate	yyyyMMdd유형의 Date문자열
     * @param intSize	날짜 증감처리를 위한 정수값
     * @return
     */
    public static String getDate( String strDate, String format ) {
        Calendar calendar = Calendar.getInstance();
        if(strDate == null) {
            return "";
        }
        if(strDate.length() == 8) {
            calendar.set(Integer.parseInt(strDate.substring(0, 4)), Integer.parseInt(strDate.substring(4, 6)) - 1, Integer.parseInt(strDate.substring(6)));
        }else if(strDate.length() == 14) {
            calendar.set(Integer.parseInt(strDate.substring(0, 4)), Integer.parseInt(strDate.substring(4, 6)) - 1, Integer.parseInt(strDate.substring(6, 8)), Integer.parseInt(strDate.substring(8, 10)), Integer.parseInt(strDate.substring(10, 12)), Integer.parseInt(strDate.substring(12)));
        }else if(strDate.length() == 6) {
            strDate = getDate("YYYYMMDD") + strDate;
            calendar.set(Integer.parseInt(strDate.substring(0, 4)), Integer.parseInt(strDate.substring(4, 6)) - 1, Integer.parseInt(strDate.substring(6, 8)), Integer.parseInt(strDate.substring(8, 10)), Integer.parseInt(strDate.substring(10, 12)), Integer.parseInt(strDate.substring(12)));
        }else {
            return strDate;
        }
        return getDate(calendar.getTime(), format);
    }

    /**
     * 오늘 날짜를 기준으로 타입[(연('Y'), 월('M'), 일('D'), 주('W'))]에 따라 증감을 하여 지정한 포멧에 맞게 반환하는 메소드
     *
     * @param format	날짜 포멧  ex) yyyy-MM-dd HH:mm:ss
     * @param type		연(Year) 'Y', 월(Month) 'M', 일(Day) 'D', 주(Week) 'W'
     * @param intSize	정수형 증감값
     * @return
     */
    public static String getDate( String format, char type, int intSize ) {
        return getDate(getDate(Calendar.getInstance().getTime(), "yyyyMMddHHmmss"), format, type, intSize);
    }

    /**
     * 입력한 날짜를 기준으로 타입[(연('Y'), 월('M'), 일('D'), 주('W'))]에 따라 증감을 하여 지정한 포멧에 맞게 반환하는 메소드
     *
     * @param strDate	yyyyMMdd유형의 Date문자열
     * @param format	날짜 포멧 ex) yyyy-MM-dd HH:mm:ss
     * @param type		연(Year) 'Y', 월(Month) 'M', 일(Day) 'D', 주(Week) 'W'
     * @param intSize	정수형 증감값
     * @return
     */
    public static String getDate( String strDate, String format, char type, int intSize	) {
    	Calendar calendar = Calendar.getInstance();

    	if(strDate == null) return "";

    	if(strDate.length() == 8) {
    		calendar.set(Integer.parseInt(strDate.substring(0, 4)), Integer.parseInt(strDate.substring(4, 6)) - 1, Integer.parseInt(strDate.substring(6)));
    	} else if(strDate.length() == 14){
    		calendar.set(Integer.parseInt(strDate.substring(0, 4)), Integer.parseInt(strDate.substring(4, 6)) - 1, Integer.parseInt(strDate.substring(6, 8)), Integer.parseInt(strDate.substring(8, 10)), Integer.parseInt(strDate.substring(10, 12)), Integer.parseInt(strDate.substring(12)));
    	} else if(strDate.length() == 6) {
    		strDate = getDate("YYYYMMDD") + strDate;
    		calendar.set(Integer.parseInt(strDate.substring(0, 4)), Integer.parseInt(strDate.substring(4, 6)) - 1, Integer.parseInt(strDate.substring(6, 8)), Integer.parseInt(strDate.substring(8, 10)), Integer.parseInt(strDate.substring(10, 12)), Integer.parseInt(strDate.substring(12)));
    	} else {
    		return strDate;
    	}

		switch(type) {
			case 89: /* 'Y' */
				calendar.add(Calendar.YEAR, intSize);
				break;
			case 77: /* 'M' */
				calendar.add(Calendar.MONTH, intSize);
				break;
			case 68: /* 'D' */
				calendar.add(Calendar.DATE, intSize);
				break;
			case 87: /* 'W' */
				calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH, intSize);
				break;
			case 'H': /* 시간 */
				calendar.add(Calendar.HOUR, intSize);
				break;
		}
		int li_week = -1;
		if (format.toUpperCase().indexOf("WEEK") != -1) {
			format = format.substring(0, format.toUpperCase().indexOf("WEEK"));
			li_week = calendar.get(Calendar.DAY_OF_WEEK);
		}
		return getDate(calendar.getTime(), format, li_week);
	}

    /**
     * 날짜 유형의 문자열을 Date객체로 변환하여 반환하는 메소드
     *
     * @param strDate	yyyyMMdd유형의 Date문자열
     * @return
     */
    public static Date strToDate( String strDate ) {
        SimpleDateFormat sdf = new SimpleDateFormat(defaultFormat);
        return sdf.parse(strDate , new ParsePosition(0));
    }

    /**
     * 날짜 유형의 문자열 2개 사이의 날짜의 차이를 반환하는 메소드
     *
     * @param fromDate	시작 일짜
     * @param toDate	종료 일짜
     * @return
     */
    public static int getDayBetween( String fromDate, String toDate	) {
        return getDayBetween(strToDate(fromDate), strToDate(toDate));
    }

    /**
     * Date객체의 2개 사이의 날짜의 차이를 반환하는 메소드
     *
     * @param fromDate	시작 일짜
     * @param toDate	종료 일짜
     * @return
     */
    public static int getDayBetween( Date fromDate, Date toDate ) {
        Calendar fromCal = Calendar.getInstance();
        Calendar toCal   = Calendar.getInstance();
        Calendar tmpCal  = Calendar.getInstance();

        fromCal.setTime(fromDate);
        toCal.setTime(toDate);

        int nFromYear = fromCal.get(Calendar.YEAR);
        int nToYear   = toCal.get(Calendar.YEAR);
        int nFromDate = fromCal.get(Calendar.DAY_OF_YEAR);
        int nToDate   = toCal.get(Calendar.DAY_OF_YEAR);

        int nCheckDate = 0;
        //이전 년도에대한 일자를 계산한다.
        for (int i=nFromYear ;i<nToYear;i++) {
            tmpCal.set(i,Calendar.DECEMBER,31);
            nCheckDate += tmpCal.get(Calendar.DAY_OF_YEAR);
        }
        return (nCheckDate+nToDate-nFromDate);
    }

    /**
     * 파라미터로 받은 년/월이 몇일로 구성되어있는지를 반환하는 메소드
     *
     * @param year		년을 의미하는 정수값
     * @param month		월을 의미하는 정수값
     * @return
     */
    public static int getDayCount( int year, int month ) {
        int localDays;
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                localDays = 30; break;
            case 2:
                if ( isLeapYear(year)) {
                    localDays = 29;
                } else {
                    localDays = 28;
                }
                break;
            default:
                localDays = 31;
        }
        return localDays;
    }
    
    /**
     * 입력 받은 년도가 윤년인지를 반환하는 메소드
     *
     * @param year	년도를 의미하는 정수값
     * @return
     */
    public static String formatMon() {
    String thisYear = new java.text.SimpleDateFormat ("yyyy").format(new java.util.Date()); //올해 

    int year = Integer.parseInt(thisYear); //숫자로 변환

    int [] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31} ; //1년 12달의 마지막날짜 데이터
     if ( (year%4==0) && !(year%10==0) || (year%400==0) ) { //윤달있으면 2월달을 29일로
      days[1] = 29; 
     }
	return thisYear;

    }
    

    /**
     * 입력 받은 년도가 윤년인지를 반환하는 메소드
     *
     * @param year	년도를 의미하는 정수값
     * @return
     */
    private static boolean isLeapYear( int year ) {
        return (((year % 4) == 0) && (year != 1900));
    }

	/**
	 * get String which formmatted formatter that is given by user.
	 * @param date java.util.Date Class instance
	 * @param format String representation of the date format. For example, "yyyy-MM-dd"
	 */
	public static String format(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * For example, String time = DateTime.getFormatString("yyyy-MM-dd HH:mm:ss");
	 *
	 * @param java.lang.String pattern  "yyyy, MM, dd, HH, mm, ss and more"
	 * @return formatted string representation of current day and time with  your pattern.
	 */
	public static String format(String format) {
		return format(new java.util.Date(), format);
	}


	/**
	 * check date string validation with an user defined format.
	 * @param s date string you want to check.
	 * @param format string representation of the date format. For example, "yyyy-MM-dd".
	 * @return date java.util.Date
	 */
	public static java.util.Date parse(String s, String format)
		throws java.text.ParseException {
		if (s == null)
			throw new java.text.ParseException(
				"date string to check is null",
				0);
		if (format == null)
			throw new java.text.ParseException(
				"format string to check date is null",
				0);

		java.text.SimpleDateFormat formatter =
			new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date date = null;

		try {
			date = formatter.parse(s);
		} catch (java.text.ParseException e) {

			throw new java.text.ParseException(
				" wrong date:\"" + s + "\" with format \"" + format + "\"",
				0);
		}

		if (!format(date, format).equals(s))
			throw new java.text.ParseException(
				"Out of bound date:\""
					+ s
					+ "\" with format \""
					+ format
					+ "\"",
				0);
		return date;
	}

	/**
	 * check date string validation with an user defined format.
	 * @param s date string you want to check.
	 * @param format string representation of the date format. For example, "yyyy-MM-dd".
	 * @return boolean true 날짜 형식이 맞고, 존재하는 날짜일 때
	 *                 false 날짜 형식이 맞지 않거나, 존재하지 않는 날짜일 때
	 */
	public static boolean isValid(String s, String format) {

		try {
			parse(s, format);
		} catch (java.text.ParseException e) {
			return false;
		}
		return true;
	}

	/**
	 *
	 * 인수로 주어진 java.util.Date의 일을 숫자로 반환하는 메소드
	 *
	 * @param java.util.Date
	 * @return int
	 */
	public static int getDay(Date date) {
		return getNumber(date, "dd");
	}

	/**
	 * 오늘이 몇일인지 숫자로 리턴한다.
	 * @return int
	 */
	public static int getDay(){
		return getNumber(new java.util.Date(), "dd");
	}

	/**
	 *
	 * 인수로 주어진 java.util.Date의 년을 숫자로 반환하는 메소드
	 *
	 * @param java.util.Date
	 * @return int
	 */
	public static int getYear(Date date) {
		return getNumber(date, "yyyy");
	}

	/**
	 * 오늘이 몇년인지 반환하는 메소드
	 * @return int
	 */
	public static int getYear(){
		return getNumber(new java.util.Date(), "yyyy");
	}

	/**
	 *
	 * 인수로 주어진 java.util.Date의 월을 숫자로 반환하는 메소드
	 *
	 * @param java.util.Date
	 * @return int
	 */
	public static int getMonth(Date date) {
		return getNumber(date, "MM");
	}

	/**
	 * 오늘이 몇월인지 반환하는 메소드
	 * @return int
	 */
	public static int getMonth(){
		return getNumber(new java.util.Date(), "MM");
	}

	/**
	 * 인수로 주어진 java.util.Date 를 숫자로 parsing하여 반환하는 메소드
	 *
	 * @param date
	 * @param format 숫자로 parsing 할때 숫자형식의 format이여야 한다("yyyyMMdd")
	 * @return int
	 */
	public static int getNumber(Date date, String format) {
		String dateString = format(date, format);
		return Integer.parseInt(dateString);
	}

	/**
	 * 오늘을 년월일 형태로 숫자로 반환하는 메소드 ex)20030408
	 * @return int
	 */
	public static int getNumber(){
		return Integer.parseInt(format("yyyyMMdd"));
	}

	/**
	 * 오늘을 년월일시분초까지 숫자로 반환하는 메소드 ex)20030408160912
	 * @return
	 */
	public static double getLongNumber(){
		return Double.parseDouble(format("yyyyMMddHHmmss"));
	}

	/**
	 * 현재 날짜가 해당년의 몇번째 주인지를 반환하는 메소드
	 * @return
	 */
	public static String getWeekOfYear() {
		Calendar cal = Calendar.getInstance();
		int week = cal.get(Calendar.WEEK_OF_YEAR);

		return Integer.toString(week);
	}

	/**
	 * return days between two date strings with user defined format.
	 * @param s date string you want to check.
	 * @param format string representation of the date format. For example, "yyyy-MM-dd".
	 * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 요일을 리턴
	 *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
	 *          0: 일요일 (java.util.Calendar.SUNDAY 와 비교)
	 *          1: 월요일 (java.util.Calendar.MONDAY 와 비교)
	 *          2: 화요일 (java.util.Calendar.TUESDAY 와 비교)
	 *          3: 수요일 (java.util.Calendar.WENDESDAY 와 비교)
	 *          4: 목요일 (java.util.Calendar.THURSDAY 와 비교)
	 *          5: 금요일 (java.util.Calendar.FRIDAY 와 비교)
	 *          6: 토요일 (java.util.Calendar.SATURDAY 와 비교)
	 * 예) String s = "2000-05-29";
	 *  int dayOfWeek = whichDay(s, "yyyy-MM-dd");
	 *  if (dayOfWeek == java.util.Calendar.MONDAY)
	 *      log.debug(" 월요일: " + dayOfWeek);
	 *  if (dayOfWeek == java.util.Calendar.TUESDAY)
	 *      log.debug(" 화요일: " + dayOfWeek);
	 */
	public static int whichDay(String s, String format)
		throws java.text.ParseException {
		java.text.SimpleDateFormat formatter =
			new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
		java.util.Date date = parse(s, format);

		java.util.Calendar calendar = formatter.getCalendar();
		calendar.setTime(date);
		return calendar.get(java.util.Calendar.DAY_OF_WEEK);
	}

	/**
	 * 해당일에 대하여 무슨 요일인지 반환하는 메소드
	 * 리턴은 월, 화, 수, 목.....으로 된다.
	 * @param java.util.Date
	 * @return String
	 */
	public static String getDayOfTheWeek(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("E", Locale.KOREA);
		return sdf.format(date);
	}

	/**
	 * 오늘이 무슨 요일인지 반환하는 메소드
	 * 리턴은 월, 화, 수, 목.....으로 된다.
	 * @param java.util.Date
	 * @return String
	 */
	public static String getTodayOfTheWeek() {
		return getDayOfTheWeek(new Date());
	}

	/**
	 * return add day to date strings with user defined format.
	 * @param String date string
	 * @param int 더할 일수
	 * @param format string representation of the date format. For example, "yyyy-MM-dd".
	 * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 일수 더하기
	 *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
	 */
	public static String addDays(String s, int day, String format)
		throws java.text.ParseException {

		java.util.Date date = parse(s, format);

		int yyyy = Integer.parseInt(format(date, "yyyy"));
		int MM = Integer.parseInt(format(date, "MM"));
		int dd = Integer.parseInt(format(date, "dd"));
		Calendar c = new GregorianCalendar(yyyy, MM - 1, dd + day);
		return format(c.getTime(), format);
	}

	/**
	 * return add month to date strings with user defined format.
	 * @param String date string
	 * @param int 더할 월수
	 * @param format string representation of the date format. For example, "yyyy-MM-dd".
	 * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 월수 더하기
	 *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
	 */
	public static String addMonths(String s, int addMonth, String format)
		throws Exception {
		try {
			java.util.Date date = parse(s, format);

			int yyyy = Integer.parseInt(format(date, "yyyy"));
			int MM = Integer.parseInt(format(date, "MM"));
			int dd = Integer.parseInt(format(date, "dd"));
			Calendar c = new GregorianCalendar(yyyy, MM - 1 + addMonth, dd);
			return format(c.getTime(), format);
		} catch (ParseException pe) {
			throw new Exception(pe);
		}
	}

	/**
	 * return add month to date strings with user defined format.
	 * @param String date string
	 * @param int 더할 년수
	 * @param format string representation of the date format. For example, "yyyy-MM-dd".
	 * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 년수 더하기
	 *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
	 */
	public static String addYears(String s, int year, String format)
		throws java.text.ParseException {

		java.util.Date date = parse(s, format);

		int yyyy = Integer.parseInt(format(date, "yyyy"));
		int MM = Integer.parseInt(format(date, "MM"));
		int dd = Integer.parseInt(format(date, "dd"));
		Calendar c = new GregorianCalendar(yyyy + year, MM - 1, dd);
		return format(c.getTime(), format);
	}

	/**
	 * 두 날짜의 차이를 long으로 반환하는 메소드
	 *
     * @param fromDate	시작 일짜
     * @param toDate	종료 일짜
	 * @return
	 */
	public static long getDifferenceDate( Date fromDate, Date toDate ) {
		return fromDate.getTime() - toDate.getTime();
	}

	/**
	 * return days between two date strings with user defined format.
	 * @param String from date string
	 * @param String to date string
	 * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 2개 일자 사이의 일자 리턴
	 *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
	 */
	public static int daysBetween(String from, String to, String format)
		throws java.text.ParseException {

		java.util.Date d1 = parse(from, format);
		java.util.Date d2 = parse(to, format);

		long duration = getDifferenceDate(d1, d2);

		return (int) (duration / (1000 * 60 * 60 * 24));
		// seconds in 1 day
	}

	/**
	 * return age between two date strings with user defined format.
	 * @param String from date string
	 * @param String to date string
	 * @param format string representation of the date format. For example, "yyyy-MM-dd".
	 * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 2개 일자 사이의 나이 리턴
	 *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
	 */
	public static int ageBetween(String from, String to, String format)
		throws java.text.ParseException {
		return (int) (daysBetween(from, to, format) / 365);
	}

	public static int monthsBetween(String from, String to, String format)
		throws java.text.ParseException {
		return (int) (daysBetween(from, to, format) / 30);
	}

	/**
	 * 해당월의 첫날을 가져온다
	 * @param date String
	 * @param format String
	 * @return int
	 */
	public static int getFirstDay(String date, String format)
		throws ParseException {
		Date dt = parse(date, format);
		int yyyy = Integer.parseInt(format(dt, "yyyy"));
		int MM = Integer.parseInt(format(dt, "MM"));
		int dd = Integer.parseInt(format(dt, "dd"));
		Calendar c = new GregorianCalendar(yyyy, MM - 1, dd);
		return c.getActualMinimum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 해당월의 마지막날을 가져온다
	 * @param date String
	 * @param format String
	 * @return int
	 */
	public static int getLastDay(String date, String format)
		throws ParseException {

		Date dt = parse(date, format);
		int yyyy = Integer.parseInt(format(dt, "yyyy"));
		int MM = Integer.parseInt(format(dt, "MM"));
		int dd = Integer.parseInt(format(dt, "dd"));
		Calendar c = new GregorianCalendar(yyyy, MM - 1, dd);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 해당월의 첫날을 포맷에 맞게 가져온다
	 * @param date String
	 * @param format String
	 * @param reForamt
	 * @return String
	 */
	public static String getFirstDay(
		String date,
		String format,
		String reFormat)
		throws ParseException {

		Date dt = parse(date, format);
		int yyyy = Integer.parseInt(format(dt, "yyyy"));
		int MM = Integer.parseInt(format(dt, "MM"));
		int dd = Integer.parseInt(format(dt, "dd"));
		Calendar c = new GregorianCalendar(yyyy, MM - 1, dd);
		int minday = c.getActualMinimum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, minday);
		return format(c.getTime(), reFormat);
	}

	public static String getPrevMonthFirstDay(
			String date,
			String format,
			String reFormat)
			throws ParseException {

			Date dt = parse(date, format);
			int yyyy = Integer.parseInt(format(dt, "yyyy"));
			int MM = Integer.parseInt(format(dt, "MM"))-1;
			int dd = Integer.parseInt(format(dt, "dd"));
			Calendar c = new GregorianCalendar(yyyy, MM - 1, dd);
			int minday = c.getActualMinimum(Calendar.DAY_OF_MONTH);
			c.set(Calendar.DAY_OF_MONTH, minday);
			return format(c.getTime(), reFormat);
		}

	public static String getPrevMonthLastDay(
			String date,
			String format,
			String reFormat)
			throws ParseException {

			Date dt = parse(date, format);
			int yyyy = Integer.parseInt(format(dt, "yyyy"));
			int MM = Integer.parseInt(format(dt, "MM"))-1;
			int dd = Integer.parseInt(format(dt, "dd"));
			Calendar c = new GregorianCalendar(yyyy, MM - 1, dd);
			int maxday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			c.set(Calendar.DAY_OF_MONTH, maxday);
			return format(c.getTime(), reFormat);
		}
	/**
	 * 해당월의 마지막날을 포맷에 맞게 가져온다
	 * @param date String
	 * @param format String
	 * @param reForamt
	 * @return String
	 */
	public static String getLastDay(
		String date,
		String format,
		String reFormat)
		throws ParseException {

		Date dt = parse(date, format);
		int yyyy = Integer.parseInt(format(dt, "yyyy"));
		int MM = Integer.parseInt(format(dt, "MM"));
		int dd = Integer.parseInt(format(dt, "dd"));
		Calendar c = new GregorianCalendar(yyyy, MM - 1, dd);
		int maxday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, maxday);
		return format(c.getTime(), reFormat);
	}

	/**
	 * @return formatted string representation of current day with  "yyyyMMdd".
	 */
	public static String getShortDateString() {
		return format(new java.util.Date(), "yyyyMMdd");
	}

	/**
	 * @return formatted string representation of current time with  "HHmmss".
	 */
	public static String getShortTimeString() {
		return format(new java.util.Date(), "HHmmss");
	}

	public static Date parseDate(String value, String format) {
		Date date;
		try {
			date = DateUtils.parse(value, format);
		} catch (ParseException e) {
			date = new Date();
		}
		return date;
	}

	public static Date parseDate(String value) {
		Date date = parseDate(value, "yyyy-MM-dd");
		return date;
	}
	

	public static String changeDateFormat(String oldFormat, String newFormat, String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
			Date d = sdf.parse(date);
			sdf.applyPattern(newFormat);
			return sdf.format(d);
		} catch (ParseException e) {
			return date;
		}
	}

	/**
	 * 다음 월
	 * 
	 * @param cSaleDt
	 * @return
	 */
	public static String NextMonthday(String cSaleDt) {
		String cNextCalcYm = "";
		int next_year = 0;
		int next_month = 0;
		String month = "";
		if (cSaleDt.length() == 8) {
			next_year = Integer.parseInt(cSaleDt.substring(0, 4));
			next_month = Integer.parseInt(cSaleDt.substring(4, 6)) + 1;
			if (next_month == 13) {
				next_year++;
				next_month = 1;
			}
			if (next_month < 10) {
				month = "0" + next_month;
			} else {
				month = "" + next_month;
			}
		}

		cNextCalcYm = ((Object) next_year).toString() + month;

		return cNextCalcYm;
	}

	/**
	 * 다음 월
	 * 
	 * @param cSaleDt
	 * @return
	 */
	public static String NextMonthday2(String cSaleDt) {
		String cNextCalcYm = "";
		int next_year = 0;
		int next_month = 0;
		String month = "";
		if (cSaleDt.length() == 8) {
			next_year = Integer.parseInt(cSaleDt.substring(0, 4));
			next_month = Integer.parseInt(cSaleDt.substring(4, 6)) + 1;
			if (next_month == 13) {
				next_year++;
				next_month = 1;
			}
			if (next_month < 10) {
				month = "0" + next_month;
			} else {
				month = "" + next_month;
			}
		}

		cNextCalcYm = ((Object) next_year).toString() + month + cSaleDt.substring(6);

		return cNextCalcYm;
	}

	/**
	 * 일 수 구하기
	 * 
	 * @param cLastCalcDt
	 * @param cSttlDt
	 * @return
	 */
	public static int betweenDay(String cLastCalcDt, String cSttlDt) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(cSttlDt.substring(0, 4)), Integer.parseInt(cSttlDt.substring(4, 6)) - 1, Integer.parseInt(cSttlDt.substring(6, 8)));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		Calendar cal2 = Calendar.getInstance();
		cal2.set(Integer.parseInt(cLastCalcDt.substring(0, 4)), Integer.parseInt(cLastCalcDt.substring(4, 6)) - 1, Integer.parseInt(cLastCalcDt.substring(6, 8)));
		cal2.set(Calendar.HOUR_OF_DAY, 0);
		cal2.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.SECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);

		int day = -1;
		while (!cal2.after(cal)) {
			cal2.add(Calendar.DATE, 1);
			day++;
		}
		return day;
	}

	 public static boolean chkYun(int year) {
         boolean isYun=false;
         
         if( (0 == (year % 4) && 
                  0 != (year %100)) ||  
                  0 == year%400 ){
                 isYun = true;
         }else{
                 isYun = false;
         }
         return isYun;
	 }

	 /**
		 * 다음 월
		 * 
		 * @param cSaleDt
		 * @return
		 */
		@SuppressWarnings("unused")
		public static String Nextday(String cSaleDt) {
			String cNextCalcYm = "";
			int next_year = 0;
			int next_month = 0;
			String month = "";
			int next_day = 0;
			if (cSaleDt.length() == 8) {
				next_year = Integer.parseInt(cSaleDt.substring(0, 4));
				next_month = Integer.parseInt(cSaleDt.substring(4, 6)) + 1;
				next_day = Integer.parseInt(cSaleDt.substring(6, 8)) - 1;
				if (next_month == 13) {
					next_year++;
					next_month = 1;
				}
				if (next_month < 10) {
					month = "0" + next_month;
				} else {
					month = "" + next_month;
				}
				
			}

			cNextCalcYm = ((Object) next_year).toString() + month + cSaleDt.substring(6);

			return cNextCalcYm;
		}
		
		/**
		 * return add day to date strings with user defined format.
		 * @param String date string
		 * @param int 빼기 일수
		 * @param format string representation of the date format. For example, "yyyy-MM-dd".
		 * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 일수 빼기
		 *           형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
		 */
		public static String mineDays(String s, int day, String format)
			throws java.text.ParseException {

			java.util.Date date = parse(s, format);

			int yyyy = Integer.parseInt(format(date, "yyyy"));
			int MM = Integer.parseInt(format(date, "MM"));
			int dd = Integer.parseInt(format(date, "dd"));
			Calendar c = new GregorianCalendar(yyyy, MM - 1, dd - day);
			return format(c.getTime(), format);
		}
		
		
		/**
		 * 날짜 계산기
		 * 예) getCalsDate(2, -6, "yyyyMMdd") : 6개월 이전 날짜 반환
		 * 
		 */
		@SuppressWarnings("static-access")
		public static String getCalsDate(int y, int z, String date_type) 
	            throws Exception{
	        String resultDate = "";
	        
	        Calendar cal = Calendar.getInstance(Locale.KOREAN);
	        TimeZone timezone = cal.getTimeZone();
	        timezone = timezone.getTimeZone("Asia/Seoul");
	        cal = Calendar.getInstance(timezone);
	        
	        cal.add(y, z);
	        Date currentTime = cal.getTime();
	        SimpleDateFormat formatter = new SimpleDateFormat(date_type, 
	                Locale.KOREAN);
	        resultDate = formatter.format(currentTime);
	                
	        return resultDate;
	    }
		
		
		
		
		
}
