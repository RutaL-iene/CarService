package com.techin.CarService.controller;

import com.techin.CarService.dto.user.UserMapper;
import com.techin.CarService.dto.user.UserRequestDTO;
import com.techin.CarService.dto.user.UserResponseDTO;
import com.techin.CarService.model.User;
import com.techin.CarService.service.EmployeeService;
import com.techin.CarService.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

  private final UserService userService;
  private final EmployeeService employeeService;

  public UserController(UserService userService, EmployeeService employeeService) {
    this.userService = userService;
    this.employeeService = employeeService;
  }

  @GetMapping
  public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
    List<User> users = userService.findAllUsers();
    List<UserResponseDTO> dtos = users.stream()
            .map(UserMapper::toResponseDTO)
            .toList();
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
    return userService.findUserById(id)
            .map(UserMapper::toResponseDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto) {
    User user = UserMapper.toEntity(dto);
    User savedUser = userService.saveUser(user);
    return ResponseEntity.status(201).body(UserMapper.toResponseDTO(savedUser));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserResponseDTO> updateUser(
          @PathVariable Long id,
          @Valid @RequestBody UserRequestDTO dto) {

    return userService.findUserById(id)
            .map(existingUser -> {
              existingUser.setName(dto.name());
              existingUser.setSurname(dto.surname());
              existingUser.setEmail(dto.email());
              existingUser.setUsername(dto.username());
              // For password updates, handle carefully (hash, validate)
              existingUser.setPassword(dto.password());
              User updatedUser = userService.saveUser(existingUser);
              return ResponseEntity.ok(UserMapper.toResponseDTO(updatedUser));
            })
            .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    if (userService.findUserById(id).isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }

  // --- Client operation: rate employee ---

  @PutMapping("/rate-employee/{employeeId}")
  public ResponseEntity<Void> rateEmployee(
          @PathVariable Long employeeId,
          @RequestParam Double rating) {

    // You might want to validate rating value here (e.g., 0-5)

    try {
      employeeService.rateEmployee(employeeId, rating);
      return ResponseEntity.ok().build();
    } catch (RuntimeException ex) {
      return ResponseEntity.notFound().build();
    }
  }
}
