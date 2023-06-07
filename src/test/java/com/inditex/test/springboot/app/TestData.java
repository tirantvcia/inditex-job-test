package com.inditex.test.springboot.app;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.inditex.test.springboot.app.dto.response.ProductRateResponse;
import com.inditex.test.springboot.app.models.Brand;
import com.inditex.test.springboot.app.models.Price;

public class TestData {
	
	private static final String EUR_CURRENCY = "EUR";
	public static final long BRAND_ID_1 = 1L;
	public static final long PRODUCT_ID_35455 = 35455l;
	
	public static final Date TEST_1_DATE_CRITERIA = convertToDate(LocalDateTime.of(2020, 6, 14, 10, 0, 0));
	public static final Date TEST_2_DATE_CRITERIA = convertToDate(LocalDateTime.of(2020, 6, 14, 16, 0, 0));
	public static final Date TEST_3_DATE_CRITERIA = convertToDate(LocalDateTime.of(2020, 6, 14, 21, 0, 0));
	public static final Date TEST_4_DATE_CRITERIA = convertToDate(LocalDateTime.of(2020, 6, 15, 10, 0, 0));
	public static final Date TEST_5_DATE_CRITERIA = convertToDate(LocalDateTime.of(2020, 6, 16, 21, 0, 0));
	public static final Date TEST_DATE_CRITERIA_NOT_MATCHED = convertToDate(LocalDateTime.of(2022, 6, 16, 21, 0, 0));
	
	public static final Brand BRAND_ZARA = new Brand(1L, "ZARA");
	public static final Price PRICE_LIST_1 = new Price(1L, BRAND_ZARA,
			convertToDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0)),
			convertToDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59)), PRODUCT_ID_35455, 0, 35.5, EUR_CURRENCY);

	public static final Price PRICE_LIST_2 = new Price(2L, BRAND_ZARA,
			convertToDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0)),
			convertToDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0)), PRODUCT_ID_35455, 1, 25.45, EUR_CURRENCY);

	public static final Price PRICE_LIST_3 = new Price(3L, BRAND_ZARA,
			convertToDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0)),
			convertToDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0)), PRODUCT_ID_35455, 1, 30.50, EUR_CURRENCY);

	public static final Price PRICE_LIST_4 = new Price(4L, BRAND_ZARA,
			convertToDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0)),
			convertToDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59)), PRODUCT_ID_35455, 1, 38.95, EUR_CURRENCY);

	public static final List<Price> PRICE_RESULT_LIST_FOR_PETITION_1 = Stream.of(PRICE_LIST_1)
			.collect(Collectors.toList());
	public static final List<Price> PRICE_RESULT_LIST_FOR_PETITION_2 = Stream.of(PRICE_LIST_2, PRICE_LIST_1)
			.collect(Collectors.toList());

	public static final ProductRateResponse PRODUCT_RATE_1 = ProductRateResponse.create(PRICE_LIST_1);
	public static final ProductRateResponse PRODUCT_RATE_2 = ProductRateResponse.create(PRICE_LIST_2);
	public static final ProductRateResponse PRODUCT_RATE_3 = ProductRateResponse.create(PRICE_LIST_3);
	public static final ProductRateResponse PRODUCT_RATE_4 = ProductRateResponse.create(PRICE_LIST_4);
	


	
	private static Date convertToDate(LocalDateTime dateToConvert) {
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}
	


}
