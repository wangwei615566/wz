package com.wz.cashloan.core.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class OrderNoUtil {
	
	private static final String COMMON_DATE = "yyyyMMdd";

	/**
	 * 产生唯一 的序列号
	 * @return
	 */
	public static String getSerialNumber() {
		int hashCode = UUID.randomUUID().toString().hashCode();
		if (hashCode < 0) {
			hashCode = -hashCode;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(COMMON_DATE);
		return sdf.format(new Date()).substring(2, 8)
				+ String.format("%010d", hashCode);
	}
}
