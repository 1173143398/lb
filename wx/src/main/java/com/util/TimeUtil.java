package com.util;

import java.sql.Timestamp;
import java.util.Date;

public class TimeUtil {

	public static Timestamp getTms(){
		Timestamp tms = new Timestamp(new Date().getTime());
		return tms;
	}
}
