package com.rbf.UserProfileManagement.Server.Controllers;

import com.rbf.UserProfileManagement.Server.DTOs.FindByEitherNameModel;
import com.rbf.UserProfileManagement.Server.Models.UserInformationModel;
import com.rbf.UserProfileManagement.Server.Services.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/userinformation")
public class UserInformationController {
    @Autowired
    private UserInformationService userInformationService;

    // get all users
    @GetMapping(path = "/")
    public ResponseEntity<List<UserInformationModel>> getAllUsers() {
        return ResponseEntity.ok(userInformationService.getAllUsers());
    }

    // get user by id
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserInformationModel> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userInformationService.getUserById(id));
    }

    // get by either name for search bar queries
    @GetMapping(path = "/find/{nameInput}")
    public ResponseEntity<List<Map<String, FindByEitherNameModel>>> findByEitherName(@PathVariable String nameInput) {
        return ResponseEntity.ok(userInformationService.findEitherName(nameInput));
    }

    // get user id be username
    @GetMapping(path = "/findid/{username}")
    public ResponseEntity<Map<String, UUID>> findUserIdByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userInformationService.findUserIdByUsername(username));
    }

    // create user
    @PostMapping(path = "/add")
    public ResponseEntity<UserInformationModel> addUser(@RequestBody UserInformationModel newUser) {
        return ResponseEntity.ok(userInformationService.addUser(newUser));
    }

    // update user
    @PutMapping(path = "/{id}")
    public ResponseEntity<UserInformationModel> updateUser(@PathVariable UUID id, @RequestBody UserInformationModel model) {
        return ResponseEntity.ok(userInformationService.updateUser(id, model));
    }

    // delete user
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable UUID id) {
        userInformationService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}