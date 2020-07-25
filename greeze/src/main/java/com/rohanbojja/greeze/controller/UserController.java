package com.rohanbojja.greeze.controller;

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
    public ResponseEntity<User> createUser(@RequestBody final User user){
        //Call only once
        return ResponseEntity.ok()
                .body(userService.createUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAllUsers(){
        return ResponseEntity.ok()
                .body(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserDetails(@PathVariable String id){
        return userService.getUserDetails(id).map(
                user -> ResponseEntity.ok()
                        .body(user)
        ).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/user/{id}/{hospitalId}")
    @ApiModelProperty(value = "Create a Covid-19 test application to the hospital specified by the hospital ID.")
    public ResponseEntity<TestApplication> applyForTest(@PathVariable Long hospitalId, @PathVariable String id){
        return userService.applyForTest(id,hospitalId).map(
                user -> ResponseEntity.ok().body(user)
        ).orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/user/{id}/applications")
    public ResponseEntity<Iterable<TestApplication>> getUserApplications(@PathVariable final String uid){
        return userService.getUserApplications(uid).map(
                hospitals -> ResponseEntity.ok().body(hospitals)
        ).orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
