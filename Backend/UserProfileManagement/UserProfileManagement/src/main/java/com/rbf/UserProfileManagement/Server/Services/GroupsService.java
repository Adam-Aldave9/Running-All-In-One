package com.rbf.UserProfileManagement.Server.Services;

import com.rbf.UserProfileManagement.Server.Exceptions.EmployeeNotFoundException;
import com.rbf.UserProfileManagement.Server.Models.GroupsModel;
import com.rbf.UserProfileManagement.Server.Repositories.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GroupsService {
    @Autowired
    private GroupsRepository groupsRepository;

    // get all groups
    public List<GroupsModel> getAllGroups() {
        return (List<GroupsModel>) groupsRepository.findAll();
    }

    // get group by id
    public GroupsModel getGroupById(UUID id) {
        return groupsRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    // create group
    public GroupsModel addGroup(GroupsModel newGroup) {
        return groupsRepository.save(newGroup);
    }

    // update group
    public GroupsModel updateGroup(UUID id, GroupsModel model) {
        return groupsRepository.findById(id)
                .map(group -> {
                    group.setUserId(model.getUserId());
                    group.setGroupName(model.getGroupName());
                    return groupsRepository.save(group);
                })
                .orElseGet(() -> {
                    model.setGroupId(id);
                    return groupsRepository.save(model);
                });
    }

    // delete group
    public void deleteGroup(UUID id) {
        groupsRepository.deleteById(id);
    }

}