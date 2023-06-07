package com.example.bakis.database.repository;

import com.example.bakis.database.entity.RegistrationEntity;
import com.example.bakis.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Integer> {
    @Query("select r from RegistrationEntity r where r.user = :user")
    List<RegistrationEntity> findAllByUser(UserEntity user);
    @Query("select r from RegistrationEntity r where r.user = :user and r.timeFrom = :from and r.timeTo = :to")
    Optional<RegistrationEntity> findSpecificByUser(UserEntity user, LocalDateTime from, LocalDateTime to);
}
