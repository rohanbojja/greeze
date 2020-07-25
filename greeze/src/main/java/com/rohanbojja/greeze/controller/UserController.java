package com.rohanbojja.greeze.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.rohanbojja.greeze.models.TestApplication;
import com.rohanbojja.greeze.models.User;
import com.rohanbojja.greeze.services.UserService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody final User user, @RequestHeader("auth_token") String idToken){
        //Call only once
        FirebaseToken decodedToken;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();
            user.setId(uid);
            return ResponseEntity.ok()
                    .body(userService.createUser(user));
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(@RequestHeader("auth_token") String idToken){
        FirebaseToken decodedToken;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();
            return userService.getUserDetails(uid).map(
                    user -> ResponseEntity.ok()
                            .body(user)
            ).orElse(ResponseEntity.notFound().build());
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/user/apply/{hospitalId}")
    @ApiModelProperty(value = "Create a Covid-19 test application to the hospital specified by the hospital ID.")
    public ResponseEntity<TestApplication> applyForTest(@PathVariable Long hospitalId, @RequestHeader("auth_token") String idToken){
        FirebaseToken decodedToken;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();
            return userService.applyForTest(uid,hospitalId).map(
                    user -> ResponseEntity.ok().body(user)
            ).orElse(ResponseEntity.notFound().build());
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user/applications")
    public ResponseEntity<Iterable<TestApplication>> getUserApplications(@RequestHeader("auth_token") String idToken){
        FirebaseToken decodedToken;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();
            return userService.getUserApplications(uid).map(
                    hospitals -> ResponseEntity.ok().body(hospitals)
            ).orElse(ResponseEntity.badRequest().build());
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity<Boolean> deleteUser(@RequestHeader("auth_token") String idToken){
        FirebaseToken decodedToken;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();
            userService.deleteUser(uid);
            return ResponseEntity.ok().build();
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("user/applications/{applicationId}")
    public ResponseEntity<?> deleteUserApplication(@PathVariable final Long applicationId, @RequestHeader("auth_token") String idToken){
        FirebaseToken decodedToken;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();
            userService.deleteUserApplication(uid,applicationId);
            return ResponseEntity.ok().build();
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
