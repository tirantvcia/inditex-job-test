package com.inditex.test.springboot.app.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
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
	public ProductRate getProductRate(RateSelection petition) {
		
		if(petition == null) {
			return ProductRate.create(null);
		}
		List<Price> findPriceRateBySelectionEntryPage = 
				this.pricesRepository.findPriceRatesBySelectionEntry(petition.getDate(), petition.getProduct(), petition.getBrand());
		
		return (findPriceRateBySelectionEntryPage != null && !findPriceRateBySelectionEntryPage.isEmpty())?
				findPriceRateBySelectionEntryPage.stream().map(ProductRate::create).findFirst().get():new ProductRate();		
		

	}

}
