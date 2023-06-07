package com.example.bakis.database.repository;

import com.example.bakis.database.entity.UserEntity;
import com.example.bakis.database.entity.WorkerShiftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerShiftRepository extends JpaRepository<WorkerShiftEntity, Integer> {
    List<WorkerShiftEntity> findAllByUser(UserEntity user);
}
