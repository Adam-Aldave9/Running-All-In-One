package com.rbf.auth.Server.Services;

import com.rbf.auth.Server.Exceptions.CredentialNotFoundException;
import com.rbf.auth.Server.Messaging.Publisher;
import com.rbf.auth.Server.Models.CredentialsModel;
import com.rbf.auth.Server.Models.HistoryModel;
import com.rbf.auth.Server.Repositories.CredentialRepository;
import com.rbf.auth.Server.Repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CredentialsService {
    @Autowired
    private CredentialRepository credentialsRepository;
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    Publisher publisher;

    //get all credentials
    public List<CredentialsModel> getAllCredentials() {
        return (List<CredentialsModel>) credentialsRepository.findAll();
    }

    //get by id
    public CredentialsModel getCredentialById(UUID id) {
        return credentialsRepository.findById(id)
                .orElseThrow(() -> new CredentialNotFoundException(id));
    }

    //create credential
    public CredentialsModel addCredential(CredentialsModel newCredential) {
        CredentialsModel result = credentialsRepository.save(newCredential);
        if(result.getUsername().equals(newCredential.getUsername())){
            // create base profile in upam
            publisher.createProfile(result.getUsername());
            // create base history record
            HistoryModel history = new HistoryModel();
            history.setCredentialId(result.getCredentialId());
            history.setLastLogin(LocalDate.now());
            historyRepository.save(history);
        }
        return result;
    }

    //update only username
    public int updateUsername(String newUsername, String oldUsername) {
        int feedback = credentialsRepository.updateUsername(newUsername, oldUsername);
        publisher.updateUsernameUPAM(newUsername, oldUsername);
        return feedback;
    }

    //update credential
    public CredentialsModel updateCredential(UUID id, CredentialsModel credential) {
        return credentialsRepository.findById(id)
                .map(curr -> {
                    curr.setCredentialId(credential.getCredentialId());
                    curr.setUsername(credential.getUsername());
                    curr.setPassword(credential.getPassword());
                    curr.setDateCreated(credential.getDateCreated());
                    return credentialsRepository.save(curr);
                })
                .orElseGet(() -> {
                    credential.setCredentialId(id);
                    return credentialsRepository.save(credential);
                });
    }

    public boolean verifyCredentials(String username, String password) {
        boolean res = credentialsRepository.existsByUsernameAndPassword(username, password);
        System.out.println("res 1 is " +res);
        if(res){
            // get id given username
            UUID id = credentialsRepository.findIdByUsername(username);
            LocalDate date = LocalDate.now();
            historyRepository.updateLastLogin(id, date);
        }
        System.out.println("res 2 is " +res);
        return res;
    }

    //delete credential
    public void deleteCredential(UUID id) {
        credentialsRepository.deleteById(id);
    }
}