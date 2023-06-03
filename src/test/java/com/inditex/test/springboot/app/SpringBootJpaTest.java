package com.inditex.test.springboot.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.inditex.test.springboot.app.data.ProductRate;
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
		
		List<Price> findFirstPriceRateBySelectionEntry = 
				pricesRepository.findPriceRatesBySelectionEntry(entry.getDate(), entry.getProduct(), entry.getBrand());
		
		Price price =  findFirstPriceRateBySelectionEntry.stream().findFirst().orElse(new Price());	
		
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
		
		
		RateSelection entry = new RateSelection(LocalDate.of(2020, 6, 14),
					LocalTime.of(10,00), 35455L, 1L);
		
		List<Price> findFirstPriceRateBySelectionEntry = 
				pricesRepository.findPriceRatesBySelectionEntry(entry.getDate(), entry.getProduct(), entry.getBrand());
		
		Price price =  findFirstPriceRateBySelectionEntry.stream().findFirst().orElse(new Price());	
		
		assertEquals(35455L, price.getProductId());
		assertEquals(1, price.getId());
		assertEquals(1, price.getBrand().getId());
		assertEquals(convertToDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0)), price.getStartDate());
		assertEquals(convertToDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59)), price.getEndDate());
		assertEquals(35.50, price.getPrice());
	}

	@Test
	@DisplayName("petición a las 16:00 del día 14 del producto 35455 para la brand 1")
	void checkProductRatesForDate14at16Oclock() {
		
		
		RateSelection entry = new RateSelection(LocalDate.of(2020, 6, 14),
					LocalTime.of(16,00), 35455L, 1L);
		
		List<Price> findFirstPriceRateBySelectionEntry = 
				pricesRepository.findPriceRatesBySelectionEntry(entry.getDate(), entry.getProduct(), entry.getBrand());
		
		Price price =  findFirstPriceRateBySelectionEntry.stream().findFirst().orElse(new Price());	
		
		assertEquals(35455L, price.getProductId());
		assertEquals(2, price.getId());
		assertEquals(1, price.getBrand().getId());
		assertEquals(25.45, price.getPrice());
	}	
	
	private Date convertToDate(LocalDateTime dateToConvert) {
	    return java.util.Date
	      .from(dateToConvert.atZone(ZoneId.systemDefault())
	      .toInstant());
	}

}
