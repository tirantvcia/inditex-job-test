package com.inditex.test.springboot.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.inditex.test.springboot.app.TestData;
import com.inditex.test.springboot.app.data.ProductRate;
import com.inditex.test.springboot.app.data.RateSelection;

@SpringBootTest
class FacadeTest {

	@MockBean
	ZaraProductRateService service;

	@Autowired
	Facade facade;

	@Test
	@DisplayName("petición a las 16:00 del día 14 del producto 35455 para la brand 1")
	void checkProductRatesForDate14at16Oclock() {

		String date = "14, 06, 2020";
		String time = "16:00";
		Long productId = 35455L;
		Long brandId = 1L;
		
		RateSelection entry = RateSelection.create(date, time, productId, brandId);
		
		when(service.findMostPriorityPriceBySelection(entry)).thenReturn(TestData.PRODUCT_RATE_1);
			

		ProductRate productRate = facade.findMostPriorityPriceBySelection(date, time, productId, brandId);
		assertNotNull(productRate);
		

		assertEquals(TestData.PRODUCT_RATE_1.getPrice(), productRate.getPrice());
		assertEquals(TestData.PRODUCT_RATE_1.getPriceList(), productRate.getPriceList());
		assertEquals(TestData.PRODUCT_RATE_1.getProductId(), productRate.getProductId());
	}


}
