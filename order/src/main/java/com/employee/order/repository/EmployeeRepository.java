package com.employee.order.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.order.model.Employee;
import com.employee.order.model.EmployeeRole;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByActiveTrueOrderByEmployeeNameAsc();

	List<Employee> findByRoleAndActiveTrueOrderByEmployeeNameAsc(EmployeeRole role);
}
