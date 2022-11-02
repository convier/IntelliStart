package com.inl.rst.repository;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EntityLifeCycleTest {

	@Autowired
	private EntityManager entityManager;

	@Test
	public void shouldBeRightState() {

	}
}