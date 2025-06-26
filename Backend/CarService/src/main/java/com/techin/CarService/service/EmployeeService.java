package com.techin.CarService.service;

import com.techin.CarService.dto.employee.EmployeeRequestDTO;
import com.techin.CarService.model.Employee;
import com.techin.CarService.model.Provider;
import com.techin.CarService.repository.EmployeeRepository;
import com.techin.CarService.repository.ProviderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final ProviderRepository providerRepository;

  public EmployeeService(EmployeeRepository employeeRepository, ProviderRepository providerRepository) {
    this.employeeRepository = employeeRepository;
    this.providerRepository = providerRepository;
  }

  public Employee toEntity(EmployeeRequestDTO dto) {
    if (dto == null) return null;

    Employee employee = new Employee();
    employee.setName(dto.name());
    employee.setSurname(dto.surname());
    employee.setSpecialisation(dto.specialisation());
    employee.setCity(dto.city());


    return employee;
  }

  public Employee addEmployee(Long providerId, EmployeeRequestDTO dto) {
    Provider provider = providerRepository.findById(providerId)
            .orElseThrow(() -> new RuntimeException("Provider not found with id: " + providerId));

    Employee employee = toEntity(dto);
    employee.setProvider(provider);

    return employeeRepository.save(employee);
  }

  public Employee updateEmployee(Long providerId, Long employeeId, EmployeeRequestDTO dto) {
    Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

    if (!employee.getProvider().getId().equals(providerId)) {
      throw new RuntimeException("Unauthorized: Employee does not belong to the specified provider");
    }

    employee.setName(dto.name());
    employee.setSurname(dto.surname());
    employee.setSpecialisation(dto.specialisation());
    employee.setCity(dto.city());

    return employeeRepository.save(employee);
  }

  public Employee rateEmployee(Long employeeId, Double rating) {
    Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

    employee.setRating(rating);
    return employeeRepository.save(employee);
  }

  public void deleteEmployee(Long providerId, Long employeeId) {
    Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

    if (!employee.getProvider().getId().equals(providerId)) {
      throw new RuntimeException("Unauthorized: Employee does not belong to the specified provider");
    }

    employeeRepository.delete(employee);
  }
}




