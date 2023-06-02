package com.inditex.test.springboot.app.repositories;

import java.util.List;

import com.inditex.test.springboot.app.models.Price;

public interface PricesRepository {
	List<Price> findAll();
	Price findByProductId(Long productId);
}
