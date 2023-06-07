package com.example.bakis.database.entity;

import com.example.bakis.database.model.ShiftType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "worker_shift")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class WorkerShiftEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;
    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity user;
    @Enumerated(value = EnumType.STRING)
    private ShiftType shiftType;

    public WorkerShiftEntity() {
    }
}
