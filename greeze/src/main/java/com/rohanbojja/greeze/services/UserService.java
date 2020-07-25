package com.rohanbojja.greeze.services;

import com.rohanbojja.greeze.models.Hospital;
import com.rohanbojja.greeze.models.TestApplication;
import com.rohanbojja.greeze.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);

    Iterable<User> getAllUsers();

    void deleteUser(String uid);

    Optional<User> getUserDetails(String uid);

    Optional<Iterable<TestApplication>> getUserApplications(String uid);

    List<TestApplication> getRejectedUserApplications(String uid);

    List<TestApplication> getScheduledUserApplications(String uid);

    List<TestApplication> getPreviousUserApplications(String uid);

    List<TestApplication> getPendingUserApplications(String uid);

    void deleteUserApplication(String uid, Long applicationId);

    Optional<TestApplication> applyForTest(String uid, Long hospitalId);
}
