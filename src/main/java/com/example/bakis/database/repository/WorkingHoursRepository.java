package com.example.bakis.database.repository;

import com.example.bakis.database.entity.WorkingHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkingHoursRepository extends JpaRepository<WorkingHoursEntity, Integer> {
    @Query("select w from WorkingHoursEntity w where w.system.name = :systemName")
    List<WorkingHoursEntity> findAllBySystemName(String systemName);
}
