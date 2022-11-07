package com.inl.rst.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.inl.rst.domain.Order;

@DataJpaTest
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@Test
	public void shouldCreateOrder() {
		Order order = orderRepository.saveAndFlush(new Order(null, "order 1", "test order"));
		assertNotNull(order.getId());
	}
}