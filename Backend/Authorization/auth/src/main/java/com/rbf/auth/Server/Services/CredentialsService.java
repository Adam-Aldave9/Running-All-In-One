package com.rbf.auth.Server.Services;

import com.rbf.auth.Server.Exceptions.CredentialNotFoundException;
import com.rbf.auth.Server.Messaging.Publisher;
import com.rbf.auth.Server.Models.CredentialsModel;
import com.rbf.auth.Server.Repositories.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CredentialsService {
    @Autowired
    private CredentialRepository credentialsRepository;

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
        return credentialsRepository.save(newCredential);
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
                    return credentialsRepository.save(curr);
                })
                .orElseGet(() -> {
                    credential.setCredentialId(id);
                    return credentialsRepository.save(credential);
                });
    }

    //delete credential
    public void deleteCredential(UUID id) {
        credentialsRepository.deleteById(id);
    }
}