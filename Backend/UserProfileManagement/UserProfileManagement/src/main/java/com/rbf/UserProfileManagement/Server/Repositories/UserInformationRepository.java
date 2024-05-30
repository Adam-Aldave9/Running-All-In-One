package com.rbf.UserProfileManagement.Server.Repositories;

import com.rbf.UserProfileManagement.Server.Models.UserInformationModel;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserInformationRepository extends CrudRepository<UserInformationModel, UUID> {
}