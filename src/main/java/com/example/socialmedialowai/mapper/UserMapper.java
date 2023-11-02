package com.example.socialmedialowai.mapper;

import com.example.socialmedialowai.dto.response.UserDTO;
import com.example.socialmedialowai.model.UserE;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO map(UserE user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public static List<UserDTO> map(List<UserE> users) {
        return users.stream().map(UserMapper::map).collect(Collectors.toList());
    }
}
