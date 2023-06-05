package com.inditex.test.springboot.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inditex.test.springboot.app.data.ProductRate;
import com.inditex.test.springboot.app.data.RateSelection;

@Component
public class FacadeImpl implements Facade {

	private ZaraProductRateService service;

	public FacadeImpl() {
	}

	@Autowired
	public FacadeImpl(ZaraProductRateService service) {
		this.service = service;
	}

	@Override
	public ProductRate findMostPriorityPriceBySelection(String date, String time, Long productId, Long brandId) {

		RateSelection entry = RateSelection.create(date, time, productId, brandId);
		 ProductRate findMostPriorityPriceBySelection = service.findMostPriorityPriceBySelection(entry);
		 return findMostPriorityPriceBySelection;


	}


}
