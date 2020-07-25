package com.rohanbojja.greeze.repositories;

import com.rohanbojja.greeze.models.User;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

public interface UserRepository extends DatastoreRepository<User,String> {
    User findByEmailId(String email);

}
