package com.rbf.Scheduling.Server.Repositories;

import com.rbf.Scheduling.Server.Models.SessionJoinModel;
import com.rbf.Scheduling.Server.Models.SessionParticipantsModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface SessionParticipantsRepository extends CrudRepository<SessionParticipantsModel, UUID>, JpaRepository<SessionParticipantsModel, UUID>{
    String updateUsernameQuery = "UPDATE \"session participants\" SET username = :newUsername WHERE username = :oldUsername " +
            "AND EXISTS (SELECT 1 FROM \"session participants\" WHERE username = :oldUsername)";
    @Modifying
    @Transactional
    @Query(value = updateUsernameQuery, nativeQuery = true)
    int updateUsername(@Param("newUsername") String newUsername, @Param("oldUsername") String oldUsername);


    String deleteSessionsQuery = "DELETE FROM \"session participants\" WHERE (partner_one = :firstPartner " +
            "AND partner_two = :secondPartner) OR (partner_one = :secondPartner AND partner_two = :firstPartner)";
    @Modifying
    @Transactional
    @Query(value = deleteSessionsQuery, nativeQuery = true)
    int deleteSessions(@Param("firstPartner") String firstPartner, @Param("secondPartner") String secondPartner);


    String getSessionsAndParticipantsQuery = "SELECT \"session participants\".partner_one, \"session participants\".partner_two, sessions.date, sessions.location, sessions.time, sessions.session_id\n" +
            "FROM sessions\n" +
            "JOIN \"session participants\"\n" +
            "ON \"session participants\".session_id = sessions.session_id\n" +
            "WHERE \"session participants\".partner_one = :username OR \"session participants\".partner_two = :username";

    @Transactional
    @Query(value = getSessionsAndParticipantsQuery, nativeQuery = true)
    List<Map<String, SessionJoinModel>> getSessionsAndParticipants(@Param("username") String username);
    //List<SessionJoinModel> getSessionsAndParticipants(@Param("username") String username);
}