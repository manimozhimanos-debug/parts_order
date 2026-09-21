package com.employee.order.service;

import java.math.*;
import java.util.*;
import org.springframework.stereotype.Component;

import com.employee.order.model.Employee;
import com.employee.order.model.EmployeeOrder;
import com.employee.order.model.OrderPart;

@Component
public class OrderMapper {
	public Map<String, Object> employee(Employee e) {
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		m.put("id", e.getId());
		m.put("employeeNumber", e.getEmployeeNumber());
		m.put("employeeName", e.getEmployeeName());
		m.put("email", e.getEmail());
		m.put("department", e.getDepartment());
		m.put("role", e.getRole());
		m.put("active", e.isActive());
		return m;
	}

	public Map<String, Object> order(EmployeeOrder o) {
		Map<String, Object> m = new LinkedHashMap<String, Object>();
		m.put("id", o.getId());
		m.put("orderNumber", o.getOrderNumber());
		m.put("createdBy", employee(o.getCreatedBy()));
		m.put("l4Approver", employee(o.getL4Approver()));
		m.put("modeOfTransport", o.getModeOfTransport());
		m.put("deliveryLocation", o.getDeliveryLocation());
		m.put("purpose", o.getPurpose());
		m.put("requesterRemarks", o.getRequesterRemarks());
		m.put("status", o.getStatus());
		m.put("l4Remarks", o.getL4Remarks());
		m.put("createdAt", o.getCreatedAt());
		m.put("actionAt", o.getActionAt());
		m.put("actionBy", o.getActionBy() == null ? null : employee(o.getActionBy()));
		List<Map<String, Object>> ps = new ArrayList<Map<String, Object>>();
		BigDecimal total = BigDecimal.ZERO;
		for (OrderPart p : o.getParts()) {
			Map<String, Object> x = new LinkedHashMap<String, Object>();
			BigDecimal line = p.getUnitPrice().multiply(BigDecimal.valueOf(p.getQuantity()));
			x.put("id", p.getId());
			x.put("partNumber", p.getPartNumber());
			x.put("description", p.getDescription());
			x.put("quantity", p.getQuantity());
			x.put("unitPrice", p.getUnitPrice());
			x.put("lineTotal", line);
			x.put("remarks", p.getRemarks());
			ps.add(x);
			total = total.add(line);
		}
		m.put("parts", ps);
		m.put("totalAmount", total);
		return m;
	}
}
