package com.inditex.test.springboot.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inditex.test.springboot.app.data.ProductRate;
import com.inditex.test.springboot.app.data.RateSelection;
import com.inditex.test.springboot.app.models.Price;
import com.inditex.test.springboot.app.repositories.PricesRepository;

@Service
public class ZaraProductRate implements ZaraProductRateService {
	
	private PricesRepository pricesRepository;
	

	@Autowired
	public ZaraProductRate(PricesRepository pricesRepository) {
		this.pricesRepository = pricesRepository;
	}



	@Override
	public ProductRate getProductRate(RateSelection entry) {
		
		if(entry == null) {
			return ProductRate.create(null);
		}
		Optional<Price> findFirstPriceRateBySelectionEntry = 
				this.pricesRepository.findFirstPriceRateBySelectionEntry(entry);
		
		return findFirstPriceRateBySelectionEntry.map(ProductRate::create)
				.orElse(ProductRate.create(null));
		

	}

}
