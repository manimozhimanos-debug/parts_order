package com.employee.order.controller;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.employee.order.dto.ApprovalRequest;
import com.employee.order.dto.CreateOrderRequest;
import com.employee.order.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	private final OrderService s;

	public OrderController(OrderService x) {
		s = x;
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateOrderRequest d) {
		return ResponseEntity.status(HttpStatus.CREATED).body(s.create(d));
	}

	@GetMapping("/{id}")
	public Map<String, Object> get(@PathVariable Long id) {
		return s.get(id);
	}

	@GetMapping("/requester/{id}")
	public List<Map<String, Object>> requester(@PathVariable Long id) {
		return s.requester(id);
	}

	@GetMapping("/l4/{id}/pending")
	public List<Map<String, Object>> pending(@PathVariable Long id) {
		return s.pending(id);
	}

	@PutMapping("/{id}/approve")
	public Map<String, Object> approve(@PathVariable Long id, @Valid @RequestBody ApprovalRequest d) {
		return s.approve(id, d);
	}

	@PutMapping("/{id}/reject")
	public Map<String, Object> reject(@PathVariable Long id, @Valid @RequestBody ApprovalRequest d) {
		return s.reject(id, d);
	}
}
