package com.rbf.auth.Server.Controllers;

import com.rbf.auth.Server.Models.HistoryModel;
import com.rbf.auth.Server.Services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    //get all history
    @GetMapping(path="/")
    public ResponseEntity<List<HistoryModel>> getAllHistory() {
        return ResponseEntity.ok(historyService.getAllHistory());
    }
    //get history by id
    @GetMapping(path="/{id}")
    public ResponseEntity<HistoryModel> getHistoryById(UUID id) {
        return ResponseEntity.ok(historyService.getHistoryById(id));
    }

    //create history
    @PostMapping(path="/add")
    public ResponseEntity<HistoryModel> addHistory(HistoryModel newHistory) {
        return ResponseEntity.ok(historyService.addHistory(newHistory));
    }

    //update history
    @PutMapping(path="/{id}")
    public ResponseEntity<HistoryModel> updateHistory(UUID id, HistoryModel history) {
        return ResponseEntity.ok(historyService.updateHistory(id, history));
    }
    //delete history
    @DeleteMapping(path="/{id}")
    public ResponseEntity deleteHistory(UUID id) {
        historyService.deleteHistory(id);
        return ResponseEntity.noContent().build();
    }

}