package com.rohanbojja.greeze.repositories;

import com.rohanbojja.greeze.models.Hospital;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

public interface HospitalRepository extends DatastoreRepository<Hospital,Long> {
    //Implement search
}
