package com.rbf.Scheduling.Server.Repositories;

import com.rbf.Scheduling.Server.Models.GroupSessionsModel;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GroupSessionsRepository extends CrudRepository<GroupSessionsModel, UUID> {
}