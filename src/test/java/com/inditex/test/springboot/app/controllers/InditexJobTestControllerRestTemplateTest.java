package com.inditex.test.springboot.app.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ConstraintViolationException;

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
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import com.inditex.test.springboot.app.TestData;
import com.inditex.test.springboot.app.dto.response.ProductRateResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class InditexJobTestControllerRestTemplateTest {
	private static final String GET_MOST_PRIORITY_PRICE_BY_SELECTION = "/test/prices/getMostPriorityPriceBySelection";

	@Autowired
	private TestRestTemplate client;
	@LocalServerPort
	private String puerto;

	
	
	@Test
	@DisplayName("petición error a las 100:00")
	void getMostPriorityPriceBySelectionBadRequest() throws Exception {

		String date = "14-06-2020";
		String time = "100:00";
		Long productId = 35455L;
		Long brandId = 1L;
		assertThrows(RestClientException.class, () -> {
			ResponseEntity<?> invokeUriExchange = invokeUriExchange(date, time, productId, brandId);
			assertEquals(invokeUriExchange.getStatusCode(), HttpStatus.BAD_REQUEST);
			
		});
		


	}
	

	
	@Test
	@DisplayName("Return empty rate when priceList is not selected")
	void emptyProductRateForNoPriceSelection() {

		String date = "14-06-2022";
		String time = "10:00";
		Long productId = 35455L;
		Long brandId = 1L;
		ResponseEntity<ProductRateResponse> response = invokeUriExchange(date, time, productId, brandId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		ProductRateResponse actual = response.getBody();
		assertNotNull(actual);
		assertEquals(null, actual.getProductId());
		assertEquals(null, actual.getPriceList());
		assertEquals(null, actual.getBrandId());
		assertEquals(null, actual.getPrice());
	}
	
	@Test
	@DisplayName("petición 1 a las 10:00 del día 14 del producto 35455 para la brand 1")
	void getMostPriorityPriceBySelection() throws Exception {

		String date = "14-06-2020";
		String time = "10:00";
		Long productId = 35455L;
		Long brandId = 1L;
		ResponseEntity<ProductRateResponse> response = invokeUriExchange(date, time, productId, brandId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		ProductRateResponse actual = response.getBody();
		assertNotNull(actual);
		assertions(TestData.PRODUCT_RATE_1, actual);
	}

	@Test
	@DisplayName("petición 2 a las 16:00 del día 14 del producto 35455 para la brand 1")
	void checkProductRatesForDate14at16Oclock() {

		String date = "14-06-2020";
		String time = "16:00";
		Long productId = 35455L;
		Long brandId = 1L;
		ResponseEntity<ProductRateResponse> response = invokeUriExchange(date, time, productId, brandId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		ProductRateResponse actual = response.getBody();
		assertNotNull(actual);
		assertions(TestData.PRODUCT_RATE_2, actual);
	}
	
	@Test
	@DisplayName("petición 3 a las 21:00 del día 14 del producto 35455 para la brand 1")
	void checkProductRatesForDate14at21Oclock() {

		String date = "14-06-2020";
		String time = "21:00";
		Long productId = 35455L;
		Long brandId = 1L;
		ResponseEntity<ProductRateResponse> response =  invokeUriExchange(date, time, productId, brandId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		ProductRateResponse actual = response.getBody();
		assertNotNull(actual);
		assertions(TestData.PRODUCT_RATE_1, actual);
	}

	@Test
	@DisplayName("petición 4 a las 10:00 del día 15 del producto 35455 para la brand 1")
	void checkProductRatesForDate15at10Oclock() {
		String date = "15-06-2020";
		String time = "10:00";
		Long productId = 35455L;
		Long brandId = 1L;
		ResponseEntity<ProductRateResponse> response =  invokeUriExchange(date, time, productId, brandId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		ProductRateResponse actual = response.getBody();
		assertNotNull(actual);
		assertions(TestData.PRODUCT_RATE_3, actual);
	}
	
	@Test
	@DisplayName("petición 5 a las 21:00 del día 16 del producto 35455 para la brand 1")
	void checkProductRatesForDate16at21Oclock() {
		String date = "16-06-2020";
		String time = "21:00";
		Long productId = 35455L;
		Long brandId = 1L;
		ResponseEntity<ProductRateResponse> response =  invokeUriExchange(date, time, productId, brandId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		ProductRateResponse productRate = response.getBody();
		ProductRateResponse actual = response.getBody();
		assertNotNull(actual);
		assertions(TestData.PRODUCT_RATE_4, actual);

	}	
	
	private ResponseEntity<ProductRateResponse> invokeUriExchange(String date, String time, Long productId, Long brandId) {
		String url = getUri(GET_MOST_PRIORITY_PRICE_BY_SELECTION);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("date", date)
				.queryParam("time", time).queryParam("productId", productId).queryParam("brandId", brandId);
		HttpEntity requestEntity = getHeaders();
		return client.exchange(builder.build().encode().toUri(), HttpMethod.GET,
				requestEntity, ProductRateResponse.class);
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
	
	private void assertions(ProductRateResponse expected, ProductRateResponse actual) {
		assertEquals(expected.getProductId(), actual.getProductId());
		assertEquals(expected.getPriceList(), actual.getPriceList());
		assertEquals(expected.getBrandId(), actual.getBrandId());
		assertTrue(expected.getStartDate().equals(actual.getStartDate()));
		assertTrue(expected.getEndDate().equals(actual.getEndDate()));
		assertEquals(expected.getPrice(), actual.getPrice());
	}

}
