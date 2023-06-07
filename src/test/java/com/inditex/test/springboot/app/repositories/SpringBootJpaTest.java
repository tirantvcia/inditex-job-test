package com.inditex.test.springboot.app.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.inditex.test.springboot.app.TestData;
import com.inditex.test.springboot.app.models.Price;

@DataJpaTest
public class SpringBootJpaTest {
	
	@Autowired
	PricesRepository pricesRepository;

	@Test
	@DisplayName("Return empty rate when priceList is not selected")
	void emptyProductRateForNoPriceSelection() {

		List<Price> findFirstPriceRateBySelectionEntry = pricesRepository
				.findPricesBySelectionOrderedByPrioriry(TestData.TEST_DATE_CRITERIA_NOT_MATCHED, TestData.PRODUCT_ID_35455,
						TestData.BRAND_ID_1);
		assertTrue(findFirstPriceRateBySelectionEntry.isEmpty());
	}

	@Test
	@DisplayName("petición 1 a las 10:00 del día 14 del producto 35455   para la brand 1")
	void checkProductRatesForDate14at10Oclock() {

		List<Price> findFirstPriceRateBySelectionEntry = pricesRepository
				.findPricesBySelectionOrderedByPrioriry(TestData.TEST_1_DATE_CRITERIA, TestData.PRODUCT_ID_35455,
						TestData.BRAND_ID_1);
		assertTrue(findFirstPriceRateBySelectionEntry.size() == 1);
		Price actual = findFirstPriceRateBySelectionEntry.get(0);
		priceAssertValidation(TestData.PRICE_LIST_1, actual);
	}



	@Test
	@DisplayName("petición 2 a las 16:00 del día 14 del producto 35455 para la brand 1")
	void checkProductRatesForDate14at16Oclock() {

		List<Price> findFirstPriceRateBySelectionEntry = pricesRepository
				.findPricesBySelectionOrderedByPrioriry(TestData.TEST_2_DATE_CRITERIA, TestData.PRODUCT_ID_35455,
						TestData.BRAND_ID_1);
		

		assertTrue(findFirstPriceRateBySelectionEntry.size() == 2);
		
		Price actual = findFirstPriceRateBySelectionEntry.get(0);
		priceAssertValidation(TestData.PRICE_LIST_2, actual);
		
		actual = findFirstPriceRateBySelectionEntry.get(1);
		priceAssertValidation(TestData.PRICE_LIST_1, actual);

	}

	@Test
	@DisplayName("petición 3 a las 21:00 del día 14 del producto 35455 para la brand 1")
	void checkProductRatesForDate14at21Oclock() {


		List<Price> findFirstPriceRateBySelectionEntry = pricesRepository
				.findPricesBySelectionOrderedByPrioriry(TestData.TEST_3_DATE_CRITERIA, TestData.PRODUCT_ID_35455,
						TestData.BRAND_ID_1);
		
		assertTrue(findFirstPriceRateBySelectionEntry.size() == 1);

		Price actual = findFirstPriceRateBySelectionEntry.get(0);
		priceAssertValidation(TestData.PRICE_LIST_1, actual);
	}

	@Test
	@DisplayName("petición 4 a las 10:00 del día 15 del producto 35455 para la brand 1")
	void checkProductRatesForDate15at10Oclock() {

		List<Price> findFirstPriceRateBySelectionEntry = pricesRepository
				.findPricesBySelectionOrderedByPrioriry(TestData.TEST_4_DATE_CRITERIA, TestData.PRODUCT_ID_35455,
						TestData.BRAND_ID_1);
		
		assertTrue(findFirstPriceRateBySelectionEntry.size() == 2);
		
		Price actual = findFirstPriceRateBySelectionEntry.get(0);
		priceAssertValidation(TestData.PRICE_LIST_3, actual);
		actual = findFirstPriceRateBySelectionEntry.get(1);
		priceAssertValidation(TestData.PRICE_LIST_1, actual);
		
	}

	@Test
	@DisplayName("petición 5 a las 21:00 del día 16 del producto 35455 para la brand 1")
	void checkProductRatesForDate16at21Oclock() {


		List<Price> findFirstPriceRateBySelectionEntry = pricesRepository
				.findPricesBySelectionOrderedByPrioriry(TestData.TEST_5_DATE_CRITERIA, TestData.PRODUCT_ID_35455,
						TestData.BRAND_ID_1);
		
		assertTrue(findFirstPriceRateBySelectionEntry.size() == 2);
		Price actual = findFirstPriceRateBySelectionEntry.get(0);
		priceAssertValidation(TestData.PRICE_LIST_4, actual);
		actual = findFirstPriceRateBySelectionEntry.get(1);
		priceAssertValidation(TestData.PRICE_LIST_1, actual);
	}
	
	private void priceAssertValidation(Price expected, Price actual) {
		assertEquals(expected.getProductId(), actual.getProductId());
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getBrand().getId(), actual.getBrand().getId());
		assertTrue(expected.getStartDate().equals(actual.getStartDate()));
		assertTrue(expected.getEndDate().equals(actual.getEndDate()));
		assertEquals(expected.getPrice(), actual.getPrice());
	}
}
