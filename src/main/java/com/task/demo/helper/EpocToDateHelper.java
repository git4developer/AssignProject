package com.task.demo.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EpocToDateHelper {

	public static String getEpocIntoDate(Long l) {

		Date date = new Date(l * 1000);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = dateFormat.format(date);
		return format;
		
		
//		    String t1 = "2023-11-06 16:23:01";
//	        String ti2 = "2023-11-06 19:52:40";
//
//	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//	        LocalDateTime dateTime1 = LocalDateTime.parse(t1, formatter);
//	        LocalDateTime dateTime2 = LocalDateTime.parse(ti2, formatter);
//
//	        if (dateTime1.isBefore(dateTime2)) {
//	            System.out.println("Time 1 is earlier.");
//	            System.out.println("Earlier time: " + dateTime1);
//	        } else if (dateTime2.isBefore(dateTime1)) {
//	            System.out.println("Time 2 is earlier.");
//	            System.out.println("Earlier time: " + dateTime2);
//	        } else {
//	            System.out.println("Both times are the same.");
//	        }
		
//		Long longValue = 123456789L; // Your Long value here
//		int intValue = longValue.intValue();

	}

}
