package com.employee.order.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PartRequest {
	@NotBlank
	private String partNumber;
	@NotBlank
	private String description;
	@NotNull
	@Min(1)
	private Integer quantity;
	@NotNull
	@DecimalMin("0.0")
	private BigDecimal unitPrice;
	private String remarks;

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