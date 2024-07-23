package com.rbf.Scheduling.Server.Controllers;

import com.rbf.Scheduling.Server.Models.SessionsModel;
import com.rbf.Scheduling.Server.Services.SessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/sessions")
public class SessionsController {
    @Autowired
    private SessionsService sessionsService;

    //get all sessions
    @GetMapping(path="/")
    public ResponseEntity<List<SessionsModel>> getAllSessions() {
        return ResponseEntity.ok(sessionsService.getAllSessions());
    }

    //get by id
    @GetMapping(path="/{id}")
    public ResponseEntity<SessionsModel> getSessionById(@PathVariable UUID id) {
        return ResponseEntity.ok(sessionsService.getSessionById(id));
    }

    //create session
    @PostMapping(path="/add")
    public ResponseEntity<SessionsModel> addSession(@RequestBody SessionsModel newSession) {
        return ResponseEntity.ok(sessionsService.addSession(newSession));
    }

    //update session
    @PutMapping(path="/{id}")
    public ResponseEntity<SessionsModel> updateSession(@PathVariable UUID id, @RequestBody SessionsModel session) {
        return ResponseEntity.ok(sessionsService.updateSession(id, session));
    }

    //delete session
    @DeleteMapping(path="/{id}")
    public ResponseEntity deleteSession(@PathVariable UUID id) {
        sessionsService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
}