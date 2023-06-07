package com.example.bakis.apis.model;

import com.example.bakis.database.entity.SettingsEntity;

public record SettingsDTO(Integer defaultIteration) {
    public static SettingsDTO from(SettingsEntity settings){
        return new SettingsDTO(settings.getDefaultIteration());
    }
}
