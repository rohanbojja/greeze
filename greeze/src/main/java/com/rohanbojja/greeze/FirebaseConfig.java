package com.rohanbojja.greeze;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
@Configuration
public class FirebaseConfig {
    @Bean
    public void firebaseInit() throws IOException{
        FirebaseOptions options;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .build();
            FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
            FirebaseAuth.getInstance(firebaseApp);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
