package com.rbf.auth.Server.Repositories;

import com.rbf.auth.Server.Models.CredentialsModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CredentialRepository extends CrudRepository<CredentialsModel, UUID>, JpaRepository<CredentialsModel, UUID>{
    @Modifying
    @Transactional
    @Query(value = "UPDATE credentials SET username = :newUsername WHERE username = :oldUsername", nativeQuery = true)
    int updateUsername(@Param("newUsername") String newUsername, @Param("oldUsername") String oldUsername);

    @Transactional
    @Query(value = "SELECT EXISTS(SELECT 1 FROM credentials WHERE username = :username AND password = :password)", nativeQuery = true)
    boolean existsByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}