package com.inl.rst;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import com.inl.rst.model.Order;

public class OrderControllerRestTemplateTest {

	public final static String HOST = "http://localhost:8080/";

	@Test
	public void shouldCreateNewOrder() {
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<Order> request = new HttpEntity<>(new Order(3, "test", "fromTest", 1));

		Order order = restTemplate.postForObject(HOST + "orders", request, Order.class);
		assertNotNull(order);
	}
	
	@Test
	public void shouldBeResponseResourseNotExist() {
		RestTemplate restTemplate = new RestTemplate();
		String entityUrl = HOST + "v1/orders/10";//?version=1";
		restTemplate.delete(entityUrl);		
	}
}