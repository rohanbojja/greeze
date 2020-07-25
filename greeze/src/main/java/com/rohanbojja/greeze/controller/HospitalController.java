package com.rohanbojja.greeze.controller;

import com.rohanbojja.greeze.models.Hospital;
import com.rohanbojja.greeze.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HospitalController {

    @Autowired
    HospitalService hospitalService;

    @GetMapping("/hospitals")
    public Iterable<Hospital> getAllHospitals(){
        return hospitalService.getAllHospitals();
    }

    @PostMapping("/hospital")
    public Hospital create(@RequestBody final Hospital hospital){
        return hospitalService.createHospital(hospital);
    }

}
