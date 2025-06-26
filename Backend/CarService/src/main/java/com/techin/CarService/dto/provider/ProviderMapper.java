package com.techin.CarService.dto.provider;

import com.techin.CarService.model.Provider;

import java.util.List;

public class ProviderMapper {

  public static Provider toEntity(ProviderRequestDTO dto) {
    if (dto == null) return null;

    Provider provider = new Provider();
    provider.setTitle(dto.title());
    provider.setAddress(dto.address());
    provider.setManager(dto.manager());

    return provider;
  }

  public static ProviderResponseDTO toResponseDTO(Provider provider) {
    if (provider == null) return null;

    return new ProviderResponseDTO(
            provider.getId(),
            provider.getTitle(),
            provider.getAddress(),
            provider.getManager()
    );
  }

  public static List<ProviderResponseDTO> toResponseDTOList(List<Provider> providers) {
    return providers.stream()
            .map(ProviderMapper::toResponseDTO)
            .toList();
  }
}