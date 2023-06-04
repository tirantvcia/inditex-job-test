package com.inditex.test.springboot.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.test.springboot.app.data.ProductRate;
import com.inditex.test.springboot.app.data.RateSelection;
import com.inditex.test.springboot.app.models.Price;
import com.inditex.test.springboot.app.repositories.PricesRepository;

@Service
public class ZaraProductRateServiceImpl implements ZaraProductRateService {

	private PricesRepository pricesRepository;

	public ZaraProductRateServiceImpl() {
	}

	@Autowired
	public ZaraProductRateServiceImpl(PricesRepository pricesRepository) {
		this.pricesRepository = pricesRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public ProductRate findMostPriorityPriceBySelection(RateSelection petition) {

		if (petition == null) {
			return ProductRate.create(null);
		}
		List<Price> findPriceRateBySelectionEntry = this.pricesRepository
				.findPricesBySelectionOrderedByPrioriry(petition.getDate(), petition.getProduct(), petition.getBrand());

		return (findPriceRateBySelectionEntry != null && !findPriceRateBySelectionEntry.isEmpty())
				? findPriceRateBySelectionEntry.stream().map(ProductRate::create).findFirst().get()
				: new ProductRate();

	}

}
