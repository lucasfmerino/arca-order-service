package com.arca.order.modules.orderProduct.repository;

import com.arca.order.modules.orderProduct.domain.dto.OrderProductDisplayDto;
import com.arca.order.modules.orderProduct.domain.model.OrderProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID>
{
    @Query("SELECT op FROM OrderProduct op WHERE op.order.id = :orderId")
    Page<OrderProduct> findByOrderId(@Param("orderId") UUID orderId, Pageable pageable);
}
