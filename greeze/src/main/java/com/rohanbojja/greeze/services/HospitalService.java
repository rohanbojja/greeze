package com.rohanbojja.greeze.services;

import com.rohanbojja.greeze.models.Hospital;
import com.rohanbojja.greeze.models.TestApplication;

import java.util.List;
import java.util.Optional;

public interface HospitalService {
    Hospital createHospital(Hospital hospital);
    Hospital updateHospitalDetails(Hospital hospital);
    List<Hospital> getHospitalsNearby(String geohash);
    Iterable<TestApplication> getPendingTestApplications(Long hospitalId);
    Iterable<TestApplication> getScheduledApplications(Long hospitalId);
    Iterable<TestApplication> getPreviousApplications(Long hospitalId);
    void rejectApplication(Long hospitalId, Long applicationId);
    Optional<TestApplication> acceptApplication(Long hospitalId, Long applicationId, Long scheduledMSE);

}
