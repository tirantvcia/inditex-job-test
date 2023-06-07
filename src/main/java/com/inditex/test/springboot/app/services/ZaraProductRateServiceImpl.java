package com.inditex.test.springboot.app.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.test.springboot.app.dto.response.ProductRateResponse;
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
	public ProductRateResponse findMostPriorityPriceBySelection(Date dateTimeCriteria, Long productId, Long brandId) {
		List<Price> findPriceRateBySelectionEntry = this.pricesRepository
				.findPricesBySelectionOrderedByPrioriry(dateTimeCriteria, productId, brandId);

		return findPriceRateBySelectionEntry.stream().map(ProductRateResponse::create).findFirst().orElse( new ProductRateResponse());
	}


}
