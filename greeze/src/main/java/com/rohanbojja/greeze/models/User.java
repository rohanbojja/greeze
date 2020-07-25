package com.rohanbojja.greeze.models;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity(name="users")
public class User {
    @Id
    String id; //Firebase UID
    String displayName;
    String emailId;
    String photoURL;
    List<Long> applications; // applicationid list

    Boolean isMod; //Elevated priveleges
    Long hospitalId; // Null if not mod

    public void setApplications(List<Long> applications) {
        this.applications = applications;
    }

    public User(String id, String displayName, String emailId, String photoURL) {
        this.id = id;
        this.displayName = displayName;
        this.emailId = emailId;
        this.photoURL = photoURL;
        this.isMod = false;
        this.hospitalId = null;
        this.applications = new ArrayList<Long>();
    }

    public Boolean getMod() {
        return isMod;
    }

    public void setMod(Boolean mod) {
        isMod = mod;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public List<Long> getApplications() {
        return applications;
    }

    public void addToApplicationList(Long applicationId) {
        this.applications.add(applicationId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

}
