package com.inl.rst.service;

import java.util.List;
import java.util.Optional;

import com.inl.rst.model.Order;

public interface OrderService {

	List<Order> all();

	Order add(Order order);
	
	Optional<Order> find(long id);

	boolean delete(long id);
}
