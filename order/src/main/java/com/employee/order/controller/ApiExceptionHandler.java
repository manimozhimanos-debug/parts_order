package com.employee.order.controller;

import java.time.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler({ IllegalArgumentException.class, IllegalStateException.class })
	public ResponseEntity<Map<String, Object>> bad(RuntimeException e) {
		return body(HttpStatus.BAD_REQUEST, e.getMessage());
	}

	@ExceptionHandler(SecurityException.class)
	public ResponseEntity<Map<String, Object>> forbidden(SecurityException e) {
		return body(HttpStatus.FORBIDDEN, e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> validation(MethodArgumentNotValidException e) {
		return body(HttpStatus.BAD_REQUEST, e.getBindingResult().getFieldErrors().get(0).getField() + ": "
				+ e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
	}

	private ResponseEntity<Map<String, Object>> body(HttpStatus s, String msg) {
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		m.put("timestamp", LocalDateTime.now());
		m.put("status", s.value());
		m.put("message", msg);
		return ResponseEntity.status(s).body(m);
	}
}