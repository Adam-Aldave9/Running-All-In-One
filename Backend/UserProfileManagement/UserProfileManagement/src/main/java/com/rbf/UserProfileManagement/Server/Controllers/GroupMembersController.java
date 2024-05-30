package com.rbf.UserProfileManagement.Server.Controllers;

import com.rbf.UserProfileManagement.Server.Models.GroupMembersModel;
import com.rbf.UserProfileManagement.Server.Services.GroupMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/groupmembers")
public class GroupMembersController {
    @Autowired
    private GroupMembersService groupMembersService;

    //get all group members
    @GetMapping(path="/")
    public ResponseEntity<List<GroupMembersModel>> getAllGroupMembers() {
        return ResponseEntity.ok(groupMembersService.getAllGroupMembers());
    }

    //get by id
    @GetMapping(path="/{id}")
    public ResponseEntity<GroupMembersModel> getGroupMemberById(@PathVariable Integer id) {
        return ResponseEntity.ok(groupMembersService.getGroupMemberById(id));
    }

    //create group member
    @PostMapping(path="/add")
    public ResponseEntity<GroupMembersModel> addGroupMember(@RequestBody GroupMembersModel newGroupMember) {
        return ResponseEntity.ok(groupMembersService.addGroupMember(newGroupMember));
    }

    //update group member
    @PutMapping(path="/{id}")
    public ResponseEntity<GroupMembersModel> updateGroupMember(@PathVariable Integer id, @RequestBody GroupMembersModel groupMember) {
        return ResponseEntity.ok(groupMembersService.updateGroupMember(id, groupMember));
    }

    //delete group member
    @DeleteMapping(path="/{id}")
    public ResponseEntity deleteGroupMember(@PathVariable Integer id) {
        groupMembersService.deleteGroupMember(id);
        return ResponseEntity.noContent().build();
    }
}