package com.rbf.UserProfileManagement.Server.Repositories;

import com.rbf.UserProfileManagement.Server.Models.UserInformationModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserInformationRepository extends CrudRepository<UserInformationModel, UUID>, JpaRepository<UserInformationModel, UUID>{
    @Modifying
    @Transactional
    @Query(value = "UPDATE UserInformation SET username = :newUsername WHERE username = :oldUsername", nativeQuery = true) //need where statement
    //@Param for proper
    int updateUsername(@Param("newUsername") String newUsername, @Param("oldUsername") String oldUsername);

}