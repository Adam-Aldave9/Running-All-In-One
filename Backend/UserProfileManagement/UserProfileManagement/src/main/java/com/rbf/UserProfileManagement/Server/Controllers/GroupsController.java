package com.rbf.UserProfileManagement.Server.Controllers;

import com.rbf.UserProfileManagement.Server.Models.GroupsModel;
import com.rbf.UserProfileManagement.Server.Services.GroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/groups")
public class GroupsController {
    @Autowired
    private GroupsService groupsService;

    //get all groups
    @GetMapping(path="/")
    public ResponseEntity<List<GroupsModel>> getAllGroups() {
        return ResponseEntity.ok(groupsService.getAllGroups());
    }

    //get group by id
    @GetMapping(path="/{id}")
    public ResponseEntity<GroupsModel> getGroupById(@PathVariable UUID id) {
        return ResponseEntity.ok(groupsService.getGroupById(id));
    }

    //create group
    @PostMapping(path="/add")
    public ResponseEntity<GroupsModel> addGroup(@RequestBody GroupsModel newGroup) {
        return ResponseEntity.ok(groupsService.addGroup(newGroup));
    }

    //update group
    @PutMapping(path="/{id}")
    public ResponseEntity<GroupsModel> updateGroup(@PathVariable UUID id, @RequestBody GroupsModel group) {
        return ResponseEntity.ok(groupsService.updateGroup(id, group));
    }

    //delete group
    @DeleteMapping(path="/{id}")
    public ResponseEntity deleteGroup(@PathVariable UUID id) {
        groupsService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }
}