package com.rbf.Scheduling.Server.Controllers;

import com.rbf.Scheduling.Server.Models.GroupSessionsModel;
import com.rbf.Scheduling.Server.Services.GroupSessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/groupsessions")
public class GroupSessionsController {
    @Autowired
    private GroupSessionsService groupSessionsService;

    //get all group sessions
    @GetMapping(path="/")
    public ResponseEntity<List<GroupSessionsModel>> getAllGroupSessions() {
        return ResponseEntity.ok(groupSessionsService.getAllGroupSessions());
    }

    //get by id
    @GetMapping(path="/{id}")
    public ResponseEntity<GroupSessionsModel> getGroupSessionById(@PathVariable UUID id) {
        return ResponseEntity.ok(groupSessionsService.getGroupSessionById(id));
    }

    //create group session
    @PostMapping(path="/add")
    public ResponseEntity<GroupSessionsModel> addGroupSession(@RequestBody GroupSessionsModel newGroupSession) {
        return ResponseEntity.ok(groupSessionsService.addGroupSession(newGroupSession));
    }

    //update group session
    @PutMapping(path="/{id}")
    public ResponseEntity<GroupSessionsModel> updateGroupSession(@PathVariable UUID id, @RequestBody GroupSessionsModel groupSession) {
        return ResponseEntity.ok(groupSessionsService.updateGroupSession(id, groupSession));
    }

    //delete group session
    @DeleteMapping(path="/{id}")
    public ResponseEntity deleteGroupSession(@PathVariable UUID id) {
        groupSessionsService.deleteGroupSession(id);
        return ResponseEntity.noContent().build();
    }
}