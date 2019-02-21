package com.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	public static Timestamp getTms(){
		Timestamp tms = new Timestamp(new Date().getTime());
		return tms;
	}
	
	private static String format(Date date,String pattern){
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.format(date);
	}
	
	public static String now(){
		return format(new Date(),"yyyyMMddHHmmss");
	}
	
	public static String time(long period){
		long t = new Date().getTime() + period;
		return format(new Date(t),"yyyyMMddHHmmss");
	}
}
