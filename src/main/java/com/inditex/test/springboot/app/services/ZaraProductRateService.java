package com.inditex.test.springboot.app.services;

import com.inditex.test.springboot.app.dto.request.RateSelectionRequest;
import com.inditex.test.springboot.app.dto.response.ProductRateResponse;

public interface ZaraProductRateService {

	ProductRateResponse findMostPriorityPriceBySelection(RateSelectionRequest entry);

}