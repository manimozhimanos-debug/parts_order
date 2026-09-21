package com.employee.order.dto;

import java.util.*;

import com.employee.order.model.TransportMode;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateOrderRequest {
	@NotNull
	private Long employeeId;
	@NotNull
	private Long l4ApproverId;
	@NotNull
	private TransportMode modeOfTransport;
	@NotBlank
	private String deliveryLocation;
	@NotBlank
	private String purpose;
	private String requesterRemarks;
	@NotEmpty
	private List<@Valid PartRequest> parts;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long v) {
		employeeId = v;
	}

	public Long getL4ApproverId() {
		return l4ApproverId;
	}

	public void setL4ApproverId(Long v) {
		l4ApproverId = v;
	}

	public TransportMode getModeOfTransport() {
		return modeOfTransport;
	}

	public void setModeOfTransport(TransportMode v) {
		modeOfTransport = v;
	}

	public String getDeliveryLocation() {
		return deliveryLocation;
	}

	public void setDeliveryLocation(String v) {
		deliveryLocation = v;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String v) {
		purpose = v;
	}

	public String getRequesterRemarks() {
		return requesterRemarks;
	}

	public void setRequesterRemarks(String v) {
		requesterRemarks = v;
	}

	public List<PartRequest> getParts() {
		return parts;
	}

	public void setParts(List<PartRequest> v) {
		parts = v;
	}
}