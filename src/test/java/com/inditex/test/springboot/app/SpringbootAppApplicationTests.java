package com.inditex.test.springboot.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.inditex.test.springboot.app.dto.response.ProductRateResponse;
import com.inditex.test.springboot.app.repositories.PricesRepository;
import com.inditex.test.springboot.app.services.ZaraProductRateService;

@SpringBootTest
class SpringbootAppApplicationTests {



	@MockBean
	PricesRepository pricesRepository;

	@Autowired
	ZaraProductRateService zaraProductRate;

	@Test
	@DisplayName("Return empty rate when priceList is not selected")
	void emptyProductRateForNoPriceSelection() {

		when(pricesRepository.findPricesBySelectionOrderedByPrioriry(TestData.TEST_DATE_CRITERIA_NOT_MATCHED, TestData.PRODUCT_ID_35455,
				TestData.BRAND_ID_1)).thenReturn(TestData.PRICE_RESULT_LIST_FOR_PETITION_1);
		
		ProductRateResponse rate = zaraProductRate.findMostPriorityPriceBySelection(TestData.TEST_1_DATE_CRITERIA, TestData.PRODUCT_ID_35455,
				TestData.BRAND_ID_1);

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
		
		when(pricesRepository.findPricesBySelectionOrderedByPrioriry(TestData.TEST_1_DATE_CRITERIA, TestData.PRODUCT_ID_35455,
				TestData.BRAND_ID_1)).thenReturn(TestData.PRICE_RESULT_LIST_FOR_PETITION_1);
		
		ProductRateResponse rate = zaraProductRate.findMostPriorityPriceBySelection(TestData.TEST_1_DATE_CRITERIA, TestData.PRODUCT_ID_35455,
				TestData.BRAND_ID_1);
		
		
		
		assertEquals(TestData.PRODUCT_RATE_1.getProductId(), rate.getProductId());
		assertEquals(TestData.PRODUCT_RATE_1.getPriceList(), rate.getPriceList());
		assertEquals(TestData.PRODUCT_RATE_1.getBrandId(), rate.getBrandId());
		assertTrue(TestData.PRODUCT_RATE_1.getStartDate().equals(rate.getStartDate()));
		assertTrue(TestData.PRODUCT_RATE_1.getEndDate().equals(rate.getEndDate()));
		assertEquals(TestData.PRODUCT_RATE_1.getPrice(), rate.getPrice());
	}

}
