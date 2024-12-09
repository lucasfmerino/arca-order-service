package com.arca.order.modules.orderProduct.repository;

import com.arca.order.modules.orderProduct.domain.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID>
{
}
