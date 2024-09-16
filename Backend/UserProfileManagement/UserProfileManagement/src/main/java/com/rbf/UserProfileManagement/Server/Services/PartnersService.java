package com.rbf.UserProfileManagement.Server.Services;

import com.rbf.UserProfileManagement.Server.Exceptions.PartnerNotFoundException;
import com.rbf.UserProfileManagement.Server.Messaging.Publisher;
import com.rbf.UserProfileManagement.Server.Models.PartnersModel;
import com.rbf.UserProfileManagement.Server.DTOs.UserInfoAndPartnerJoined;
import com.rbf.UserProfileManagement.Server.Repositories.PartnersRepository;
import com.rbf.UserProfileManagement.Server.DTOs.PartnerNameByOwnerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class PartnersService {
    @Autowired
    private PartnersRepository partnersRepository;
    @Autowired
    private Publisher publisher;
    // get all partners
    public List<PartnersModel> getAllPartners() {
        return (List<PartnersModel>) partnersRepository.findAll();
    }

    // get partner by id
    public PartnersModel getPartnerById(UUID id) {
        return partnersRepository.findById(id)
                .orElseThrow(() -> new PartnerNotFoundException(id));
    }

    // get partners by other
    public List<Map<String, UserInfoAndPartnerJoined>> getPartnersByOther(UUID id) {
        return partnersRepository.getPartnersByOther(id);
    }

    // get partner names by owner
    public List<Map<String, PartnerNameByOwnerModel>> getPartnerNamesByOwner(UUID userId) {
        return partnersRepository.getPartnerNamesByOwner(userId);
    }

    // create partner
    public PartnersModel addPartner(PartnersModel newPartner) {
        return partnersRepository.save(newPartner);
    }

    // update partner
    public PartnersModel updatePartner(UUID id, PartnersModel model) {
        return partnersRepository.findById(id)
                .map(partner -> {
                    partner.setUserID(model.getUserID());
                    partner.setPartnerName(model.getPartnerName());
                    return partnersRepository.save(partner);
                })
                .orElseGet(() -> {
                    model.setId(id);
                    return partnersRepository.save(model);
                });
    }

    // delete partner
    // id is owning partner
    public void deletePartner(UUID id, String user) {
        int res = partnersRepository.deleteSpecificPartner(id, user);
        PartnersModel specificPartner = partnersRepository.getSpecificPartner(id, user);
        // put the partner_name as the second partner in the publisher method
        if (res != 1) { // delete failed need to rollback
            // rollback
            publisher.deleteSessions(user, specificPartner.getPartnerName(), id.toString());
        }
    }

}