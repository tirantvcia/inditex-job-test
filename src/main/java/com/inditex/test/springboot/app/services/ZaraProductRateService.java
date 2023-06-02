package com.inditex.test.springboot.app.services;

import com.inditex.test.springboot.app.data.ProductRate;
import com.inditex.test.springboot.app.data.RateSelection;

public interface ZaraProductRateService {

	ProductRate getProductRate(RateSelection entry);

}