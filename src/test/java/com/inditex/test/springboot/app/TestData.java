package com.inditex.test.springboot.app;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Currency;
import java.util.Date;

import com.inditex.test.springboot.app.data.Brand;
import com.inditex.test.springboot.app.models.Price;

public class TestData {
	public static final Price PRICE_LIST_1 = new Price(1L, Brand.ZARA, 
			convertToDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0)), 
			convertToDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
			, 35455L, 0, 35.5, Currency.getInstance("EUR"));

	public static final Price PRICE_LIST_2 = new Price(2L, Brand.ZARA, 
			convertToDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0)), 
			convertToDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0))
			, 35455L, 1, 25.45, Currency.getInstance("EUR"));
	
	public static final Price PRICE_LIST_3 = new Price(3L, Brand.ZARA, 
			convertToDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0)), 
			convertToDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
			, 35455L, 1, 30.50, Currency.getInstance("EUR"));

	public static final Price PRICE_LIST_4 = new Price(4L, Brand.ZARA, 
			convertToDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0)), 
			convertToDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
			, 35455L, 1, 38.95, Currency.getInstance("EUR"));
	
	private static Date convertToDate(LocalDateTime dateToConvert) {
	    return java.util.Date
	      .from(dateToConvert.atZone(ZoneId.systemDefault())
	      .toInstant());
	}
			
			
}
