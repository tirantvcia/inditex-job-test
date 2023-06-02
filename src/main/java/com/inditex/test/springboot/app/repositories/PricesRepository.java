package com.inditex.test.springboot.app.repositories;

import java.util.Optional;

import com.inditex.test.springboot.app.data.RateSelection;
import com.inditex.test.springboot.app.models.Price;

public interface PricesRepository {
	
	public Optional<Price> findFirstPriceRateBySelectionEntry(RateSelection entry);
	
//	private LocalDate date;
//	private LocalTime time;
//	private int product;
//	private int brand;

}
