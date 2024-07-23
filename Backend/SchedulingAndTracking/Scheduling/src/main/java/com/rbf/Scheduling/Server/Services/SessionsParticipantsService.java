package com.rbf.Scheduling.Server.Services;

import com.rbf.Scheduling.Server.Exceptions.SessionParticipantNotFoundException;
import com.rbf.Scheduling.Server.Models.SessionParticipantsModel;
import com.rbf.Scheduling.Server.Repositories.SessionParticipantsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SessionsParticipantsService {
    @Autowired
    private SessionParticipantsRepository sessionsParticipantsRepository;

    //get all session participants
    public List<SessionParticipantsModel> getAllSessionParticipants() {
        return (List<SessionParticipantsModel>) sessionsParticipantsRepository.findAll();
    }

    //get by id
    public SessionParticipantsModel getSessionParticipantById(UUID id) {
        return sessionsParticipantsRepository.findById(id)
                .orElseThrow(() -> new SessionParticipantNotFoundException(id));
    }

    //create session participant
    public SessionParticipantsModel addSessionParticipant(SessionParticipantsModel newSessionParticipant) {
        return sessionsParticipantsRepository.save(newSessionParticipant);
    }

    //update session participant
    public SessionParticipantsModel updateSessionParticipant(UUID id, SessionParticipantsModel sessionParticipant) {
        return sessionsParticipantsRepository.findById(id)
                .map(participant -> {
                    participant.setPartnerOne(sessionParticipant.getPartnerOne());
                    participant.setPartnerTwo(sessionParticipant.getPartnerTwo());
                    participant.setSessionId(sessionParticipant.getSessionId());
                    return sessionsParticipantsRepository.save(participant);
                })
                .orElseGet(() -> {
                    sessionParticipant.setParticipantsId(id);
                    return sessionsParticipantsRepository.save(sessionParticipant);
                });
    }

    //delete session participant
    public void deleteSessionParticipant(UUID id) {
        sessionsParticipantsRepository.deleteById(id);
    }
}