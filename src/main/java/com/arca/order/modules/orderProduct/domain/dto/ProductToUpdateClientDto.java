package com.arca.order.modules.orderProduct.domain.dto;

import com.arca.order.modules.orderStatus.OrderStatus;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProductToUpdateClientDto(
        @NotNull(message = "User ID is mandatory!")
        UUID productId,

        @NotNull(message = "Quantity is mandatory!")
        Integer quantity
)
{
    public ProductToUpdateClientDto(OrderProductDisplayDto orderProductDisplayDto, OrderStatus orderStatus)
    {
        this(
                orderProductDisplayDto.productId(),
                (orderStatus == OrderStatus.CANCELED)
                        ? -orderProductDisplayDto.quantity()
                        : orderProductDisplayDto.quantity()
        );
    }
}
