package com.rbf.Scheduling.Server.Services;

import com.rbf.Scheduling.Server.Exceptions.GroupParticipantNotFoundException;
import com.rbf.Scheduling.Server.Models.GroupParticipantsModel;
import com.rbf.Scheduling.Server.Repositories.GroupParticipantsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GroupParticipantsService {
    @Autowired
    private GroupParticipantsRepository groupParticipantsRepository;

    //get all group participants
    public List<GroupParticipantsModel> getAllGroupParticipants() {
        return (List<GroupParticipantsModel>) groupParticipantsRepository.findAll();
    }

    //get by id
    public GroupParticipantsModel getGroupParticipantById(UUID id) {
        return groupParticipantsRepository.findById(id)
                .orElseThrow(() -> new GroupParticipantNotFoundException(id));
    }

    //create group participant
    public GroupParticipantsModel addGroupParticipant(GroupParticipantsModel newGroupParticipant) {
        return groupParticipantsRepository.save(newGroupParticipant);
    }

    //update group participant
    public GroupParticipantsModel updateGroupParticipant(UUID id, GroupParticipantsModel groupParticipant) {
        return groupParticipantsRepository.findById(id)
                .map(participant -> {
                    participant.setGroupSessionsId(groupParticipant.getGroupSessionsId());
                    participant.setParticipantName(groupParticipant.getParticipantName());
                    return groupParticipantsRepository.save(participant);
                })
                .orElseGet(() -> {
                    groupParticipant.setId(id);
                    return groupParticipantsRepository.save(groupParticipant);
                });
    }

    //delete group participant
    public void deleteGroupParticipant(UUID id) {
        groupParticipantsRepository.deleteById(id);
    }
}