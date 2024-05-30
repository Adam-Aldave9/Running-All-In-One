package com.rbf.UserProfileManagement.Server.Repositories;

import com.rbf.UserProfileManagement.Server.Models.GroupMembersModel;
import org.springframework.data.repository.CrudRepository;

public interface GroupMembersRepository extends CrudRepository<GroupMembersModel, Integer> {
}