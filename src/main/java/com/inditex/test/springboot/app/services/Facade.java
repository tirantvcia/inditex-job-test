package com.inditex.test.springboot.app.services;

import com.inditex.test.springboot.app.dto.response.ProductRateResponse;

public interface Facade {

	ProductRateResponse findMostPriorityPriceBySelection(String date, String time, Long productId, Long brandId);

}