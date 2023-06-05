package com.inditex.test.springboot.app.services;

import com.inditex.test.springboot.app.data.ProductRate;

public interface Facade {

	ProductRate findMostPriorityPriceBySelection(String date, String time, Long productId, Long brandId);

}