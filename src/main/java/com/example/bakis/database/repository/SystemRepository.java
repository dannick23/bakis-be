package com.example.bakis.database.repository;

import com.example.bakis.database.entity.SystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemRepository extends JpaRepository<SystemEntity, Integer> {
    Optional<SystemEntity> findByName(String name);
}
