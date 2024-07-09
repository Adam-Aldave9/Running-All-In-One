package com.rbf.Scheduling.Server.Controllers;

import com.rbf.Scheduling.Server.Models.SessionParticipantsModel;
import com.rbf.Scheduling.Server.Services.SessionsParticipantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/sessionparticipants")
public class SessionParticipantsController {
    @Autowired
    private SessionsParticipantsService sessionParticipantsService;

    //get all session participants
    @GetMapping(path="/")
    public ResponseEntity<List<SessionParticipantsModel>> getAllSessionParticipants() {
        return ResponseEntity.ok(sessionParticipantsService.getAllSessionParticipants());
    }

    //get by id
    @GetMapping(path="/{id}")
    public ResponseEntity<SessionParticipantsModel> getSessionParticipantById(@PathVariable UUID id) {
        return ResponseEntity.ok(sessionParticipantsService.getSessionParticipantById(id));
    }

    //create session participant
    @PostMapping(path="/add")
    public ResponseEntity<SessionParticipantsModel> addSessionParticipant(@RequestBody SessionParticipantsModel newSessionParticipant) {
        return ResponseEntity.ok(sessionParticipantsService.addSessionParticipant(newSessionParticipant));
    }

    //update session participant
    @PutMapping(path="/{id}")
    public ResponseEntity<SessionParticipantsModel> updateSessionParticipant(@PathVariable UUID id, @RequestBody SessionParticipantsModel sessionParticipant) {
        return ResponseEntity.ok(sessionParticipantsService.updateSessionParticipant(id, sessionParticipant));
    }

    //delete session participant
    @DeleteMapping(path="/{id}")
    public ResponseEntity deleteSessionParticipant(@PathVariable UUID id) {
        sessionParticipantsService.deleteSessionParticipant(id);
        return ResponseEntity.noContent().build();
    }
}