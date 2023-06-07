package com.example.bakis.apis.model;

import com.example.bakis.database.model.ShiftType;

import java.time.LocalDateTime;

public record WorkerShiftChangeRequest(LocalDateTime from, LocalDateTime to, ShiftType type) {
}
