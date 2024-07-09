package com.rbf.UserProfileManagement.Server.Controllers;

import com.rbf.UserProfileManagement.Server.Models.PartnersModel;
import com.rbf.UserProfileManagement.Server.Services.PartnersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/partners")
public class PartnersController {
    @Autowired
    private PartnersService partnersService;

    //get all partners
    @GetMapping(path="/")
    public ResponseEntity<List<PartnersModel>> getAllPartners() {
        return ResponseEntity.ok(partnersService.getAllPartners());
    }

    //get partner by id
    @GetMapping(path="/{id}")
    public ResponseEntity<PartnersModel> getPartnerById(@PathVariable UUID id) {
        return ResponseEntity.ok(partnersService.getPartnerById(id));
    }

    //create partner
    @PostMapping(path="/add")
    public ResponseEntity<PartnersModel> addPartner(@RequestBody PartnersModel newPartner) {
        return ResponseEntity.ok(partnersService.addPartner(newPartner));
    }

    //update partner
    @PutMapping(path="/{id}")
    public ResponseEntity<PartnersModel> updatePartner(@PathVariable UUID id, @RequestBody PartnersModel partner) {
        return ResponseEntity.ok(partnersService.updatePartner(id, partner));
    }

    //delete partner
    // {user} is the current user logged in
    @DeleteMapping(path="/{user}/{id}")
    public ResponseEntity deletePartner(@PathVariable UUID id, @PathVariable String user) {
        partnersService.deletePartner(id, user);
        return ResponseEntity.noContent().build();
    }
}