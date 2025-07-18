package com.techin.CarService.repository;

import com.techin.CarService.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
  Optional<Provider> findByTitle(String title);


}
