package com.example.bakis.utils;

import com.example.bakis.database.entity.*;
import com.example.bakis.database.model.ShiftType;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DefaultValues {
    public static List<WorkerShiftEntity> defaultWorkerShift(UserEntity user) {
        List<WorkerShiftEntity> shifts = new ArrayList<>();
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = startDateTime.plusMonths(1);

        while (startDateTime.isBefore(endDateTime)) {
            if (startDateTime.getDayOfWeek() != DayOfWeek.SATURDAY && startDateTime.getDayOfWeek() != DayOfWeek.SUNDAY) {
                shifts.add(WorkerShiftEntity
                        .builder()
                        .timeFrom(startDateTime.with(LocalTime.of(8, 0)))
                        .timeTo(startDateTime.with(LocalTime.of(17, 0)))
                        .user(user)
                        .shiftType(ShiftType.REGULAR)
                        .build());
            }
            startDateTime = startDateTime.plusDays(1);
        }

        return shifts;
    }

    public static List<WorkingHoursEntity> defaultWorkingHours(SystemEntity system) {
        List<WorkingHoursEntity> shifts = new ArrayList<>();
        LocalDateTime startDateTime = LocalDateTime.now();
        for (int i = 0; i < 5; i++) {
            shifts.add(WorkingHoursEntity
                    .builder()
                    .timeFrom(startDateTime.with(LocalTime.of(8, 0)))
                    .timeTo(startDateTime.with(LocalTime.of(17, 0)))
                    .dayOfWeek(i)
                    .system(system)
                    .build());
            startDateTime = startDateTime.plusDays(1);
        }

        return shifts;
    }
    public static SettingsEntity defaultSettings(){
        return SettingsEntity
                .builder()
                .sendSmsToClient(false)
                .build();
    }
}

