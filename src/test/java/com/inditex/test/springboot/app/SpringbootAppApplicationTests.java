package com.inditex.test.springboot.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
		ProductRate rate = zaraProductRate.findMostPriorityPriceBySelection(entry);
		assertNotNull(rate);

		assertEquals(null, rate.getProductId());
		assertEquals(null, rate.getPriceList());
		assertEquals(null, rate.getBrandId());
		assertEquals(null, rate.getStartDate());
		assertEquals(null, rate.getEndDate());
		assertEquals(null, rate.getPrice());

	}

	@Test
	@DisplayName("Return empty rate when priceList is not selected")
	void emptyProductRateForNoPriceSelection() {
		RateSelection entry = RateSelection.create("14-03-2022", "10:00", 34455L, 1L);

		when(pricesRepository.findPricesBySelectionOrderedByPrioriry(entry.getDate(), entry.getProduct(),
				entry.getBrand())).thenReturn(null);

		ProductRate rate = zaraProductRate.findMostPriorityPriceBySelection(entry);

		assertNotNull(rate);

		assertEquals(null, rate.getProductId());
		assertEquals(null, rate.getPriceList());
		assertEquals(null, rate.getBrandId());
		assertEquals(null, rate.getStartDate());
		assertEquals(null, rate.getEndDate());
		assertEquals(null, rate.getPrice());

	}

	@Test
	@DisplayName("petición a las 10:00 del día 14 del producto 35455   para la brand 1")
	void checkProductRatesForDate14at10Oclock() {
		RateSelection entry = RateSelection.create("14-06-2020", "10:00", 34455L, 1L);

		when(pricesRepository.findPricesBySelectionOrderedByPrioriry(entry.getDate(), entry.getProduct(),
				entry.getBrand())).thenReturn(TestData.PRICE_RESULT_LIST_FOR_PETITION_1);

		ProductRate rate = zaraProductRate.findMostPriorityPriceBySelection(entry);

		assertEquals(35455L, rate.getProductId());
		assertEquals(1, rate.getPriceList());
		assertEquals(1, rate.getBrandId());
		assertEquals(convertToDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0)), rate.getStartDate());
		assertEquals(convertToDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59)), rate.getEndDate());
		assertEquals(35.50, rate.getPrice());
	}

	private Date convertToDate(LocalDateTime dateToConvert) {
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}

}
