package com.rbf.UserProfileManagement.Server.Repositories;

import com.rbf.UserProfileManagement.Server.DTOs.FindByEitherNameModel;
import com.rbf.UserProfileManagement.Server.Models.UserInformationModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserInformationRepository extends CrudRepository<UserInformationModel, UUID>, JpaRepository<UserInformationModel, UUID>{
    @Modifying
    @Transactional
    @Query(value = "UPDATE \"user information\" SET username = :newUsername WHERE username = :oldUsername", nativeQuery = true) //need where statement
    //@Param for proper
    int updateUsername(@Param("newUsername") String newUsername, @Param("oldUsername") String oldUsername);

    @Transactional
    @Query(value = "SELECT user_id, username, name, location, availability FROM \"user information\" WHERE username = :nameInput OR name = :nameInput", nativeQuery = true)
    List<Map<String, FindByEitherNameModel>> findByEitherName(@Param("nameInput") String nameInput);

    @Transactional
    @Query(value = "SELECT user_id FROM \"user information\" WHERE username = :username LIMIT 1", nativeQuery = true)
    Map<String, UUID> findUserIdByUsername(@Param("username") String username);
}