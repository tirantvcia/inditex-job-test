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
import com.inditex.test.springboot.app.dto.request.RateSelectionRequest;
import com.inditex.test.springboot.app.models.Price;

@DataJpaTest
public class SpringBootJpaTest {
	private static final long BRAND_ID_1 = 1L;
	private static final long PRODUCT_ID_35455 = 35455L;
	
	@Autowired
	PricesRepository pricesRepository;

	@Test
	@DisplayName("Return empty rate when priceList is not selected")
	void emptyProductRateForNoPriceSelection() {

		String date = "14-03-2022";
		String time = "10:00";
		RateSelectionRequest entry = RateSelectionRequest.create(date, time, PRODUCT_ID_35455, BRAND_ID_1);
		List<Price> findFirstPriceRateBySelectionEntry = pricesRepository
				.findPricesBySelectionOrderedByPrioriry(entry.getDate(), entry.getProduct(), entry.getBrand());

		Price price = findFirstPriceRateBySelectionEntry.stream().findFirst().orElse(new Price());

		assertNotNull(price);

		assertNull(price.getProductId());
		assertNull(price.getId());
		assertNull(price.getBrand());
		assertNull(price.getStartDate());
		assertNull(price.getEndDate());
		assertNull(price.getPrice());

	}

	@Test
	@DisplayName("petición a las 10:00 del día 14 del producto 35455   para la brand 1")
	void checkProductRatesForDate14at10Oclock() {

		String date = "14-06-2020";
		String time = "10:00";
		RateSelectionRequest entry = RateSelectionRequest.create(date, time, PRODUCT_ID_35455, BRAND_ID_1);

		List<Price> findFirstPriceRateBySelectionEntry = pricesRepository
				.findPricesBySelectionOrderedByPrioriry(entry.getDate(), entry.getProduct(), entry.getBrand());

		assertTrue(findFirstPriceRateBySelectionEntry.size() > 0);
		Price price = findFirstPriceRateBySelectionEntry.stream().findFirst().orElse(new Price());
		assertEquals(TestData.PRICE_LIST_1.getProductId(), price.getProductId());
		assertEquals(TestData.PRICE_LIST_1.getId(), price.getId());
		assertEquals(TestData.PRICE_LIST_1.getBrand().getId(), price.getBrand().getId());
		assertTrue(TestData.PRICE_LIST_1.getStartDate().equals(price.getStartDate()));
		assertTrue(TestData.PRICE_LIST_1.getEndDate().equals(price.getEndDate()));
		assertEquals(TestData.PRICE_LIST_1.getPrice(), price.getPrice());
	}

	@Test
	@DisplayName("petición a las 16:00 del día 14 del producto 35455 para la brand 1")
	void checkProductRatesForDate14at16Oclock() {

		String date = "14-06-2020";
		String time = "16:00";
		RateSelectionRequest entry = RateSelectionRequest.create(date, time, PRODUCT_ID_35455, BRAND_ID_1);

		List<Price> findFirstPriceRateBySelectionEntry = pricesRepository
				.findPricesBySelectionOrderedByPrioriry(entry.getDate(), entry.getProduct(), entry.getBrand());
		

		assertTrue(findFirstPriceRateBySelectionEntry.size() > 0);
		Price price = findFirstPriceRateBySelectionEntry.stream().findFirst().orElse(new Price());
		assertEquals(TestData.PRICE_LIST_2.getProductId(), price.getProductId());
		assertEquals(TestData.PRICE_LIST_2.getId(), price.getId());
		assertEquals(TestData.PRICE_LIST_2.getBrand().getId(), price.getBrand().getId());
		assertTrue(TestData.PRICE_LIST_2.getStartDate().equals(price.getStartDate()));
		assertTrue(TestData.PRICE_LIST_2.getEndDate().equals(price.getEndDate()));
		assertEquals(TestData.PRICE_LIST_2.getPrice(), price.getPrice());
	}

	@Test
	@DisplayName("petición a las 21:00 del día 14 del producto 35455 para la brand 1")
	void checkProductRatesForDate14at21Oclock() {

		String date = "14-06-2020";
		String time = "21:00";
		RateSelectionRequest entry = RateSelectionRequest.create(date, time, PRODUCT_ID_35455, BRAND_ID_1);

		List<Price> findFirstPriceRateBySelectionEntry = pricesRepository
				.findPricesBySelectionOrderedByPrioriry(entry.getDate(), entry.getProduct(), entry.getBrand());
		assertTrue(findFirstPriceRateBySelectionEntry.size() > 0);
		Price price = findFirstPriceRateBySelectionEntry.stream().findFirst().orElse(new Price());
		assertEquals(TestData.PRICE_LIST_1.getProductId(), price.getProductId());
		assertEquals(TestData.PRICE_LIST_1.getId(), price.getId());
		assertEquals(TestData.PRICE_LIST_1.getBrand().getId(), price.getBrand().getId());
		assertTrue(TestData.PRICE_LIST_1.getStartDate().equals(price.getStartDate()));
		assertTrue(TestData.PRICE_LIST_1.getEndDate().equals(price.getEndDate()));
		assertEquals(TestData.PRICE_LIST_1.getPrice(), price.getPrice());
	}

	@Test
	@DisplayName("petición a las 10:00 del día 15 del producto 35455 para la brand 1")
	void checkProductRatesForDate15at10Oclock() {

		String date = "15-06-2020";
		String time = "10:00";
		RateSelectionRequest entry = RateSelectionRequest.create(date, time, PRODUCT_ID_35455, BRAND_ID_1);

		List<Price> findFirstPriceRateBySelectionEntry = pricesRepository
				.findPricesBySelectionOrderedByPrioriry(entry.getDate(), entry.getProduct(), entry.getBrand());
		assertTrue(findFirstPriceRateBySelectionEntry.size() > 0);
		Price price = findFirstPriceRateBySelectionEntry.stream().findFirst().orElse(new Price());
		assertEquals(TestData.PRICE_LIST_3.getProductId(), price.getProductId());
		assertEquals(TestData.PRICE_LIST_3.getId(), price.getId());
		assertEquals(TestData.PRICE_LIST_3.getBrand().getId(), price.getBrand().getId());
		assertTrue(TestData.PRICE_LIST_3.getStartDate().equals(price.getStartDate()));
		assertTrue(TestData.PRICE_LIST_3.getEndDate().equals(price.getEndDate()));
		assertEquals(TestData.PRICE_LIST_3.getPrice(), price.getPrice());
	}

	@Test
	@DisplayName("petición a las 21:00 del día 16 del producto 35455 para la brand 1")
	void checkProductRatesForDate16at21Oclock() {

		String date = "16-06-2020";
		String time = "21:00";
		RateSelectionRequest entry = RateSelectionRequest.create(date, time, PRODUCT_ID_35455, BRAND_ID_1);

		List<Price> findFirstPriceRateBySelectionEntry = pricesRepository
				.findPricesBySelectionOrderedByPrioriry(entry.getDate(), entry.getProduct(), entry.getBrand());
		assertTrue(findFirstPriceRateBySelectionEntry.size() > 0);
		Price price = findFirstPriceRateBySelectionEntry.stream().findFirst().orElse(new Price());
		assertEquals(TestData.PRICE_LIST_4.getProductId(), price.getProductId());
		assertEquals(TestData.PRICE_LIST_4.getId(), price.getId());
		assertEquals(TestData.PRICE_LIST_4.getBrand().getId(), price.getBrand().getId());
		assertTrue(TestData.PRICE_LIST_4.getStartDate().equals(price.getStartDate()));
		assertTrue(TestData.PRICE_LIST_4.getEndDate().equals(price.getEndDate()));
		assertEquals(TestData.PRICE_LIST_4.getPrice(), price.getPrice());
	}

}
