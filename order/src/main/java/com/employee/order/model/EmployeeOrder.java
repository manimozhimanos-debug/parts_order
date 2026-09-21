package com.employee.order.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import com.employee.order.model.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_orders")
public class EmployeeOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "order_number", nullable = false, unique = true)
	private String orderNumber;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "created_by_id")
	private Employee createdBy;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "l4_approver_id")
	private Employee l4Approver;
	@Enumerated(EnumType.STRING)
	@Column(name = "mode_of_transport", nullable = false)
	private TransportMode modeOfTransport;
	@Column(name = "delivery_location", nullable = false)
	private String deliveryLocation;
	@Column(nullable = false, length = 1000)
	private String purpose;
	@Column(name = "requester_remarks", length = 1000)
	private String requesterRemarks;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private OrderStatus status;
	@Column(name = "l4_remarks", length = 1000)
	private String l4Remarks;
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	@Column(name = "action_at")
	private LocalDateTime actionAt;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "action_by_id")
	private Employee actionBy;
	@Version
	@Column(name = "version_no")
	private Long versionNo;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderPart> parts = new ArrayList<OrderPart>();

	public void addPart(OrderPart p) {
		parts.add(p);
		p.setOrder(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long v) {
		id = v;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String v) {
		orderNumber = v;
	}

	public Employee getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Employee v) {
		createdBy = v;
	}

	public Employee getL4Approver() {
		return l4Approver;
	}

	public void setL4Approver(Employee v) {
		l4Approver = v;
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

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus v) {
		status = v;
	}

	public String getL4Remarks() {
		return l4Remarks;
	}

	public void setL4Remarks(String v) {
		l4Remarks = v;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime v) {
		createdAt = v;
	}

	public LocalDateTime getActionAt() {
		return actionAt;
	}

	public void setActionAt(LocalDateTime v) {
		actionAt = v;
	}

	public Employee getActionBy() {
		return actionBy;
	}

	public void setActionBy(Employee v) {
		actionBy = v;
	}

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long v) {
		versionNo = v;
	}

	public List<OrderPart> getParts() {
		return parts;
	}

	public void setParts(List<OrderPart> v) {
		parts = v;
	}
}
