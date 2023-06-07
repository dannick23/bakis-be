package com.example.bakis.database.entity;

import com.example.bakis.database.model.ShiftType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "shift_change_request")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ShiftChangeRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;
    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity user;
    @Enumerated(value = EnumType.STRING)
    private ShiftType shiftType;

    public ShiftChangeRequestEntity() {
    }
}
