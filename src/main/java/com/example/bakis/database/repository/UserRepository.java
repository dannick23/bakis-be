package com.example.bakis.database.repository;

import com.example.bakis.database.entity.SystemEntity;
import com.example.bakis.database.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
    @Query("select u from UserEntity u where u.system = :system")
    List<UserEntity> findAllBySystem(SystemEntity system);
    @Query("select count(u) > 0 from UserEntity u where u.email= :email")
    boolean existsByEmail(String email);

    @Query("select u from UserEntity u where u.system.name = :systemName and u.authority = 'ADMIN'")
    List<UserEntity> findAllAdmins(String systemName);
}
