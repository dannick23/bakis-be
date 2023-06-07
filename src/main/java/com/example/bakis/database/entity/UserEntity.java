package com.example.bakis.database.entity;

import com.example.bakis.database.model.UserAuthority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String description;
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    private UserAuthority authority;
    @ManyToOne(fetch = FetchType.EAGER)
    private SystemEntity system;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<WorkerShiftEntity> workerShifts;
    @OneToMany(mappedBy = "user")
    List<RegistrationEntity> registrationEntities;
    @OneToMany(mappedBy = "user")
    private List<TokenEntity> tokens;

    public UserEntity() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(authority.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
