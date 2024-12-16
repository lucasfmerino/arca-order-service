package com.arca.order.modules.orderProduct.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrderProductRegistrationDto(
        @NotNull(message = "User ID is mandatory!")
        UUID productId,

        @NotNull(message = "Order ID is mandatory!")
        UUID orderId,

        @NotBlank(message = "Product name is mandatory!")
        String name,

        @NotNull(message = "Price is mandatory!")
        Double price,

        @NotNull(message = "Quantity is mandatory!")
        Double quantity
)
{
}
