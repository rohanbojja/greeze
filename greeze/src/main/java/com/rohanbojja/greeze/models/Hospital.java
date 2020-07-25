package com.rohanbojja.greeze.models;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "hospitals")
public class Hospital {
    @Id
    Long hospitalId;
    String modId; //UID
    String name;
    String address;
    String geohash;
    Integer available;
    Integer capacity;
    String phoneNo;
    Long lastModifiedMSE;
    Integer hospitalType; // 0 - Test center only ; 1 - Care center only ; 2 - Both
    List<Long> applications;

    public Hospital(Long hospitalId,String modId, String name, String address, String geohash, Integer available, Integer capacity, String phoneNo, Integer hospitalType){
        this.hospitalId = hospitalId;
        this.modId = modId;
        this.name = name;
        this.address = address;
        this.geohash = geohash;
        this.available = available;
        this.capacity = capacity;
        this.phoneNo = phoneNo;
        this.hospitalType = hospitalType;
        this.applications = new ArrayList<Long>();
        this.lastModifiedMSE = System.currentTimeMillis();
    }
    public void addToApplicationList(Long applicationId) {
        this.applications.add(applicationId);
    }
    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Integer getHospitalType() {
        return hospitalType;
    }

    public void setHospitalType(Integer hospitalType) {
        this.hospitalType = hospitalType;
    }

    public List<Long> getApplications() {
        return applications;
    }

    public void setApplications(List<Long> applications) {
        this.applications = applications;
    }

    public Long getLastModifiedMSE() {
        return lastModifiedMSE;
    }

    public void setLastModifiedMSE(Long lastModifiedMSE) {
        this.lastModifiedMSE = lastModifiedMSE;
    }
}
