package com.rbf.auth.Server.Controllers;

import com.rbf.auth.Server.Models.CredentialsModel;
import com.rbf.auth.Server.Services.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/credentials")
public class CredentialsController {
    @Autowired
    private CredentialsService credentialService;

    //get all credentials
    @GetMapping(path="/")
    public ResponseEntity<List<CredentialsModel>> getAllCredentials() {
        return ResponseEntity.ok(credentialService.getAllCredentials());
    }

    //get credential by id
    @GetMapping(path="/{id}")
    public ResponseEntity<CredentialsModel> getCredentialById(@PathVariable UUID id) {
        return ResponseEntity.ok(credentialService.getCredentialById(id));
    }

    //create credential
    @PostMapping(path="/add")
    public ResponseEntity<CredentialsModel> addCredential(@RequestBody CredentialsModel newCredential) {
        return ResponseEntity.ok(credentialService.addCredential(newCredential));
    }

    //update credential
    @PutMapping(path="/{id}")
    public ResponseEntity<CredentialsModel> updateCredential(@PathVariable UUID id, @RequestBody CredentialsModel credential) {
        return ResponseEntity.ok(credentialService.updateCredential(id, credential));
    }

    //update only username
    @PutMapping(path="/updateUsername/{newUsername}/{oldUsername}")
    public ResponseEntity<Integer> updateUsername(@PathVariable String newUsername, @PathVariable String oldUsername) {
        return ResponseEntity.ok(credentialService.updateUsername(newUsername, oldUsername));
    }

    //delete credential
    @DeleteMapping(path="/{id}")
    public ResponseEntity deleteCredential(@PathVariable UUID id) {
        credentialService.deleteCredential(id);
        return ResponseEntity.noContent().build();
    }
}