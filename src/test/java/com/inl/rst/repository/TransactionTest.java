package com.inl.rst.repository;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.inl.rst.domain.Order;

@DataJpaTest
public class TransactionTest {

	@Autowired
	private OrderRepository orderRepository;

	@Test
	public void shouldViolateMandatoryConstraintThenExceptionAndRollback() {
		Exception thrown = assertThrows(Exception.class, () -> {
			orderRepository.saveAndFlush(new Order(null, null, null));
		});

		System.err.println("Exception: " + thrown.getMessage());
	}

}
