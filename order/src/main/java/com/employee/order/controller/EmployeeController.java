package com.employee.order.controller;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.employee.order.dto.EmployeeRequest;
import com.employee.order.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	private final EmployeeService s;

	public EmployeeController(EmployeeService x) {
		s = x;
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody EmployeeRequest d) {
		return ResponseEntity.status(HttpStatus.CREATED).body(s.create(d));
	}

	@GetMapping
	public List<Map<String, Object>> all() {
		return s.all();
	}

	@GetMapping("/l4-approvers")
	public List<Map<String, Object>> l4() {
		return s.l4();
	}
}