package com.rbf.Scheduling.Server.Repositories;

import com.rbf.Scheduling.Server.Models.GroupParticipantsModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface GroupParticipantsRepository extends CrudRepository<GroupParticipantsModel, UUID>, JpaRepository<GroupParticipantsModel, UUID>{
    String query = "UPDATE \"group participants\"\n" +
            "SET username = :newUsername\n" +
            "WHERE username = :oldUsername\n" +
            "AND EXISTS (\n" +
            "    SELECT 1 FROM \"group participants\" WHERE username = :oldUsername\n" +
            ")";
    @Modifying
    @Transactional
    @Query(value = query, nativeQuery = true)
    int updateUsername(@Param("newUsername") String newUsername, @Param("oldUsername") String oldUsername);

}