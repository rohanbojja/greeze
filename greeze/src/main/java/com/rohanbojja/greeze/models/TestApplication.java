package com.rohanbojja.greeze.models;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Entity(name = "testApplications")
public class TestApplication {

    @Id
    Long applicationId;
    Integer status; // 0 - Pending ; 1 - Scheduled ; 2 - Rejected ; 3 - Previous
    String uid;
    Long hospitalId;
    Long createdMSE;
    Long scheduledMSE;

    public TestApplication(Integer status, String uid, Long hospitalId) {
        this.status = status;
        this.uid = uid;
        this.hospitalId = hospitalId;
        this.createdMSE = System.currentTimeMillis();
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Long getCreatedMSE() {
        return createdMSE;
    }

    public void setCreatedMSE(Long createdMSE) {
        this.createdMSE = createdMSE;
    }

    public Long getScheduledMSE() {
        return scheduledMSE;
    }

    public void setScheduledMSE(Long scheduledMSE) {
        this.scheduledMSE = scheduledMSE;
    }
}
