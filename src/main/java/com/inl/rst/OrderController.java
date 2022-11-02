package com.inl.rst;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.inl.rst.model.Order;
import com.inl.rst.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Operation(summary = "Get all orders")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found orders", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Order.class)) }),
			@ApiResponse(responseCode = "404", description = "Orders not found", content = @Content) })
	@GetMapping("/orders")
	public List<Order> all() {
		return orderService.all();
	}

	// the global filter overrides the ETag value we set here. We can still
	@Operation(summary = "Find order by id")
	@GetMapping(value = "/orders/{id}")
	public ResponseEntity<Order> findWithCustomEtag(@PathVariable("id") final Long id) {
		Order order = orderService.find(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).body(order);
	}

	@Operation(summary = "Add the new order")
	@PostMapping(value = "/orders")
	@ResponseBody
	public Order add(@RequestBody Order order) {
		return orderService.add(order);
	}

	@Operation(summary = "Delete an order by id")
	@DeleteMapping(value = "/v1/orders/{id}")
	public void delete(@PathVariable("id") Long id) {
		orderService.delete(id);
	}

	@Operation(summary = "Delete an order by id with a reason")
	@DeleteMapping(value = "/v2/orders/{id}")
	public void delete(@PathVariable Long id, @PathVariable String reason) {
		orderService.delete(id);
	}
}