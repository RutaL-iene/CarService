package com.techin.CarService.dto.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeRequestDTO(
        @NotBlank(message = "Name is mandatory")
        String name,

        @NotBlank(message = "Surname is mandatory")
        String surname,

        @NotBlank(message = "Specialisation is mandatory")
        String specialisation,

        @NotBlank(message = "City is mandatory")
        String city,

        // Optional rating, can be null when creating
        Double rating,

        // Provider ID to link employee to a provider
        @NotNull(message = "Provider ID is mandatory")
        Long providerId
) {
}
