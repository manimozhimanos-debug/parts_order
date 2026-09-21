package com.employee.order.service;

import java.time.*;
import java.time.format.*;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.order.dto.ApprovalRequest;
import com.employee.order.dto.CreateOrderRequest;
import com.employee.order.dto.PartRequest;
import com.employee.order.model.Employee;
import com.employee.order.model.EmployeeOrder;
import com.employee.order.model.EmployeeRole;
import com.employee.order.model.OrderPart;
import com.employee.order.model.OrderStatus;
import com.employee.order.repository.EmployeeRepository;
import com.employee.order.repository.OrderRepository;

@Service
public class OrderService {
	private final EmployeeRepository employees;
	private final OrderRepository orders;
	private final OrderMapper mapper;

	public OrderService(EmployeeRepository e, OrderRepository o, OrderMapper m) {
		employees = e;
		orders = o;
		mapper = m;
	}

	@Transactional
	public Map<String, Object> create(CreateOrderRequest d) {
		Employee creator = active(d.getEmployeeId());
		Employee l4 = active(d.getL4ApproverId());
		if (l4.getRole() != EmployeeRole.L4_APPROVER)
			throw new IllegalArgumentException("Selected employee is not an L4 approver");
		if (creator.getId().equals(l4.getId()))
			throw new IllegalArgumentException("Creator cannot approve own order");
		Set<String> s = new HashSet<String>();
		EmployeeOrder o = new EmployeeOrder();
		o.setOrderNumber("ORD-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
		o.setCreatedBy(creator);
		o.setL4Approver(l4);
		o.setModeOfTransport(d.getModeOfTransport());
		o.setDeliveryLocation(d.getDeliveryLocation().trim());
		o.setPurpose(d.getPurpose().trim());
		o.setRequesterRemarks(trim(d.getRequesterRemarks()));
		o.setStatus(OrderStatus.PENDING_L4_APPROVAL);
		o.setCreatedAt(LocalDateTime.now());
		for (PartRequest x : d.getParts()) {
			String pn = x.getPartNumber().trim().toUpperCase();
			if (!s.add(pn))
				throw new IllegalArgumentException("Duplicate part number: " + pn);
			OrderPart p = new OrderPart();
			p.setPartNumber(pn);
			p.setDescription(x.getDescription().trim());
			p.setQuantity(x.getQuantity());
			p.setUnitPrice(x.getUnitPrice());
			p.setRemarks(trim(x.getRemarks()));
			o.addPart(p);
		}
		o = orders.save(o);
		return mapper.order(o);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> get(Long id) {
		return mapper.order(detail(id));
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> requester(Long id) {
		return maps(orders.findByCreatedByIdOrderByCreatedAtDesc(id));
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> pending(Long id) {
		return maps(orders.findByL4ApproverIdAndStatusOrderByCreatedAtDesc(id, OrderStatus.PENDING_L4_APPROVAL));
	}

	@Transactional
	public Map<String, Object> approve(Long id, ApprovalRequest d) {
		return action(id, d, false);
	}

	@Transactional
	public Map<String, Object> reject(Long id, ApprovalRequest d) {
		return action(id, d, true);
	}

	private Map<String, Object> action(Long id, ApprovalRequest d, boolean reject) {
		EmployeeOrder o = detail(id);
		Employee a = active(d.getApproverId());
		if (o.getStatus() != OrderStatus.PENDING_L4_APPROVAL)
			throw new IllegalStateException("Order has already been actioned");
		if (!o.getL4Approver().getId().equals(a.getId()))
			throw new SecurityException("Only assigned L4 approver can action this order");
		if (reject && trim(d.getRemarks()) == null)
			throw new IllegalArgumentException("Remarks are required for rejection");
		o.setStatus(reject ? OrderStatus.L4_REJECTED : OrderStatus.L4_APPROVED);
		o.setL4Remarks(trim(d.getRemarks()));
		o.setActionAt(LocalDateTime.now());
		o.setActionBy(a);
		return mapper.order(o);
	}

	private Employee active(Long id) {
		Employee e = employees.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
		if (!e.isActive())
			throw new IllegalArgumentException("Employee is inactive");
		return e;
	}

	private EmployeeOrder detail(Long id) {
		return orders.findDetailedById(id).orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
	}

	private List<Map<String, Object>> maps(List<EmployeeOrder> xs) {
		List<Map<String, Object>> o = new ArrayList<Map<String, Object>>();
		for (EmployeeOrder x : xs)
			o.add(mapper.order(x));
		return o;
	}

	private String trim(String s) {
		return s == null || s.trim().isEmpty() ? null : s.trim();
	}
}
