package com.task.demo.helper;

import java.util.UUID;

public class UuidHelper {

	public static String getUUid() {
		String string = UUID.randomUUID().toString();
		return string;
	}

}
