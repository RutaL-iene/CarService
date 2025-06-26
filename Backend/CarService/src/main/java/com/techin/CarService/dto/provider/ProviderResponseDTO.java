package com.techin.CarService.dto.provider;


public record ProviderResponseDTO(
        Long id,
        String title,
        String address,
        String manager
) {
}
