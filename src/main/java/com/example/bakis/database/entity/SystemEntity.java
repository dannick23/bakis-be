package com.example.bakis.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "systems")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class SystemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String aboutUs;
    private String address;
    @OneToMany(mappedBy = "system", fetch = FetchType.EAGER)
    private List<UserEntity> users;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "system")
    private List<WorkingHoursEntity> workingHours;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SettingsEntity settings;
    @OneToMany(mappedBy = "system")
    private List<ImageCollageEntity> imageCollageEntity;

    public SystemEntity() {
    }
}
