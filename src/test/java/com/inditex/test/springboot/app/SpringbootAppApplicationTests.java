package com.inditex.test.springboot.app;

import static org.junit.jupiter.api.Assertions.*;
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

	private static final long BRAND_ID_1 = 1L;
	private static final long PRODUCT_ID_34455 = 34455L;

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
		String date = "14-03-2022";
		String time = "10:00";
		RateSelection entry = RateSelection.create(date, time, PRODUCT_ID_34455, BRAND_ID_1);

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
	@DisplayName("petición a las 10:00 del día 14 del producto 35455 para la brand 1")
	void checkProductRatesForDate14at10Oclock() {
		String date = "14-06-2020";
		String time = "10:00";
		RateSelection entry = RateSelection.create(date, time, PRODUCT_ID_34455, 1L);
		when(pricesRepository.findPricesBySelectionOrderedByPrioriry(entry.getDate(), entry.getProduct(),
				entry.getBrand())).thenReturn(TestData.PRICE_RESULT_LIST_FOR_PETITION_1);
		ProductRate rate = zaraProductRate.findMostPriorityPriceBySelection(entry);
		assertEquals(TestData.PRODUCT_RATE_1.getProductId(), rate.getProductId());
		assertEquals(TestData.PRODUCT_RATE_1.getPriceList(), rate.getPriceList());
		assertEquals(TestData.PRODUCT_RATE_1.getBrandId(), rate.getBrandId());
		assertTrue(TestData.PRODUCT_RATE_1.getStartDate().equals(rate.getStartDate()));
		assertTrue(TestData.PRODUCT_RATE_1.getEndDate().equals(rate.getEndDate()));
		assertEquals(TestData.PRODUCT_RATE_1.getPrice(), rate.getPrice());
	}

	private Date convertToDate(LocalDateTime dateToConvert) {
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}

}
