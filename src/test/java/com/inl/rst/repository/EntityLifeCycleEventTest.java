package com.inl.rst.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.inl.rst.domain.Order;

@DataJpaTest
public class EntityLifeCycleEventTest {
	
	@Autowired
	private EntityManager entityManager;
	
	@Test
	public void shouldGenerateEvents() {
		Order order = new Order(null, "name", "description");
		entityManager.persist(order);		
		assertTrue(entityManager.contains(order));
		
		Order newOrder = entityManager.getReference(Order.class, order.getId());	
		entityManager.remove(newOrder);	
		assertFalse(entityManager.contains(newOrder));
	}

}
