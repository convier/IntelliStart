package com.inl.rst.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.inl.rst.domain.Order;

@DataJpaTest
public class QueryTest {

	@Autowired
	private EntityManager entityManager;

	@Test
	public void shouldFindById() {
		Order newOrder = new Order(null, "test", "test");
		entityManager.persist(newOrder);

		TypedQuery<Order> query = entityManager.createNamedQuery("Order.findById", Order.class);
		query.setParameter("orderId", newOrder.getId());

		Order order = query.getSingleResult();
		assertNotNull(order);
	}

	@Test
	public void shouldFindViaCriteriaAPI() {
		Order newOrder = new Order(null, "test", "test");
		entityManager.persist(newOrder);

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order> query = cb.createQuery(Order.class);
		Root<Order> root = query.from(Order.class);
		query = query.select(root).where(cb.equal(root.get("id"), newOrder.getId()));

		Order order = entityManager.createQuery(query).getSingleResult();
		assertNotNull(order);

	}

}
