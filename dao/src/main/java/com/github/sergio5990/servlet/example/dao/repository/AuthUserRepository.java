package com.github.sergio5990.servlet.example.dao.repository;

import com.github.sergio5990.servlet.example.dao.entity.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUserEntity, Long> {
    Optional<AuthUserEntity> findByLogin(String login);

    @Modifying(clearAutomatically = true)
    @Query("update AuthUserEntity set password= :password where id = :authUserId")
    void updatePassword(@Param("authUserId") Long authUserId, @Param("password") String password);
}
