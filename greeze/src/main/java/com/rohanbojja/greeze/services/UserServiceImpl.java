package com.rohanbojja.greeze.services;

import com.rohanbojja.greeze.models.TestApplication;
import com.rohanbojja.greeze.models.User;
import com.rohanbojja.greeze.repositories.HospitalRepository;
import com.rohanbojja.greeze.repositories.TestApplicationRepository;
import com.rohanbojja.greeze.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
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

    public List<TestApplication> getRejectedUserApplications(String uid) {
        List<TestApplication> rejectedUserApplications = new ArrayList<>();
        getUserApplications(uid).map(
                testApplications -> {
                    testApplications.forEach(
                            testApplication -> {
                                if(testApplication.getStatus()==2){
                                    rejectedUserApplications.add(testApplication);
                                }
                            }
                    );
                    return null;
                }
        );
        return rejectedUserApplications;
    }

    @Override
    public List<TestApplication> getScheduledUserApplications(String uid) {
        List<TestApplication> scheduledUserApplications = new ArrayList<>();
        getUserApplications(uid).map(
                testApplications -> {
                    testApplications.forEach(
                            testApplication -> {
                                if(testApplication.getStatus()==1){
                                    scheduledUserApplications.add(testApplication);
                                }
                            }
                    );
                    return null;
                }
        );
        return scheduledUserApplications;
    }

    @Override
    public List<TestApplication> getPreviousUserApplications(String uid) {
        List<TestApplication> previousUserApplications = new ArrayList<>();
        getUserApplications(uid).map(
                testApplications -> {
                    testApplications.forEach(
                            testApplication -> {
                                if(testApplication.getStatus()==3){
                                    previousUserApplications.add(testApplication);
                                }
                            }
                    );
                    return null;
                }
        );
        return previousUserApplications;
    }

    @Override
    public List<TestApplication> getPendingUserApplications(String uid) {
        List<TestApplication> pendingUserApplications = new ArrayList<>();
        getUserApplications(uid).map(
                testApplications -> {
                    testApplications.forEach(
                            testApplication -> {
                                if(testApplication.getStatus()==0){
                                    pendingUserApplications.add(testApplication);
                                }
                            }
                    );
                    return null;
                }
        );
        return pendingUserApplications;
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
                    TestApplication testApplication = testApplicationRepository.save(new TestApplication(0, user.getId(), hospitalId));
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
