package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static Date format(String date){
		return format(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	public static String format(Date date,String pattern) {
		if(date==null) return null;
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}
	
	public static Date format(String date,String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	
	public static Date now(String pattern){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(format(date,pattern));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	
	//获取相差天数
	public static int getDays (Date start,Date end){
		int data=0;
		try {
			long l=end.getTime()-start.getTime();
			long day=l/(24*60*60*1000);
			if (day != 0)
				data=(int)day;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return data;
	}
	
	
	public static String getDifferdata (Date date){
		String data="";
		try {
			Date now = new Date();
			long l=now.getTime()-date.getTime();
			long day=l/(24*60*60*1000);
			long hour=(l/(60*60*1000)-day*24);
			long min=((l/(60*1000))-day*24*60-hour*60);
			if (day != 0)
				data=day+"天前";
			if(day==0 && hour!=0)
				data=hour+"小时前";
			if(day==0 && hour==0 && min!=0)
				data=min+"分钟前";
			if(day==0&&hour==0&&min==0)
				data="当前";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return data;
	}
}
