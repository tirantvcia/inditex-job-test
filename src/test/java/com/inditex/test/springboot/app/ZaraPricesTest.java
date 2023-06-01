package com.inditex.test.springboot.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ZaraPricesTest {

	@Test
	@DisplayName("No rate selected when entry is null")
	void emptyProductRateForNullSelection() {
		RateSelection entry = null;
		ProductRate rate = ZaraProductRate.getProductRate(entry);
		assertNull(rate);
	}

}
