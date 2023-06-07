package com.example.bakis.database.repository;

import com.example.bakis.database.entity.ImageCollageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageCollageRepository extends JpaRepository<ImageCollageEntity, Integer> {
    @Query("select i from ImageCollageEntity i where i.system.name = :systemName")
    List<ImageCollageEntity> findAllBySystemName(String systemName);
    @Modifying
    @Query("delete from ImageCollageEntity i where i.id = :id")
    void deleteById(Integer id);
}
