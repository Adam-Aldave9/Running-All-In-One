package com.rbf.Scheduling.Server.Repositories;

import com.rbf.Scheduling.Server.Models.GroupParticipantsModel;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GroupParticipantsRepository extends CrudRepository<GroupParticipantsModel, UUID> {
}