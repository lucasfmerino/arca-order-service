package com.arca.order.modules.order.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrderRegistrationDto(
        @NotNull(message = "User ID is mandatory!")
        UUID userId
)
{

}
