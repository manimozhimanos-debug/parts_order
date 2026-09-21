package com.employee.order.model;


import org.springframework.data.annotation.Id;

import com.employee.order.model.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name="employees")
public class Employee {
 @Id 
 @GeneratedValue(strategy=GenerationType.IDENTITY) 
 private Long id;
 @Column(name="employee_number",nullable=false,unique=true) 
 private String employeeNumber;
 @Column(name="employee_name",nullable=false) 
 private String employeeName;
 @Column(nullable=false,unique=true) 
 private String email;
 @Column(nullable=false) 
 private String department;
 @Enumerated(EnumType.STRING)
 @Column(nullable=false) 
 private EmployeeRole role;
 @Column(nullable=false) 
 private boolean active=true;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getEmployeeNumber() {
	return employeeNumber;
}
public void setEmployeeNumber(String employeeNumber) {
	this.employeeNumber = employeeNumber;
}
public String getEmployeeName() {
	return employeeName;
}
public void setEmployeeName(String employeeName) {
	this.employeeName = employeeName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public EmployeeRole getRole() {
	return role;
}
public void setRole(EmployeeRole role) {
	this.role = role;
}
public boolean isActive() {
	return active;
}
public void setActive(boolean active) {
	this.active = active;
}
 
}