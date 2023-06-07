package com.example.bakis.apis.model;

import com.example.bakis.database.entity.ImageCollageEntity;

public record ImageInfoDTO(Integer id, String name, String description) {
    public static ImageInfoDTO from (ImageCollageEntity imageCollage){
        return new ImageInfoDTO(imageCollage.getId(), imageCollage.getName(), imageCollage.getDescription());
    }
}
