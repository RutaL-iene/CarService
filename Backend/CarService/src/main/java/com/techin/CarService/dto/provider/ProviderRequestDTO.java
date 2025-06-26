package com.techin.CarService.dto.provider;


import jakarta.validation.constraints.NotBlank;

public record ProviderRequestDTO(
        @NotBlank(message = "Title is mandatory")
        String title,

        @NotBlank(message = "Address is mandatory")
        String address,

        @NotBlank(message = "Manager is mandatory")
        String manager
) {
}
