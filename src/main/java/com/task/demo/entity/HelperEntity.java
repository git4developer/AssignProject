package com.task.demo.entity;

import com.task.demo.helper.EpocHelper;
import com.task.demo.helper.IsoHelper;
import com.task.demo.helper.UuidHelper;

public class HelperEntity {
	
	
	public static String getUUild() {
		           return UuidHelper.getUUid();
	}
	
	public static Long getEpoc() {
		 return EpocHelper.getEpoc();
	}
	
	public static String getIso() {
		return IsoHelper.getIso();
	}

}
