package com.example.bakis.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "image_collage")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ImageCollageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @ManyToOne
    private SystemEntity system;
    @Lob
    private byte[] image;

    public ImageCollageEntity() {
    }
}
