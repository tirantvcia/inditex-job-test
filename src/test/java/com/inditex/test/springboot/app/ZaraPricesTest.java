package com.inditex.test.springboot.app;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ZaraPricesTest {

	@Test
	@DisplayName("No rate selected when entry is null")
	void emptyProductRateForNullSelection() {
		RateSelection entry = null;
		ProductRate rate = ZaraProductRate.getProductRate(entry);
		assertNull(rate);
	}
	
	@Test
	@DisplayName("petición a las 10:00 del día 14 del producto 35455   para la brand 1")
	void checkProductRatesForDate14at10Oclock() {
		RateSelection entry = new RateSelection(LocalDate.of(2014, 3, 14),
				LocalTime.of(10,00), 34455, 1);
		ProductRate rate = ZaraProductRate.getProductRate(entry);
		assertNotNull(rate);
		assertEquals(34455, rate.getProductId());
		assertEquals(1, rate.getPriceList());
		assertEquals(Brand.ZARA, rate.getBrand());
		assertEquals(convertToDate(LocalDateTime.of(2020, 6, 14, 0, 0)), rate.getStartDate());
		assertEquals(convertToDate(LocalDateTime.of(2020, 6, 12, 23, 59)), rate.getEndDate());
		assertEquals(35.50, rate.getPrice());
	}
	
	private Date convertToDate(LocalDateTime dateToConvert) {
	    return java.util.Date
	      .from(dateToConvert.atZone(ZoneId.systemDefault())
	      .toInstant());
	}

}
