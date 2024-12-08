package com.arca.order.modules.orderProduct.domain.dto;

import com.arca.order.modules.orderProduct.domain.model.OrderProduct;

import java.util.UUID;

public record OrderProductDisplayDto(
        UUID id,
        UUID productId,
        String name,
        Double price,
        Double quantity
)
{
    public OrderProductDisplayDto(OrderProduct orderProduct)
    {
        this(
                orderProduct.getProductId(),
                orderProduct.getProductId(),
                orderProduct.getName(),
                orderProduct.getPrice(),
                orderProduct.getQuantity()
        );
    }
}
