package com.inditex.test.springboot.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

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
				TestData.BRAND_ID_1)).thenReturn(new ArrayList<>());
		
		ProductRateResponse rate = zaraProductRate.findMostPriorityPriceBySelection(TestData.TEST_DATE_CRITERIA_NOT_MATCHED, TestData.PRODUCT_ID_35455,
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
	@DisplayName("petición 1 a las 10:00 del día 14 del producto 35455 para la brand 1")
	void checkProductRatesForDate14at10Oclock() {
		
		when(pricesRepository.findPricesBySelectionOrderedByPrioriry(TestData.TEST_1_DATE_CRITERIA, TestData.PRODUCT_ID_35455,
				TestData.BRAND_ID_1)).thenReturn(TestData.PRICE_RESULT_LIST_FOR_PETITION_1);
		ProductRateResponse actual = zaraProductRate.findMostPriorityPriceBySelection(TestData.TEST_1_DATE_CRITERIA, TestData.PRODUCT_ID_35455,
				TestData.BRAND_ID_1);
		assertions(TestData.PRODUCT_RATE_1, actual);
	}


	
	@Test
	@DisplayName("petición 2 a las 16:00 del día 14 del producto 35455 para la brand 1")
	void checkProductRatesForDate14at16Oclock() {
		
		when(pricesRepository.findPricesBySelectionOrderedByPrioriry(TestData.TEST_2_DATE_CRITERIA, TestData.PRODUCT_ID_35455,
				TestData.BRAND_ID_1)).thenReturn(TestData.PRICE_RESULT_LIST_FOR_PETITION_2);
		
		ProductRateResponse actual = zaraProductRate.findMostPriorityPriceBySelection(TestData.TEST_2_DATE_CRITERIA, TestData.PRODUCT_ID_35455,
				TestData.BRAND_ID_1);
		assertions(TestData.PRODUCT_RATE_2, actual);
	}
	
	private void assertions(ProductRateResponse expected, ProductRateResponse actual) {
		assertEquals(expected.getProductId(), actual.getProductId());
		assertEquals(expected.getPriceList(), actual.getPriceList());
		assertEquals(expected.getBrandId(), actual.getBrandId());
		assertTrue(expected.getStartDate().equals(actual.getStartDate()));
		assertTrue(expected.getEndDate().equals(actual.getEndDate()));
		assertEquals(expected.getPrice(), actual.getPrice());
	}
}
