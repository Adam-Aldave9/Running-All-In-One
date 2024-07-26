package com.rbf.auth.Server.Repositories;

import com.rbf.auth.Server.Models.HistoryModel;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface HistoryRepository extends CrudRepository<HistoryModel, UUID> {
}