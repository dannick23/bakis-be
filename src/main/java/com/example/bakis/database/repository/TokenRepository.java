package com.example.bakis.database.repository;

import com.example.bakis.database.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {
    @Query(value = """
                select t from TokenEntity t inner join UserEntity u
                on t.user.id = u.id
                where u.email = :email and (t.expired = false or t.revoked = false)
            """)
    List<TokenEntity> findAllValidTokensByUser(String email);

    @Query(value = "select t from TokenEntity t where t.token = :token and (t.expired = false or t.revoked = false) and :email = t.user.email")
    Optional<TokenEntity> findByTokenAndEmail(String token, String email);
    @Query(value = "select t from TokenEntity t where t.token = :token and (t.expired = false or t.revoked = false)")
    Optional<TokenEntity> findByToken(String token);
}
