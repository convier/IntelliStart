package com.inl.rst.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inl.rst.domain.Order;
import com.inl.rst.repository.OrderRepository;
import com.inl.rst.service.common.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<OrderDTO> all() {
		List<Order> orders = orderRepository.findAll();
			List<OrderDTO> dtos = orders
				  .stream()
				  .map(order -> modelMapper.map(order, OrderDTO.class))
				  .collect(Collectors.toList());
		return  dtos;
	}

	@Override
	public Optional<OrderDTO> find(long id) {
		Optional<Order> order = orderRepository.findById(id);
		return order.map(v -> Optional.of(modelMapper.map(order, OrderDTO.class))).orElseGet(() -> Optional.empty());
	}

	@Override
	public OrderDTO add(OrderDTO orderDto) {
		Order order = orderRepository.save(modelMapper.map(orderDto, Order.class));
		return  modelMapper.map(order, OrderDTO.class);
	}

	@Override
	public boolean delete(long id) {
		orderRepository.deleteById(id);
		return true;
	}
}
