package com.rbf.UserProfileManagement.Server.Repositories;

import com.rbf.UserProfileManagement.Server.Models.PartnersModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PartnersRepository extends CrudRepository<PartnersModel, UUID>, JpaRepository<PartnersModel, UUID> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO partners (partner_id, user_id, username) VALUES (:partner_id, :userid, :username)", nativeQuery = true)
    List<Object[]> addPartner(@Param("partner_id") UUID partner_id, @Param("userid") String userid, @Param("username") String username); //object[] bad practice fix later

}