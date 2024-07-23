package com.rbf.Scheduling.Server.Repositories;

import com.rbf.Scheduling.Server.Models.SessionParticipantsModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface SessionParticipantsRepository extends CrudRepository<SessionParticipantsModel, UUID>, JpaRepository<SessionParticipantsModel, UUID>{
    String updateUsernameQuery = "UPDATE \"session participants\"\n" +
            "SET username = :newUsername\n" +
            "WHERE username = :oldUsername\n" +
            "AND EXISTS (\n" +
            "    SELECT 1 FROM \"session participants\" WHERE username = :oldUsername\n" +
            ")";
    @Modifying
    @Transactional
    @Query(value = updateUsernameQuery, nativeQuery = true)
    int updateUsername(@Param("newUsername") String newUsername, @Param("oldUsername") String oldUsername);


    String deleteSessionsQuery = "DELETE FROM \"session participants\"\n" +
            "WHERE (column1 = :firstPartner " +
            "AND column2 = :secondPartner) OR (column1 = :secondPartner AND column2 = :firstPartner)";
    @Modifying
    @Transactional
    @Query(value = deleteSessionsQuery, nativeQuery = true)
    int deleteSessions(@Param("firstPartner") String firstPartner, @Param("secondPartner") String secondPartner);
}