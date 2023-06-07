package com.example.bakis.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "settings")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class SettingsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean sendSmsToClient;
    private Integer defaultIteration;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "settings", fetch = FetchType.EAGER)
    private SystemEntity system;

    public SettingsEntity() {
    }
}
