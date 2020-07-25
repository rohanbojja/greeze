package com.rohanbojja.greeze.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.rohanbojja.greeze.models.Hospital;
import com.rohanbojja.greeze.models.TestApplication;
import com.rohanbojja.greeze.models.User;
import com.rohanbojja.greeze.repositories.HospitalRepository;
import com.rohanbojja.greeze.repositories.TestApplicationRepository;
import com.rohanbojja.greeze.services.HospitalService;
import com.rohanbojja.greeze.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HospitalController {

    @Autowired
    TestApplicationRepository testApplicationRepository;
    @Autowired
    HospitalService hospitalService;
    @Autowired
    HospitalRepository hospitalRepository;
    @Autowired
    UserService userService;

    @PostMapping("/hospital")
    public Hospital create(@RequestBody final Hospital hospital){
        return hospitalService.createHospital(hospital);
    }

    @GetMapping("/hospitals")
    public Iterable<Hospital> getAllHospitals(){
        return hospitalRepository.findAll();
    }

    @GetMapping("/applications")
    public Iterable<TestApplication> getAllApplications(){
        return testApplicationRepository.findAll();
    }

    @DeleteMapping("/hospital/applications/{applicationId}")
    public void rejectApplication(@RequestHeader("auth_token") String idToken, @PathVariable final Long appplicationId){
        FirebaseToken decodedToken;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();
            Boolean isMod = userService.getUserDetails(uid).map(
                    User::getMod
            ).orElse(false);
            if(isMod){
                Long hospitalId = userService.getUserDetails(uid).map(
                        User::getHospitalId
                ).orElse((long) 0);
                if(hospitalId!=0){
                    hospitalService.rejectApplication(hospitalId,appplicationId);
                }
            }
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/hospital/applications/{applicationId}")
    public void acceptApplication(@RequestHeader("auth_token") String idToken, @PathVariable final Long appplicationId){
        FirebaseToken decodedToken;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();
            Boolean isMod = userService.getUserDetails(uid).map(
                    User::getMod
            ).orElse(false);
            if(isMod){
                Long hospitalId = userService.getUserDetails(uid).map(
                        User::getHospitalId
                ).orElse((long) 0);
                if(hospitalId!=0){
                    hospitalService.acceptApplication(hospitalId,appplicationId,System.currentTimeMillis());
                }
            }
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
    }

}
