package com.employee.order.dto;


import com.employee.order.model.EmployeeRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class EmployeeRequest {
	@NotBlank
	private String employeeNumber;
	@NotBlank
	private String employeeName;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String department;
	@NotNull
	private EmployeeRole role;

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String v) {
		employeeNumber = v;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String v) {
		employeeName = v;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String v) {
		email = v;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String v) {
		department = v;
	}

	public EmployeeRole getRole() {
		return role;
	}

	public void setRole(EmployeeRole v) {
		role = v;
	}
}