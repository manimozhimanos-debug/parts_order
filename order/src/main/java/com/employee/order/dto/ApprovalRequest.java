package com.employee.order.dto;


import jakarta.validation.constraints.NotNull;

public class ApprovalRequest {
	@NotNull
	private Long approverId;
	private String remarks;

	public Long getApproverId() {
		return approverId;
	}

	public void setApproverId(Long v) {
		approverId = v;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String v) {
		remarks = v;
	}
}