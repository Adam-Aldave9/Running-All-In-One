package com.rbf.Scheduling.Server.Repositories;

import com.rbf.Scheduling.Server.Models.SessionsModel;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SessionsRepository extends CrudRepository<SessionsModel, UUID> {
}