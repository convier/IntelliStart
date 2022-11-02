package com.inl.rst.service.common;

import java.util.List;
import java.util.Optional;

import com.inl.rst.service.OrderDTO;

public interface OrderService {

	List<OrderDTO> all();

	OrderDTO add(OrderDTO order);
	
	Optional<OrderDTO> find(long id);

	boolean delete(long id);
}