package com.rbf.UserProfileManagement.Server.Services;

import com.rbf.UserProfileManagement.Server.Exceptions.EmployeeNotFoundException;
import com.rbf.UserProfileManagement.Server.Messaging.Publisher;
import com.rbf.UserProfileManagement.Server.Models.PartnersModel;
import com.rbf.UserProfileManagement.Server.Repositories.PartnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
                .orElseThrow(() -> new EmployeeNotFoundException(id));
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
    public void deletePartner(UUID id, String user) {
        partnersRepository.deleteById(id);
        boolean exists = partnersRepository.existsById(id);
        if (exists) {
            // rollback
            publisher.deleteSessions(user, partnersRepository.findById(id).get().getPartnerName());
        }
    }


}