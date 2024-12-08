package com.arca.order.modules.order.domain.dto;

import com.arca.order.modules.orderStatus.OrderStatus;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrderUpdateDto(
        @NotNull(message = "Order ID is mandatory!")
        UUID id,

        OrderStatus orderStatus
)
{

}
