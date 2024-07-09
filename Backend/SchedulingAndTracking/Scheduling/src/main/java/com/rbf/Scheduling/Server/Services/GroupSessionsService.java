package com.rbf.Scheduling.Server.Services;

import com.rbf.Scheduling.Server.Exceptions.GroupSessionNotFoundException;
import com.rbf.Scheduling.Server.Models.GroupSessionsModel;
import com.rbf.Scheduling.Server.Repositories.GroupSessionsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GroupSessionsService {
    @Autowired
    private GroupSessionsRepository groupSessionsRepository;

    //get all group sessions
    public List<GroupSessionsModel> getAllGroupSessions() {
        return (List<GroupSessionsModel>) groupSessionsRepository.findAll();
    }

    //get by id
    public GroupSessionsModel getGroupSessionById(UUID id) {
        return groupSessionsRepository.findById(id)
                .orElseThrow(() -> new GroupSessionNotFoundException(id));
    }


    //create group session
    public GroupSessionsModel addGroupSession(GroupSessionsModel newGroupSession) {
        return groupSessionsRepository.save(newGroupSession);
    }

    //update group session
    public GroupSessionsModel updateGroupSession(UUID id, GroupSessionsModel groupSession) {
        return groupSessionsRepository.findById(id)
                .map(session -> {
                    session.setDate(groupSession.getDate());
                    session.setTime(groupSession.getTime());
                    session.setLocation(groupSession.getLocation());
                    return groupSessionsRepository.save(session);
                })
                .orElseGet(() -> {
                    groupSession.setId(id);
                    return groupSessionsRepository.save(groupSession);
                });
    }

    //delete group session
    public void deleteGroupSession(UUID id) {
        groupSessionsRepository.deleteById(id);
    }
}