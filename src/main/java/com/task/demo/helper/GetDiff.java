package com.task.demo.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class GetDiff {

	public static Long getDiffHour(String iso) {
		LocalDateTime now = LocalDateTime.now();

		
		String isoTime = iso; // "2023-11-06T15:59:32.255";

		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

		// Parse the ISO time
		LocalDateTime providedTime = LocalDateTime.parse(isoTime, formatter);

		
		long differenceInHours = ChronoUnit.HOURS.between(providedTime, now);
		return differenceInHours;
	}

	public static Long getDiffDay(String iso) {
		LocalDateTime now = LocalDateTime.now();

		
		String isoDateTime = iso; // "2023-11-06T15:59:32.255";

		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

		// Parse the ISO date-time
		LocalDateTime providedDateTime = LocalDateTime.parse(isoDateTime, formatter);

		
		long differenceInDays = ChronoUnit.DAYS.between(providedDateTime, now);

	
	//	System.out.println("Difference in days: " + differenceInDays);
		return differenceInDays;
	}

	public static Long getDiffMonth(String iso) {
		LocalDateTime now = LocalDateTime.now();

		
		String isoDateTime =iso; //"2023-11-06T15:59:32.255";

		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

		
		LocalDateTime providedDateTime = LocalDateTime.parse(isoDateTime, formatter);

		
		long differenceInMonths = ChronoUnit.MONTHS.between(providedDateTime, now);

		// Print the result
		System.out.println("Difference in months: " + differenceInMonths);
		return differenceInMonths;
	}

}
