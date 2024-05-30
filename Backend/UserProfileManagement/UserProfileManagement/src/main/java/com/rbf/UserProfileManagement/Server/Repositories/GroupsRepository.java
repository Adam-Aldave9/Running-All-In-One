package com.rbf.UserProfileManagement.Server.Repositories;

import com.rbf.UserProfileManagement.Server.Models.GroupsModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GroupsRepository extends CrudRepository<GroupsModel, UUID>{
}