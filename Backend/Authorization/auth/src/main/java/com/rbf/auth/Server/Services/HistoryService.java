package com.rbf.auth.Server.Services;

import com.rbf.auth.Server.Exceptions.HistoryNotFoundException;
import com.rbf.auth.Server.Models.HistoryModel;
import com.rbf.auth.Server.Repositories.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    //get all history
    public List<HistoryModel> getAllHistory() {
        return (List<HistoryModel>) historyRepository.findAll();
    }

    //get by id
    public HistoryModel getHistoryById(UUID id) {
        return historyRepository.findById(id)
                .orElseThrow(() -> new HistoryNotFoundException(id));
    }
    //create history
    public HistoryModel addHistory(HistoryModel newHistory) {
        return historyRepository.save(newHistory);
    }

    //update history
    public HistoryModel updateHistory(UUID id, HistoryModel history) {
        return historyRepository.findById(id)
                .map(curr -> {
                    curr.setHistoryId(history.getHistoryId());
                    curr.setCredentialId(history.getCredentialId());
                    curr.setLastLogin(history.getLastLogin());
                    return historyRepository.save(curr);
                })
                .orElseGet(() -> {
                    history.setHistoryId(id);
                    return historyRepository.save(history);
                });
    }

    //delete history
    public void deleteHistory(UUID id) {
        historyRepository.deleteById(id);
    }

}