package com.inditex.test.springboot.app.services;

import java.util.Date;

import com.inditex.test.springboot.app.dto.response.ProductRateResponse;

public interface ZaraProductRateService {

	ProductRateResponse findMostPriorityPriceBySelection(Date localDateTimeCriteria, Long productId, Long brandId);

}