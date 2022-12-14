package com.inl.rst;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.inl.rst.service.OrderDTO;

public class OrderControllerETagTest {

	public final static String HOST = "http://localhost:8080/orders/1";

	@Test
	public void shouldBe304AsResponseCode() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<OrderDTO> response = restTemplate.getForEntity(HOST, OrderDTO.class); // headForHeaders(HOST+"orders/1");

		response.getHeaders().getETag();
		String etagValue = response.getHeaders().getETag();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("If-None-Match", etagValue);		
		HttpEntity<String> httpEntity = new HttpEntity<>( headers);
	    // When
		ResponseEntity<OrderDTO> secondFindOneResponse=  restTemplate.exchange(HOST, HttpMethod.GET, httpEntity, OrderDTO.class);

	    // Then
	    assertTrue(secondFindOneResponse.getStatusCode()  == HttpStatus.NOT_MODIFIED);
	}

}
