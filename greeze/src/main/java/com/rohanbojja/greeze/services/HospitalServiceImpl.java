package com.rohanbojja.greeze.services;

import com.rohanbojja.greeze.models.Hospital;
import com.rohanbojja.greeze.models.TestApplication;
import com.rohanbojja.greeze.repositories.HospitalRepository;
import com.rohanbojja.greeze.repositories.TestApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    TestApplicationRepository testApplicationRepository;

    public Hospital createHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public Optional<Hospital> getHospitalDetails(Long id) {
        return hospitalRepository.findById(id);
    }

    public Hospital updateHospitalDetails(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    //To be implemented
    public List<Hospital> getHospitalsNearby(String geohash) {
        return null;
    }

    public Iterable<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    @Override
    public Iterable<TestApplication> getPendingTestApplications(Long hospitalId) {
        return hospitalRepository.findById(hospitalId).map(
                hospital -> {
                    Iterable<Long> applicationIds= hospital.getApplications();
                    return testApplicationRepository.findAllById(applicationIds);
                }
        ).orElse(new ArrayList<>());
    }

    @Override
    public Iterable<TestApplication> getScheduledApplications(Long hospitalId) {
        return null;
    }

    @Override
    public Iterable<TestApplication> getPreviousApplications(Long hospitalId) {
        return null;
    }

    public void rejectApplication(Long hospitalId, Long applicationId) {
        hospitalRepository.findById(hospitalId).map(
                hospital -> {
                    List<Long> applicationIds = hospital.getApplications();
                    applicationIds.remove(applicationId);
                    hospital.setApplications(applicationIds);
                    hospitalRepository.save(hospital);
                    return null;
                }
        );
        testApplicationRepository.findById(applicationId).map(
                testApplication -> {
                    testApplication.setStatus(2);
                    testApplicationRepository.save(testApplication);
                    return null;
                }
        );
    }

    @Override
    public Optional<TestApplication> acceptApplication(Long hospitalId, Long applicationId, Long scheduledMSE) {
        return testApplicationRepository.findById(applicationId).map(
                testApplication -> {
                    testApplication.setStatus(1);
                    testApplication.setScheduledMSE(scheduledMSE);
                    return testApplicationRepository.save(testApplication);
                }
        );
    }
}
