package com.rbf.UserProfileManagement.Server.Services;

import com.rbf.UserProfileManagement.Server.Exceptions.UserInformationNotFoundException;
import com.rbf.UserProfileManagement.Server.Models.UserInformationModel;
import com.rbf.UserProfileManagement.Server.Repositories.UserInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserInformationService {
    @Autowired
    private UserInformationRepository userInformationRespository;

    // get all users
    public List<UserInformationModel> getAllUsers() {
        return (List<UserInformationModel>) userInformationRespository.findAll();
    }

    // get user by id
    public UserInformationModel getUserById(UUID id) {
        return userInformationRespository.findById(id)
                .orElseThrow(() -> new UserInformationNotFoundException(id));
    }

    // create user
    public UserInformationModel addUser(UserInformationModel newUser) {
        return userInformationRespository.save(newUser);
    }

    // update user
    public UserInformationModel updateUser(UUID id, UserInformationModel model) {
        return userInformationRespository.findById(id)
                .map(user -> {
                    user.setUsername(model.getUsername());
                    user.setName(model.getName());
                    user.setAge(model.getAge());
                    user.setLocation(model.getLocation());
                    user.setExperience(model.getExperience());
                    user.setAvailability(model.getAvailability());
                    return userInformationRespository.save(user);
                })
                .orElseGet(() -> {
                    model.setUserId(id);
                    return userInformationRespository.save(model);
                });
    }

    // delete user
    public void deleteUser(UUID id) {
        userInformationRespository.deleteById(id);
    }
}