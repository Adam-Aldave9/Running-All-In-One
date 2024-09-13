package com.rbf.auth.Server.Repositories;

import com.rbf.auth.Server.Models.HistoryModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.UUID;

public interface HistoryRepository extends CrudRepository<HistoryModel, UUID>, JpaRepository<HistoryModel, UUID> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE history SET last_login = :lastLogin WHERE credential_id = :credentialId", nativeQuery = true)
    int updateLastLogin(@Param("credentialId") UUID credentialId, @Param("lastLogin") LocalDate lastLogin);
}