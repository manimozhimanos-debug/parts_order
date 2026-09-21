package com.employee.order.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_parts")
public class OrderPart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "order_id")
	private EmployeeOrder order;
	@Column(name = "part_number", nullable = false)
	private String partNumber;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private Integer quantity;
	@Column(name = "unit_price", nullable = false, precision = 18, scale = 2)
	private BigDecimal unitPrice;
	@Column(length = 1000)
	private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long v) {
		id = v;
	}

	public EmployeeOrder getOrder() {
		return order;
	}

	public void setOrder(EmployeeOrder v) {
		order = v;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String v) {
		partNumber = v;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String v) {
		description = v;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer v) {
		quantity = v;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal v) {
		unitPrice = v;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String v) {
		remarks = v;
	}
}
