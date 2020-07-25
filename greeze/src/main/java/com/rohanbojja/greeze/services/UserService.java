package com.rohanbojja.greeze.services;

import com.rohanbojja.greeze.models.Hospital;
import com.rohanbojja.greeze.models.TestApplication;
import com.rohanbojja.greeze.models.User;

import java.util.Optional;

public interface UserService {
    User createUser(User user);

    Iterable<User> getAllUsers();

    void deleteUser(String uid);

    Optional<User> getUserDetails(String uid);

    Optional<Iterable<TestApplication>> getUserApplications(String uid);

    Optional<Iterable<TestApplication>> getRejectedUserApplications(String uid);

    Optional<Iterable<TestApplication>> getScheduledUserApplications(String uid);

    Optional<Iterable<TestApplication>> getPreviousUserApplications(String uid);

    Optional<Iterable<TestApplication>> getPendingUserApplications(String uid);

    void deleteUserApplication(String uid, Long applicationId);

    Optional<TestApplication> applyForTest(String uid, Long hospitalId);
}
