package com.techin.CarService.dto.user;


import com.techin.CarService.model.Role;

import java.util.List;

public record UserResponseDTO(
        long id,
        String name,
        String surname,
        String email,
        String phoneNumber,
        String username,
        List<Role> roles) {
}
