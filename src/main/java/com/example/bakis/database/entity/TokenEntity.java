package com.example.bakis.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String token;
    private boolean revoked;
    private boolean expired;
    private LocalDateTime expiryDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    public TokenEntity() {
    }
}
