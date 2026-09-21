package com.employee.order.repository;

import java.util.*;
import org.springframework.data.jpa.repository.*;

import com.employee.order.model.EmployeeOrder;
import com.employee.order.model.OrderStatus;

public interface OrderRepository extends JpaRepository<EmployeeOrder, Long> {
	@EntityGraph(attributePaths = { "createdBy", "l4Approver", "parts", "actionBy" })
	Optional<EmployeeOrder> findDetailedById(Long id);

	@EntityGraph(attributePaths = { "createdBy", "l4Approver", "parts" })
	List<EmployeeOrder> findByCreatedByIdOrderByCreatedAtDesc(Long id);

	@EntityGraph(attributePaths={"createdBy","l4Approver","parts"}) 
	List<EmployeeOrder>  findByL4ApproverIdAndStatusOrderByCreatedAtDesc(Long id,OrderStatus status);
	}