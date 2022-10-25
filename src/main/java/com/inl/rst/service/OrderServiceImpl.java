package com.inl.rst.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inl.rst.model.Order;

@Service
public class OrderServiceImpl implements OrderService {

	private final Map<Long, Order> inMemoryStorage;

	public OrderServiceImpl() {
		inMemoryStorage = new HashMap<>(
				new OrdersRepo().create().stream().collect(Collectors.toMap(Order::getId, Function.identity())));
	}

	@Override
	public List<Order> all() {
		return new ArrayList<>(inMemoryStorage.values());
	}
	
	@Override
	public Optional<Order> find(long id) {
		return Optional.of(inMemoryStorage.get(id));
	}

	@Override
	public Order add(Order order) {
		inMemoryStorage.put(order.getId(), order);
		return order;
	}

	@Override
	public boolean delete(long id) {
		if (!inMemoryStorage.containsKey(id)) {
			return false;
		}

		inMemoryStorage.remove(id);

		return true;
	}

	private static class OrdersRepo {
		public List<Order> create() {
			return List.of(new Order(1, "workday", "workday routine", 1), new Order(2, "weekend", "weekend routine", 1));
		}

	}
}
