package com.clinic.utils;

import java.text.*;
import java.util.*;
import java.math.*;

public class DateUtils {

	public DateUtils() {
		// TODO Auto-generated constructor stub
	}

	public static String currentDateTimeMySQLFormat () {
	      DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      return df.format(new Date());
	}
	
	public static String currentDateMySQLFormat () {
	      DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	      return df.format(new Date());
	}
	
	
    public static boolean isValidMySQLDate(String datestr) {
    	return isValidFormat("yyyy-MM-dd", datestr);
    }
    
    public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }

	public static String MySQLTimeStamp_To_Date (String dt) throws ParseException {

		if (dt == null) {return "-";}
	
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		 
		return df2.format(df1.parse(dt));
	}
	
	public static int MySQLTimeStamp_Diff_Days(String dt1,  String dt2) throws ParseException {
		
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		long d1 = df1.parse(dt1).getTime();
		long d2 = df1.parse(dt2).getTime();

		int diff = (int)((d1-d2)/(1000*60*60*24));
		
		return (diff >= 0)?diff:0-diff;
	}

	public static int MySQLTimeStamp_Diff_With_Today_Days(String dt1) throws ParseException {
		
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		long d1 = df1.parse(dt1).getTime();
		Date cd = new Date();
		long d2 = cd.getTime();

		int diff = (int)((d1-d2)/(1000*60*60*24));
		
		return (diff >= 0)?diff:0-diff;
	}
	/*
	public static HTMLToMySQLDate (string dt) {
		
	}
	*/
	public static String MySQLTimeStamp_To_DD_MMM_YYYY (String dt) throws ParseException {

		if (dt == null) {return "-";}
	
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy");
		 
		return df2.format(df1.parse(dt));
	}
	public static String MySQLTimeStamp_To_DD_MM_YYYY (String dt) throws ParseException {

		if (dt == null) {return "-";}
	
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
		 
		return df2.format(df1.parse(dt));
	}
	public static String MySQDate_To_DD_MMM_YYYY (String dt) throws ParseException {

		if (dt == null) {return "-";}
		
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy");
		 
		return df2.format(df1.parse(dt));
	}
	
	public static String[] getLastThreeMonths() {
		String mths[] = new String[3];

	    DateFormat df1 = new SimpleDateFormat("MMMMMMMMM yyyy");
	    Calendar now = Calendar.getInstance();
	    for (int i = 0; i < 3; i++) {
		    now.add( GregorianCalendar.MONTH, -1 );
		    mths[i] =  df1.format(now.getTime());
	    }
	  
		return mths;
	}
	
	public static String getFirstDayOfMMMMMMMMMyyyyMth(String dt) throws ParseException {
	    DateFormat df1 = new SimpleDateFormat("MMMMMMMMM yyyy");
	    DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
	    
	    return df2.format(df1.parse(dt));
	}
	public static String getLastDayOfMMMMMMMMMyyyyMth(String dt) throws ParseException {
	    DateFormat df1 = new SimpleDateFormat("MMMMMMMMM yyyy");
	    DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
	    
	    Date fd = df1.parse(dt);
	    
	    Calendar now = Calendar.getInstance();
	    now.setTime(fd);
	    now.add(GregorianCalendar.MONTH, 1);
	    now.add(GregorianCalendar.DAY_OF_MONTH, -1);
	    
	    return df2.format(now.getTime());
	}
	
	public static String getMonthOfMySQLDate(String dt) throws ParseException {
		
	    DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
	    DateFormat df2 = new SimpleDateFormat("MMMMMMMMM");
	    
	    return df2.format(df1.parse(dt));
	}
}
