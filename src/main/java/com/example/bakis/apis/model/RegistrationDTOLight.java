package com.example.bakis.apis.model;

import com.example.bakis.database.entity.RegistrationEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record RegistrationDTOLight(LocalDateTime timeFrom,
                                   LocalDateTime timeTo) {
    public static RegistrationDTOLight from(RegistrationEntity registration) {
        return new RegistrationDTOLight(registration.getTimeFrom(), registration.getTimeTo());
    }
    public static List<RegistrationDTOLight> getSlots(RegistrationEntity entity, int itter){
        List<RegistrationDTOLight> out = new ArrayList<>();
        var from = entity.getTimeFrom();
        var to = entity.getTimeTo();
        while(from.getSecond() < to.getSecond() && from.plusMinutes(itter).getSecond() < to.getSecond()){
            out.add(new RegistrationDTOLight(from, from.plusMinutes(itter)));
            from = from.plusMinutes(itter);
        }
        return out;
    }
}
