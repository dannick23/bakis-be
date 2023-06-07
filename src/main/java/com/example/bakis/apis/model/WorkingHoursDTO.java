package com.example.bakis.apis.model;

import com.example.bakis.database.entity.WorkingHoursEntity;

import java.time.LocalDateTime;

public record WorkingHoursDTO( int dayOfWeek,
         LocalDateTime timeFrom,
         LocalDateTime timeTo) {
    public static WorkingHoursDTO from(WorkingHoursEntity workingHours){
        return new WorkingHoursDTO(workingHours.getDayOfWeek(), workingHours.getTimeFrom(), workingHours.getTimeTo());
    }
}
