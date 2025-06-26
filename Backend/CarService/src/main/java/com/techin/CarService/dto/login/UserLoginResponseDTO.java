package com.techin.CarService.dto.login;

import java.util.List;

public record UserLoginResponseDTO(long id, String username, List<String> roles) {
}
