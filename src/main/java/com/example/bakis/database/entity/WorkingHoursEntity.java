package com.example.bakis.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "working_hours")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class WorkingHoursEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int dayOfWeek;
    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;
    @ManyToOne(cascade = CascadeType.ALL)
    private SystemEntity system;

    public WorkingHoursEntity() {
    }
}
