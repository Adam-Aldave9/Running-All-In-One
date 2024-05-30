package com.rbf.UserProfileManagement.Server.Services;

import com.rbf.UserProfileManagement.Server.Exceptions.EmployeeNotFoundException;
import com.rbf.UserProfileManagement.Server.Models.GroupMembersModel;
import com.rbf.UserProfileManagement.Server.Repositories.GroupMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupMembersService {
    @Autowired
    private GroupMembersRepository groupMembersRepository;

    //get all group members
    public List<GroupMembersModel> getAllGroupMembers() {
        return (List<GroupMembersModel>) groupMembersRepository.findAll();
    }

    //get by id
    public GroupMembersModel getGroupMemberById(Integer id) {
        return groupMembersRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    //create group member
    public GroupMembersModel addGroupMember(GroupMembersModel newGroupMember) {
        return groupMembersRepository.save(newGroupMember);
    }

    //update group member
    public GroupMembersModel updateGroupMember(Integer id, GroupMembersModel groupMember) {
        return groupMembersRepository.findById(id)
                .map(member -> {
                    member.setUser_id(groupMember.getUser_id());
                    member.setGroup_id(groupMember.getGroup_id());
                    return groupMembersRepository.save(member);
                })
                .orElseGet(() -> {
                    groupMember.setId(id);
                    return groupMembersRepository.save(groupMember);
                });
    }

    //delete group member
    public void deleteGroupMember(Integer id) {
        groupMembersRepository.deleteById(id);
    }
}