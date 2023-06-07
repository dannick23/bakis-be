package com.example.bakis.apis.model;

import com.example.bakis.database.entity.WorkerShiftEntity;

import java.time.LocalDateTime;

public record WorkerShiftDTO(LocalDateTime timeFrom,
                             LocalDateTime timeTo) {
    public static WorkerShiftDTO from(WorkerShiftEntity workerShiftEntity) {
        return new WorkerShiftDTO(workerShiftEntity.getTimeFrom(), workerShiftEntity.getTimeTo());
    }
}
