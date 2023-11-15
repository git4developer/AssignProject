package com.task.demo.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IsoHelper {

	public static String getIso() {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;

		String format = ldt.format(dtf);
		return format;
	}

}
