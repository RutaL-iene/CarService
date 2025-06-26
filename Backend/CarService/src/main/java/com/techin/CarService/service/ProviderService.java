package com.techin.CarService.service;

import com.techin.CarService.model.Provider;
import com.techin.CarService.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

  private final ProviderRepository providerRepository;

  @Autowired
  public ProviderService(ProviderRepository providerRepository) {
    this.providerRepository = providerRepository;
  }

  public List<Provider> findAllProviders() {
    return providerRepository.findAll();
  }

  public Optional<Provider> findProviderById(Long id) {
    return providerRepository.findById(id);
  }

  public Provider saveProvider(Provider provider) {
    return providerRepository.save(provider);
  }

  public void deleteProvider(Long id) {
    providerRepository.deleteById(id);
  }

}
