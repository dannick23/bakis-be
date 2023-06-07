package com.example.bakis.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "registration")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class RegistrationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String description;
    private String clientPhoneNumber;
    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;
    @ManyToOne
    private UserEntity user;
    public RegistrationEntity() {
    }

}
