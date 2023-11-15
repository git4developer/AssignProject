package com.task.demo.helper;

import java.util.Date;

public class EpocHelper {

	public static Long getEpoc() {
		Date date = new Date();
		Long epoc = date.getTime() / 1000;
		return epoc;

	}

}
