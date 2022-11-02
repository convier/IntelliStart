package com.inl.rst.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.inl.rst.domain.Order;

@DataJpaTest
public class EntityLifeCycleTest {

	@Autowired
	private EntityManager entityManager;
	
	@AfterEach
	public void clearAllManagedEntities() {
		entityManager.clear();
	}

	@Test
	public void shouldBeTransient() {
		Order order = new Order(null, "name", "description");
		assertFalse(entityManager.contains(order));
	}	

	@Test
	public void shouldBeManaged() {
		Order order = new Order(null, "name", "description");
		entityManager.persist(order);		
		assertTrue(entityManager.contains(order));
	}
	
	@Test
	public void shouldBeDetachedThenManagedAgain() {
		Order order = new Order(null, "name", "description");
		entityManager.persist(order);		
		assertTrue(entityManager.contains(order));
		
		Order newOrder = entityManager.getReference(Order.class, order.getId());		
		
		entityManager.detach(newOrder);	
		assertFalse(entityManager.contains(newOrder));
		
		Order mergedOrder = entityManager.merge(newOrder);		
		assertTrue(entityManager.contains(mergedOrder));
		
		entityManager.detach(newOrder);	
	}
	
	@Test
	public void shouldBeRemoved() {		
		Order order = new Order(null, "name", "description");
		entityManager.persist(order);		
		assertTrue(entityManager.contains(order));
		
		Order newOrder = entityManager.getReference(Order.class, order.getId());	
		entityManager.remove(newOrder);	
		assertFalse(entityManager.contains(newOrder));
	}
}