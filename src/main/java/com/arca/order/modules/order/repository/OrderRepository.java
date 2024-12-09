package com.arca.order.modules.order.repository;

import com.arca.order.modules.order.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID>
{

}
