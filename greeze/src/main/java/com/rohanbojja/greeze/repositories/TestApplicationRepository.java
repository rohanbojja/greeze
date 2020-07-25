package com.rohanbojja.greeze.repositories;

import com.rohanbojja.greeze.models.TestApplication;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

public interface TestApplicationRepository extends DatastoreRepository<TestApplication,Long> {
}
