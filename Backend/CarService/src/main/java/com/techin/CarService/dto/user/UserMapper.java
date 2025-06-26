package com.techin.CarService.dto.user;

import com.techin.CarService.dto.login.UserLoginResponseDTO;
import com.techin.CarService.model.Role;
import com.techin.CarService.model.User;

import java.util.List;

public class UserMapper {
  public static User toEntity(UserRequestDTO userRequestDTO) {
    return new User(
            userRequestDTO.name(),
            userRequestDTO.surname(),
            userRequestDTO.email(),
            userRequestDTO.phoneNumber(),
            userRequestDTO.username(),
            userRequestDTO.password(),
            userRequestDTO.roles()
    );
  }

  public static UserResponseDTO toResponseDTO(User user) {
    return new UserResponseDTO(
            user.getId(),
            user.getName(),
            user.getSurname(),
            user.getEmail(),
            user.getPhoneNumber(),
            user.getUsername(),
            user.getRoles()
    );
  }

  public static List<UserResponseDTO> toResponseDTOList(List<User> users) {
    return users.stream()
            .map(u -> new UserResponseDTO(
                    u.getId(),
                    u.getName(),
                    u.getSurname(),
                    u.getEmail(),
                    u.getPhoneNumber(),
                    u.getUsername(),
                    u.getRoles()))
            .toList();
  }

  public static UserLoginResponseDTO toLoginClientResponseDTO(User user) {
    return new UserLoginResponseDTO(
            user.getId(),
            user.getUsername(),
            user.getRoles()
                    .stream()
                    .map(Role::getName)
                    .toList()
    );
  }

}
