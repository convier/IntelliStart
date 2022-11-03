package com.inl.rst.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.HashSet;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.inl.rst.domain.ECommerceItem;
import com.inl.rst.domain.Order;

@DataJpaTest
public class InheritanceStrategyTest {
	
	@Autowired
	private EntityManager entityManager;
	
	@Test
	public void shouldSaveInSingleTable() {
		ECommerceItem eCommerceItem = new ECommerceItem();
		eCommerceItem.setName("testInheritance");
		eCommerceItem.setPlatform("Amazon");
		
		final Order order = new Order(null, "test", "test");
		order.setItems(Collections.singleton(eCommerceItem));
		
		entityManager.persist(order);
		
		Order newOrder = entityManager.find(Order.class, order.getId());
		assertNotNull(newOrder);
	}

}
