package com.rbf.UserProfileManagement.Server.Repositories;

import com.rbf.UserProfileManagement.Server.Models.PartnersModel;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PartnersRepository extends CrudRepository<PartnersModel, UUID> {
}