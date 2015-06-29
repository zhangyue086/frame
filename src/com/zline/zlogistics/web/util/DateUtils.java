package com.zline.zlogistics.web.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
	
	public static String returnDate(int count) {  
	    Calendar strDate = Calendar.getInstance();  
	    strDate.add(strDate.DATE, count);  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	    return sdf.format(strDate.getTime());  
	}  
	/** 
	 * 根据日期获得所在周的日期  
	 * @param mdate 
	 * @return 
	 */  
	@SuppressWarnings("deprecation")  
	public static List<String> dateToWeek(Date mdate) {  
	    int b = mdate.getDay();  
	    Date fdate;  
	    List<String> list = new ArrayList<String>();  
	    Long fTime = mdate.getTime() - b * 24 * 3600000;  
	    for (int a = 1; a <= 7; a++) {  
	        fdate = new Date();  
	        fdate.setTime(fTime + (a * 24 * 3600000));
	        String result = new SimpleDateFormat("yyyy-MM-dd").format(fdate);
	        list.add(a-1, result);  
	    }  
	    return list;  
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * @param createTime
	 * @param string
	 * @return
	 */
	public static Object dateToString(Date createTime, String string) {
		SimpleDateFormat sdf = new SimpleDateFormat(string);
		String dateStr = sdf.format(createTime);
		return dateStr;
	}  
	
	
	
	public static void main(String[] args) {
		System.out.println(dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
	}
}
