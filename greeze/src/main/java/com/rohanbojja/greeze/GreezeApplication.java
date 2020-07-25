package com.rohanbojja.greeze;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GreezeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreezeApplication.class, args);
	}

}
