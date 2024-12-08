package com.arca.order.modules.order.domain.dto;

import com.arca.order.modules.orderStatus.OrderStatus;
import com.arca.order.modules.order.domain.model.Order;
import com.arca.order.modules.orderProduct.domain.dto.OrderProductDisplayDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderDisplayDto(
        UUID id,
        UUID userId,
        OrderStatus orderStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Double totalPrice,
        List<OrderProductDisplayDto> products
)
{
    public OrderDisplayDto(Order order)
    {
        this(
                order.getId(),
                order.getUserId(),
                order.getOrderStatus(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getOrderProducts().stream()
                        .mapToDouble(orderProduct -> orderProduct.getPrice() * orderProduct.getQuantity())
                        .sum(),
                order.getOrderProducts().stream()
                        .map(OrderProductDisplayDto::new)
                        .toList()
        );
    }
}
