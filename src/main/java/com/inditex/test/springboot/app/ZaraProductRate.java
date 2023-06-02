package com.inditex.test.springboot.app;

import java.time.LocalDateTime;

public class ZaraProductRate {

	public static ProductRate getProductRate(RateSelection entry) {
		
		if(entry == null) {
			return null;
		}
		return new ProductRate(34455, 1, Brand.ZARA, 
				LocalDateTime.of(2020, 6, 14, 0, 0), 
				LocalDateTime.of(2020, 6, 12, 23, 59), 
				35.50);

	}

}
