package com.employee.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.order.dto.EmployeeRequest;
import com.employee.order.model.Employee;
import com.employee.order.model.EmployeeRole;
import com.employee.order.repository.EmployeeRepository;

@Service
public class EmployeeService {
	private final EmployeeRepository repo;
	private final OrderMapper mapper;

	public EmployeeService(EmployeeRepository r, OrderMapper m) {
		repo = r;
		mapper = m;
	}

	@Transactional
	public Map<String, Object> create(EmployeeRequest d) {
		Employee e = new Employee();
		e.setEmployeeNumber(d.getEmployeeNumber().trim());
		e.setEmployeeName(d.getEmployeeName().trim());
		e.setEmail(d.getEmail().trim().toLowerCase());
		e.setDepartment(d.getDepartment().trim());
		e.setRole(d.getRole());
		e.setActive(true);
		return mapper.employee(repo.save(e));
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> all() {
		return map(repo.findByActiveTrueOrderByEmployeeNameAsc());
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> l4() {
		return map(repo.findByRoleAndActiveTrueOrderByEmployeeNameAsc(EmployeeRole.L4_APPROVER));
	}

	private List<Map<String, Object>> map(List<Employee> xs) {
		List<Map<String, Object>> out = new ArrayList<Map<String, Object>>();
		for (Employee e : xs)
			out.add(mapper.employee(e));
		return out;
	}
}
