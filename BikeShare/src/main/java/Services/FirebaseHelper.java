/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;



/**
 *
 * @author ahcl
 */
public class FirebaseHelper {
        
    public static boolean checkAuthentication (String email, String token) throws FileNotFoundException, IOException, FirebaseAuthException {
//        FileInputStream serviceAccount = new FileInputStream("C:\\Users\\ahcl\\Documents\\NetBeansProjects\\TestMaven\\src\\main\\resources\\bikeshare-fb429-firebase-adminsdk-0g30i-6acfa56ab4.json");
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//            .setDatabaseUrl("https://bikeshare-fb429.firebaseio.com")
//            .build();
//
//        FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
//        FirebaseAuth auth = FirebaseAuth.getInstance(firebaseApp);
//        String cusToken = "eyJhdWQiOiJodHRwczovL2lkZW50aXR5dG9vbGtpdC5nb29nbGVhcGlzLmNvbS9nb29nbGUuaWRlbnRpdHkuaWRlbnRpdHl0b29sa2l0LnYxLklkZW50aXR5VG9vbGtpdCIsImV4cCI6MTU1MjkzMzI0MCwiaWF0IjoxNTUyOTI5NjQwLCJpc3MiOiJmaXJlYmFzZS1hZG1pbnNkay0wZzMwaUBiaWtlc2hhcmUtZmI0MjkuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLCJzdWIiOiJmaXJlYmFzZS1hZG1pbnNkay0wZzMwaUBiaWtlc2hhcmUtZmI0MjkuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLCJ1aWQiOiJhbmhodW5nY29sYW83QGdtYWlsLmNvbSJ9";
////        System.out.println(auth.verifyIdToken);
//        System.out.println(cusToken);
        return true;
    }
    
    public static void main(String[] args) {
        try {
            FirebaseHelper.checkAuthentication("anhhungcolao7@gmail.com", "123");
        } catch (Exception e) {
            System.out.println("failed");
            e.printStackTrace();
        }
    }
    
}
