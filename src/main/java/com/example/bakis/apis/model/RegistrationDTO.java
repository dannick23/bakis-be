package com.example.bakis.apis.model;

import com.example.bakis.database.entity.RegistrationEntity;

import java.time.LocalDateTime;

public record RegistrationDTO(String firstName,
                              String lastName,
                              String description,
                              LocalDateTime timeFrom,
                              LocalDateTime timeTo,
                              String clientPhoneNumber,
                              String userEmail
) {
    public static RegistrationDTO from(RegistrationEntity registration){
        return new RegistrationDTO(registration.getFirstName(),
                registration.getLastName(),
                registration.getDescription(),
                registration.getTimeFrom(),
                registration.getTimeTo(),
                registration.getClientPhoneNumber(),
                registration.getUser().getEmail());
    }
}
