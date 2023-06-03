package com.inditex.test.springboot.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.inditex.test.springboot.app.data.ProductRate;
import com.inditex.test.springboot.app.data.RateSelection;
import com.inditex.test.springboot.app.repositories.PricesRepository;
import com.inditex.test.springboot.app.services.ZaraProductRateService;

@SpringBootTest
class SpringbootAppApplicationTests {

	@MockBean
	PricesRepository pricesRepository;
	
	@Autowired
	ZaraProductRateService zaraProductRate;

	
	@Test
	@DisplayName("No rate selected when entry is null")
	void emptyProductRateForNullSelection() {
		RateSelection entry = null;
		ProductRate rate = zaraProductRate.getProductRate(entry);
		assertNotNull(rate);
		
		assertEquals(null, rate.getProductId());
		assertEquals(null, rate.getPriceList());
		assertEquals(null, rate.getBrand());
		assertEquals(null, rate.getStartDate());
		assertEquals(null, rate.getEndDate());
		assertEquals(null, rate.getPrice());

	}
	
	@Test
	@DisplayName("Return empty rate when priceList is not selected")
	void emptyProductRateForNoPriceSelection() {
		RateSelection entry = new RateSelection(LocalDate.of(2022, 3, 14),
				LocalTime.of(10,00), 34455L, 1L);
		
		when(pricesRepository.findFirstPriceRateBySelectionEntry(entry.getDate(), entry.getProduct(), entry.getBrand())).thenReturn(Optional.ofNullable(null));
		
		ProductRate rate = zaraProductRate.getProductRate(entry);
		
		assertNotNull(rate);
		
		assertEquals(null, rate.getProductId());
		assertEquals(null, rate.getPriceList());
		assertEquals(null, rate.getBrand());
		assertEquals(null, rate.getStartDate());
		assertEquals(null, rate.getEndDate());
		assertEquals(null, rate.getPrice());

	}
	
	@Test
	@DisplayName("petición a las 10:00 del día 14 del producto 35455   para la brand 1")
	void checkProductRatesForDate14at10Oclock() {
		RateSelection entry = new RateSelection(LocalDate.of(2020, 6, 14),
				LocalTime.of(10,00), 34455L, 1L);
		
		when(pricesRepository.findFirstPriceRateBySelectionEntry(entry.getDate(), entry.getProduct(), entry.getBrand())).thenReturn(Optional.of(TestData.PRICE_LIST_1));
		
		ProductRate rate = zaraProductRate.getProductRate(entry);
		
		assertEquals(35455L, rate.getProductId());
		assertEquals(1, rate.getPriceList());
		assertEquals(1, rate.getBrand().getId());
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
