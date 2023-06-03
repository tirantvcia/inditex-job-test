package com.inditex.test.springboot.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;
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
	

	

}
