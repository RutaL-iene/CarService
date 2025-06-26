package com.techin.CarService.controller;

import com.techin.CarService.dto.employee.EmployeeMapper;
import com.techin.CarService.dto.employee.EmployeeRequestDTO;
import com.techin.CarService.dto.employee.EmployeeResponseDTO;
import com.techin.CarService.model.Employee;
import com.techin.CarService.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  private final EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<EmployeeResponseDTO> addEmployee(
          @RequestParam Long providerId,
          @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {

    Employee savedEmployee = employeeService.addEmployee(providerId, employeeRequestDTO);

    EmployeeResponseDTO responseDTO = EmployeeMapper.toResponseDTO(savedEmployee);

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{employeeId}")
  public ResponseEntity<EmployeeResponseDTO> updateEmployee(
          @RequestParam Long providerId,
          @PathVariable Long employeeId,
          @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {

    Employee updatedEmployee = employeeService.updateEmployee(providerId, employeeId, employeeRequestDTO);

    EmployeeResponseDTO responseDTO = EmployeeMapper.toResponseDTO(updatedEmployee);

    return ResponseEntity.ok(responseDTO);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{employeeId}")
  public ResponseEntity<Void> deleteEmployee(
          @RequestParam Long providerId,
          @PathVariable Long employeeId) {

    employeeService.deleteEmployee(providerId, employeeId);

    return ResponseEntity.noContent().build();
  }
}