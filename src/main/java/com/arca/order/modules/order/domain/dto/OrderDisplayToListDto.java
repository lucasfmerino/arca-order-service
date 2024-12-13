package com.arca.order.modules.order.domain.dto;

import com.arca.order.modules.order.domain.model.Order;
import com.arca.order.modules.orderProduct.domain.dto.OrderProductDisplayDto;
import com.arca.order.modules.orderStatus.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderDisplayToListDto(
        UUID id,
        UUID userId,
        OrderStatus orderStatus,
        LocalDateTime createdAt,
        Double totalPrice
)
{
    public OrderDisplayToListDto(Order order)
    {
        this(
                order.getId(),
                order.getUserId(),
                order.getOrderStatus(),
                order.getCreatedAt(),
                order.getOrderProducts().stream()
                        .mapToDouble(orderProduct -> orderProduct.getPrice() * orderProduct.getQuantity())
                        .sum()

        );
    }
}