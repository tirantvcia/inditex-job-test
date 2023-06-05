package com.inditex.test.springboot.app.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.inditex.test.springboot.app.TestData;
import com.inditex.test.springboot.app.data.ProductRate;
import com.inditex.test.springboot.app.data.RateSelection;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PriceListControllerRestTemplateTest {
	private static final String GET_MOST_PRIORITY_PRICE_BY_SELECTION = "/test/prices/getMostPriorityPriceBySelection";

	@Autowired
	private TestRestTemplate client;
	@LocalServerPort
	private String puerto;


	@Test
	@DisplayName("petición a las 10:00 del día 14 del producto 35455 para la brand 1")
	void getMostPriorityPriceBySelection() throws Exception {

		String date = "14-06-2020";
		String time = "10:00";
		Long productId = 35455L;
		Long brandId = 1L;
		ResponseEntity<ProductRate> response = invokeUriExchange(date, time, productId, brandId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		ProductRate productRate = response.getBody();
		assertNotNull(productRate);
		assertEquals(TestData.PRODUCT_RATE_1.getPrice(), productRate.getPrice());
		assertEquals(TestData.PRODUCT_RATE_1.getPriceList(), productRate.getPriceList());
		assertEquals(TestData.PRODUCT_RATE_1.getProductId(), productRate.getProductId());
	}

	@Test
	@DisplayName("petición a las 16:00 del día 14 del producto 35455 para la brand 1")
	void checkProductRatesForDate14at16Oclock() {

		String date = "14-06-2020";
		String time = "16:00";
		Long productId = 35455L;
		Long brandId = 1L;
		ResponseEntity<ProductRate> response = invokeUriExchange(date, time, productId, brandId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		ProductRate productRate = response.getBody();
		assertNotNull(productRate);
		assertEquals(TestData.PRODUCT_RATE_2.getPrice(), productRate.getPrice());
		assertEquals(TestData.PRODUCT_RATE_2.getPriceList(), productRate.getPriceList());
		assertEquals(TestData.PRODUCT_RATE_2.getProductId(), productRate.getProductId());
	}
	
	@Test
	@DisplayName("petición a las 21:00 del día 14 del producto 35455 para la brand 1")
	void checkProductRatesForDate14at21Oclock() {

		String date = "14-06-2020";
		String time = "21:00";
		Long productId = 35455L;
		Long brandId = 1L;
		ResponseEntity<ProductRate> response =  invokeUriExchange(date, time, productId, brandId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		ProductRate productRate = response.getBody();
		assertNotNull(productRate);
		assertEquals(TestData.PRODUCT_RATE_1.getPrice(), productRate.getPrice());
		assertEquals(TestData.PRODUCT_RATE_1.getPriceList(), productRate.getPriceList());
		assertEquals(TestData.PRODUCT_RATE_1.getProductId(), productRate.getProductId());
	}

	@Test
	@DisplayName("petición a las 10:00 del día 15 del producto 35455 para la brand 1")
	void checkProductRatesForDate15at10Oclock() {
		String date = "15-06-2020";
		String time = "10:00";
		Long productId = 35455L;
		Long brandId = 1L;
		ResponseEntity<ProductRate> response =  invokeUriExchange(date, time, productId, brandId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		ProductRate productRate = response.getBody();
		assertNotNull(productRate);
		assertEquals(TestData.PRODUCT_RATE_3.getPrice(), productRate.getPrice());
		assertEquals(TestData.PRODUCT_RATE_3.getPriceList(), productRate.getPriceList());
		assertEquals(TestData.PRODUCT_RATE_3.getProductId(), productRate.getProductId());		
	}
	private ResponseEntity<ProductRate> invokeUriExchange(String date, String time, Long productId, Long brandId) {
		String url = getUri(GET_MOST_PRIORITY_PRICE_BY_SELECTION);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("date", date)
				.queryParam("time", time).queryParam("productId", productId).queryParam("brandId", brandId);
		HttpEntity requestEntity = getHeaders();
		return client.exchange(builder.build().encode().toUri(), HttpMethod.GET,
				requestEntity, ProductRate.class);
	}
	
	private String getUri(String uri) {
		return "http://localhost:" + puerto + uri;
	}


	private HttpEntity getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");
		HttpEntity requestEntity = new HttpEntity<>(headers);
		return requestEntity;
	}

}
