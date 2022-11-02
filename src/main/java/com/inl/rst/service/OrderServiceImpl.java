package com.inl.rst.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inl.rst.service.common.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private final Map<Long, OrderDTO> inMemoryStorage;

	public OrderServiceImpl() {
		inMemoryStorage = new HashMap<>(
				new OrdersRepo().create().stream().collect(Collectors.toMap(OrderDTO::getId, Function.identity())));
	}

	@Override
	public List<OrderDTO> all() {
		return new ArrayList<>(inMemoryStorage.values());
	}
	
	@Override
	public Optional<OrderDTO> find(long id) {
		return Optional.of(inMemoryStorage.get(id));
	}

	@Override
	public OrderDTO add(OrderDTO order) {
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
		public List<OrderDTO> create() {
			return List.of(new OrderDTO(1, "workday", "workday routine", 1), new OrderDTO(2, "weekend", "weekend routine", 1));
		}

	}
}
