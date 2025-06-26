package com.techin.CarService.dto.employee;

public record EmployeeResponseDTO(
        Long id,
        String name,
        String surname,
        String specialisation,
        String city,
        Double rating,
        Long providerId
) {
}
