package com.rohanbojja.greeze.services;

import com.rohanbojja.greeze.models.Hospital;
import com.rohanbojja.greeze.models.TestApplication;
import com.rohanbojja.greeze.models.User;
import com.rohanbojja.greeze.repositories.HospitalRepository;
import com.rohanbojja.greeze.repositories.TestApplicationRepository;
import com.rohanbojja.greeze.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    TestApplicationRepository testApplicationRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(String uid) {
        userRepository.deleteById(uid);
    }

    public Optional<User> getUserDetails(String uid) {
        return userRepository.findById(uid);
    }

    public Optional<Iterable<TestApplication>> getUserApplications(String uid) {
        return userRepository.findById(uid).map(
                user -> {
                    List<Long> applicationIds = user.getApplications();
                    return testApplicationRepository.findAllById(applicationIds);
                }
        );
    }

    @Override
    public Optional<Iterable<TestApplication>> getRejectedUserApplications(String uid) {
        return Optional.empty();
    }

    @Override
    public Optional<Iterable<TestApplication>> getScheduledUserApplications(String uid) {
        return Optional.empty();
    }

    @Override
    public Optional<Iterable<TestApplication>> getPreviousUserApplications(String uid) {
        return Optional.empty();
    }

    @Override
    public Optional<Iterable<TestApplication>> getPendingUserApplications(String uid) {
        return Optional.empty();
    }

    public void deleteUserApplication(String uid, Long applicationId) {
        userRepository.findById(uid).map(
                user -> {
                    List<Long> applicationIds = user.getApplications();
                    applicationIds.remove(applicationId);
                    user.setApplications(applicationIds);
                    userRepository.save(user);
                    return null;
                }
        );
    }


    public Optional<TestApplication> applyForTest(String uid, Long hospitalId) {
        return userRepository.findById(uid).map(
                user -> {
                    TestApplication testApplication = testApplicationRepository.save(new TestApplication(0,user.getId(),hospitalId));
                    Long testApplicationId = testApplication.getApplicationId();
                    user.addToApplicationList(testApplicationId);
                    userRepository.save(user);
                    hospitalRepository.findById(hospitalId).map(
                            hospital1 -> {
                                hospital1.addToApplicationList(testApplicationId);
                                hospitalRepository.save(hospital1);
                                return null;
                            }
                    );
                    return testApplication;
                }
        );
    }
}
