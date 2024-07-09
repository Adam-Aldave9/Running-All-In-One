package com.rbf.Scheduling.Server.Repositories;

import com.rbf.Scheduling.Server.Models.SessionParticipantsModel;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SessionParticipantsRepository extends CrudRepository<SessionParticipantsModel, UUID> {
}