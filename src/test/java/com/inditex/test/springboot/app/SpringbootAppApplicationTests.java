package com.inditex.test.springboot.app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.inditex.test.springboot.app.data.Brand;
import com.inditex.test.springboot.app.data.ProductRate;
import com.inditex.test.springboot.app.data.RateSelection;
import com.inditex.test.springboot.app.repositories.PricesRepository;
import com.inditex.test.springboot.app.services.ZaraProductRate;
import com.inditex.test.springboot.app.services.ZaraProductRateService;

@SpringBootTest
class SpringbootAppApplicationTests {

	PricesRepository pricesRepository;
	ZaraProductRateService zaraProductRate;

	@BeforeEach
	void setup() {
		pricesRepository = mock(PricesRepository.class);
		zaraProductRate = new ZaraProductRate(pricesRepository);
	}
	
	@Test
	@DisplayName("No rate selected when entry is null")
	void emptyProductRateForNullSelection() {
		RateSelection entry = null;
		ProductRate rate = zaraProductRate.getProductRate(entry);
		assertNull(rate);
	}
	
	@Test
	@DisplayName("petición a las 10:00 del día 14 del producto 35455   para la brand 1")
	void checkProductRatesForDate14at10Oclock() {
		RateSelection entry = new RateSelection(LocalDate.of(2014, 3, 14),
				LocalTime.of(10,00), 34455, 1);
		
		when(pricesRepository.findFirstPriceRateBySelectionEntry(entry)).thenReturn(Optional.of(TestData.PRICE_LIST_1));
		
		ProductRate rate = zaraProductRate.getProductRate(entry);
		
		assertEquals(35455L, rate.getProductId());
		assertEquals(1, rate.getPriceList());
		assertEquals(Brand.ZARA, rate.getBrand());
		assertEquals(convertToDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0)), rate.getStartDate());
		assertEquals(convertToDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59)), rate.getEndDate());
		assertEquals(35.50, rate.getPrice());
	}
	
	private Date convertToDate(LocalDateTime dateToConvert) {
	    return java.util.Date
	      .from(dateToConvert.atZone(ZoneId.systemDefault())
	      .toInstant());
	}


}
