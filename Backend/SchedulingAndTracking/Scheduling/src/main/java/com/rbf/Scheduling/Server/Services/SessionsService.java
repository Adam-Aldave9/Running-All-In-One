package com.rbf.Scheduling.Server.Services;

import com.rbf.Scheduling.Server.Exceptions.SessionNotFoundException;
import com.rbf.Scheduling.Server.Models.SessionsModel;
import com.rbf.Scheduling.Server.Repositories.SessionsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SessionsService {
    @Autowired
    private SessionsRepository sessionsRepository;

    //get all sessions
    public List<SessionsModel> getAllSessions() {
        return (List<SessionsModel>) sessionsRepository.findAll();
    }

    //get by id
    public SessionsModel getSessionById(UUID id) {
        return sessionsRepository.findById(id)
                .orElseThrow(() -> new SessionNotFoundException(id));
    }

    //create session
    public SessionsModel addSession(SessionsModel newSession) {
        return sessionsRepository.save(newSession);
    }

    //update session
    public SessionsModel updateSession(UUID id, SessionsModel session) {
        return sessionsRepository.findById(id)
                .map(s -> {
                    s.setDate(session.getDate());
                    s.setTime(session.getTime());
                    s.setLocation(session.getLocation());
                    return sessionsRepository.save(s);
                })
                .orElseGet(() -> {
                    session.setSessionId(id);
                    return sessionsRepository.save(session);
                });
    }

    //delete session
    public void deleteSession(UUID id) {
        sessionsRepository.deleteById(id);
    }
}