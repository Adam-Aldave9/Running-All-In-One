package com.rbf.Scheduling.Server.Controllers;

import com.rbf.Scheduling.Server.Models.GroupParticipantsModel;
import com.rbf.Scheduling.Server.Services.GroupParticipantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/groupparticipants")
public class GroupParticipantsController {
    @Autowired
    private GroupParticipantsService groupParticipantsService;

    //get all group participants
    @GetMapping(path="/")
    public ResponseEntity<List<GroupParticipantsModel>> getAllGroupParticipants() {
        return ResponseEntity.ok(groupParticipantsService.getAllGroupParticipants());
    }

    //get by id
    @GetMapping(path="/{id}")
    public ResponseEntity<GroupParticipantsModel> getGroupParticipantById(@PathVariable UUID id) {
        return ResponseEntity.ok(groupParticipantsService.getGroupParticipantById(id));
    }

    //create group participant
    @PostMapping(path="/add")
    public ResponseEntity<GroupParticipantsModel> addGroupParticipant(@RequestBody GroupParticipantsModel newGroupParticipant) {
        return ResponseEntity.ok(groupParticipantsService.addGroupParticipant(newGroupParticipant));
    }

    //update group participant
    @PutMapping(path="/{id}")
    public ResponseEntity<GroupParticipantsModel> updateGroupParticipant(@PathVariable UUID id, @RequestBody GroupParticipantsModel groupParticipant) {
        return ResponseEntity.ok(groupParticipantsService.updateGroupParticipant(id, groupParticipant));
    }

    //delete group participant
    @DeleteMapping(path="/{id}")
    public ResponseEntity deleteGroupParticipant(@PathVariable UUID id) {
        groupParticipantsService.deleteGroupParticipant(id);
        return ResponseEntity.noContent().build();
    }

}