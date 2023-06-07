package com.example.bakis.apis.model;

import com.example.bakis.database.entity.UserEntity;
import com.example.bakis.database.model.UserAuthority;

public record UserDTO(Integer systemId,
                      String firstName,
                      String lastName,
                      String email,
                      String password,
                      String phoneNumber,
                      String description,
                      UserAuthority authority,
                      String jwtToken) {
    public static UserDTO from(UserEntity user){
        return new UserDTO(user.getSystem().getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail().split(";")[0],
                "",
                user.getPhoneNumber(),
                user.getDescription(),
                user.getAuthority(),
                "");
    }
    public static UserDTO from(UserEntity user, String jwtToken){
        return new UserDTO(user.getSystem().getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail().split(";")[0],
                "",
                user.getPhoneNumber(),
                user.getDescription(),
                user.getAuthority(),
                jwtToken);
    }
}
