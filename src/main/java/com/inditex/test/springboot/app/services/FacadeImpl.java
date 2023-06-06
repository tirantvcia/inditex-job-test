package com.inditex.test.springboot.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inditex.test.springboot.app.dto.request.RateSelectionRequest;
import com.inditex.test.springboot.app.dto.response.ProductRateResponse;

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
	public ProductRateResponse findMostPriorityPriceBySelection(String date, String time, Long productId, Long brandId) {

		RateSelectionRequest entry = RateSelectionRequest.create(date, time, productId, brandId);
		return service.findMostPriorityPriceBySelection(entry);
	}


}
