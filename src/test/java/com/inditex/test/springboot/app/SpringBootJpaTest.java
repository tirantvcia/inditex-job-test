package com.inditex.test.springboot.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.inditex.test.springboot.app.data.RateSelection;
import com.inditex.test.springboot.app.models.Price;
import com.inditex.test.springboot.app.repositories.PricesRepository;

@DataJpaTest
public class SpringBootJpaTest {
	@Autowired
	PricesRepository pricesRepository;
	
	
	@Test
	@DisplayName("Return empty rate when priceList is not selected")
	void emptyProductRateForNoPriceSelection() {
		
		
		RateSelection entry = new RateSelection(LocalDate.of(2022, 3, 14),
				LocalTime.of(10,00), 34455L, 1L);
		
		Optional<Price> findFirstPriceRateBySelectionEntry = pricesRepository.findFirstPriceRateBySelectionEntry(entry.getDate(), entry.getProduct(), entry.getBrand());
		
		Price rate = findFirstPriceRateBySelectionEntry.orElse(new Price());
		
		assertNotNull(rate);
		
		assertNull(rate.getProductId());
		assertNull(rate.getId());
		assertNull(rate.getBrand());
		assertNull(rate.getStartDate());
		assertNull(rate.getEndDate());
		assertNull(rate.getPrice());

	}
	
	@Test
	@DisplayName("petición a las 10:00 del día 14 del producto 35455   para la brand 1")
	void checkProductRatesForDate14at10Oclock() {
		
		
		RateSelection entry = new RateSelection(LocalDate.of(2020, 6, 14),
					LocalTime.of(10,00), 35455L, 1L);
		
		Optional<Price> findFirstPriceRateBySelectionEntry = pricesRepository.findFirstPriceRateBySelectionEntry(entry.getDate(), entry.getProduct(), entry.getBrand());
		
		Price rate = findFirstPriceRateBySelectionEntry.orElseThrow();
		
		assertEquals(35455L, rate.getProductId());
		assertEquals(1, rate.getId());
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
