package com.rbf.auth.Server.Controllers;

import com.rbf.auth.Server.Models.CredentialsModel;
import com.rbf.auth.Server.Services.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
        try {
            ResponseEntity<CredentialsModel> res =  ResponseEntity.ok(credentialService.addCredential(newCredential));
            if(res.getStatusCode() == HttpStatus.OK) {
                return res;
            }
            else {
                return ResponseEntity.status(res.getStatusCode()).build();
            }
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    //update credential
    @PutMapping(path="/{id}")
    public ResponseEntity<CredentialsModel> updateCredential(@PathVariable UUID id, @RequestBody CredentialsModel credential) {
        return ResponseEntity.ok(credentialService.updateCredential(id, credential));
    }

    // check if user exists
    @GetMapping(path="/verify/{username}/{password}")
    public ResponseEntity<Exists> verifyCredentials(@PathVariable String username, @PathVariable String password) {
        try{
            //System.out.println("output is " +credentialService.verifyCredentials(username, password));
            boolean res = credentialService.verifyCredentials(username, password);
            Exists exists = new Exists(res);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            System.out.println("we are here");
            System.out.println("error is " +e.getMessage());
            return ResponseEntity.ok(new Exists(false));
        }
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


class Exists {
    private boolean exists;

    public Exists(boolean exists) {
        this.exists = exists;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}