package com.rbf.UserProfileManagement.Server.Repositories;

import com.rbf.UserProfileManagement.Server.Models.PartnersModel;
import com.rbf.UserProfileManagement.Server.DTOs.UserInfoAndPartnerJoined;
import com.rbf.UserProfileManagement.Server.DTOs.PartnerNameByOwnerModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PartnersRepository extends CrudRepository<PartnersModel, UUID>, JpaRepository<PartnersModel, UUID> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO partners (partner_id, user_id, partner_name) VALUES (:partner_id, :userid, :username)", nativeQuery = true)
    int addPartner(@Param("partner_id") UUID partner_id, @Param("userid") String userid, @Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM partners WHERE user_id = :owningPartner AND partner_name = :secondPartner", nativeQuery = true)
    int deleteSpecificPartner(@Param("owningPartner") UUID owningPartner, @Param("secondPartner") String secondPartner);

    @Transactional
    @Query(value = "SELECT * FROM partners WHERE user_id = :owningPartner", nativeQuery = true)
    PartnersModel getAllByOwningID(@Param("owningPartner") UUID owningPartner);

    /*
    String query = "SELECT partners.partner_name, partners.partner_id\n" +
            "FROM \"user information\"\n" +
            "JOIN partners\n" +
            "ON \"user information\".user_id = partners.user_id\n" +
            "WHERE \"user information\".user_id = :user_id";

     */

    String query = "SELECT partner_name, partner_id FROM partners WHERE user_id = :user_id";

    @Transactional
    @Query(value = query, nativeQuery = true)
    List<Map<String, UserInfoAndPartnerJoined>> getPartnersByOther(@Param("user_id") UUID user_id);


    @Transactional
    @Query(value = "SELECT * FROM partners WHERE user_id = :owningPartner AND partner_name = :secondPartner LIMIT 1", nativeQuery = true)
    //List<PartnersModel> getSpecificPartner(@Param("owningPartner") UUID owningPartner, @Param("secondPartner") String secondPartner);
    PartnersModel getSpecificPartner(@Param("owningPartner") UUID owningPartner, @Param("secondPartner") String secondPartner);

    @Transactional
    @Query(value = "SELECT partner_name FROM partners WHERE user_id = :userId", nativeQuery = true)
    List<Map<String, PartnerNameByOwnerModel>> getPartnerNamesByOwner(@Param("userId") UUID userId);
}