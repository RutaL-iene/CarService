package com.techin.CarService.dto.employee;

import com.techin.CarService.model.Employee;

public class EmployeeMapper {
  public static EmployeeResponseDTO toResponseDTO(Employee employee) {
    if (employee == null) return null;

    return new EmployeeResponseDTO(
            employee.getId(),
            employee.getName(),
            employee.getSurname(),
            employee.getSpecialisation(),
            employee.getCity(),
            employee.getRating(),
            employee.getProvider() != null ? employee.getProvider().getId() : null
    );
  }

  public static Employee toEntity(EmployeeRequestDTO dto) {
    if (dto == null) return null;

    Employee employee = new Employee();
    employee.setName(dto.name());
    employee.setSurname(dto.surname());
    employee.setSpecialisation(dto.specialisation());
    employee.setCity(dto.city());
    employee.setRating(dto.rating() != null ? dto.rating() : 0.0);
    // Note: Provider should be set in service layer after fetching by providerId

    return employee;
  }


}


