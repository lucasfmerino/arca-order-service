package com.arca.order.modules.order.repository;

import com.arca.order.modules.order.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID>
{
    @Query("SELECT o FROM Order o WHERE o.userId = :userId")
    Page<Order> findByUserId(@Param("userId") UUID userId, Pageable pageable);
}