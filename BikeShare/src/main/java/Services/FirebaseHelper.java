/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.StorageClient;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.print.DocFlavor;
import sun.nio.cs.StandardCharsets;



/**
 *
 * @author ahcl
 */
public class FirebaseHelper {
        
    public static String checkAuthentication (String token) {

        //TODO if authenticated, return email
        return "asdf";
    }
    
    public static String upload(byte[] image, String name) throws FileNotFoundException, IOException {
        System.out.println(FirebaseApp.getApps().size());
        if (FirebaseApp.getApps().size() == 0) {
             String credential = "{\n" +
                "  \"type\": \"service_account\",\n" +
                "  \"project_id\": \"bikeshare-fb429\",\n" +
                "  \"private_key_id\": \"eff0d010f42e736cbdb4fd488700cfaf4a99315f\",\n" +
                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDU0Lm6WSV4lj3M\\nwkfHwLmIbJZEKun2bUf6HbnCXJIMcE0cq5TezfWJzwxtXhjpar/PNwXWS9op8U0g\\nfQOCeLtGuxOm50AqXJU6GI/qEsd9iPZ/wMzdiqxtw+WMM+Y37NLW4DQ8okn4V3HW\\nBRwFq3U4FC0rNY8kTxifz11D5r/fQ88XFsIxIiKg1CeMUfSMYA73JTIsGb17bDHq\\nz1C1rVGk49vO9/NYnaBg9l0/IeK0RSkJIkM+kPqzMRB/KQdCBEw2UPFMtCVuEgdt\\nF9tFik63GbG7UhZOULWxgK84Q6N4v0WCsPNpP52V3P++HKPQiEAl0YZqfSX2iHx6\\nerOw1g7PAgMBAAECggEANvaJYUVw1goHgACVRVPTFxbkYEqtDkX/Fuvic/4wgiHm\\n6Iav9jM/PQ5KaVyqFm7Jhxzw1v3UJIz7nG5tH59KzuO2GZ8UBVzP9acuVAfxmcxr\\nzWbgAj+0dMzpViJ5TOxPL8vL8fUAIbGD5wB+0kAMECW13LkCdSAwfeWycrz/xQtn\\nmmQ6psN5TAcaJy3oG0F3XwbUYO/6AIGdyUZWFly5rYoakFOJqlCpnVea/pbgRhvs\\nu6aaZdm5xUqISLHEGGSB7EkwFoNag0KXiLU4cSAA7QlcPDidR9QQ1iF8fkElvu9D\\ntf0KxMsGNmDcomr9xl5QWclEoOsUwfGtrotCxeQ34QKBgQD0MKKd/irzd29Xs1IX\\n1tw9135OhxkOJXiwbSAUy/6ijs04+OZKfJLt/ANeZ1/dMA/cV2quFvFWaut6lpNr\\nP9aE9ggqJkRTRCtI7sQn4Jw9hk4eYNINuEhe7cqwSUTJCbJ30u41bZae+ESjrhik\\nEt00z3RoG2mVY1d1PG9ihT/TWQKBgQDfG6Jv9WgDZgHk1h/3rIHPmo1G7SRVq2GM\\nZcFkrafJVrRLQgb7UkgLJVEBWb2+S21efLr4RmKic7SL6F81jTnQEmjWKIhOsNtr\\nOkjqI+rcJdczzV/LmMPQbJ5FW6BS+eENnVuUmr6T0HVQyltqptQcqzBIWkXnjndN\\nmVKIyAV2ZwKBgAryazfd/xrvDcTGuzVqYnE7SeRHVi42K9Ts73U/N+kdscGOFvTb\\n5NJaa+Q9iiSmVO+x04x+Zs9S97+FJJK4R+uqA++vy3xYIACjA5QuE5MGhFYETSZk\\nTopC6sVAY58HmkKY6CjpAsmZo6oa0ev5FIWbj0JjiDY0P+V3kh6+jn/xAoGAEcAD\\nFmEx20NcVzZp6KyCz19+aewn/eDaq+7Uigv+01fglDQ2lfwf2zMm1LKtE9ywYNoP\\njf0bj80gyhi8qmgH93Pff1pOLeSIMGm8r1QYNVeVXXuzaWFIAucBWIdHpBJZQ1oH\\nlTUSgHQPQVQ4Ha4UJgvAlaEI+rks6LGQDW3McXUCgYEA6Njld6qYXgT9c2jhwTza\\nLPNYZrJq8eLO8SPMziHqXXpiAlnfSuRsuJU/YUP0Lf5OdJsDwlZiadDkT6axkVDD\\nPdK0ZUW1TRO0Rcw+ed+TNtm+G3abvEBZwzguX9usAeyolzq0yDNTm3TdOKy5uT4Y\\n26bKcqI2RwrE2lVVH3jjkbk=\\n-----END PRIVATE KEY-----\\n\",\n" +
                "  \"client_email\": \"firebase-adminsdk-0g30i@bikeshare-fb429.iam.gserviceaccount.com\",\n" +
                "  \"client_id\": \"104714610809059949397\",\n" +
                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-0g30i%40bikeshare-fb429.iam.gserviceaccount.com\"\n" +
                "}";
             InputStream serviceAccount = new ByteArrayInputStream(credential.getBytes(java.nio.charset.StandardCharsets.UTF_8));
             FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("bikeshare-fb429.appspot.com")
                .build();
             FirebaseApp.initializeApp(options);
        }
        System.out.println(FirebaseApp.getApps().size());
        System.out.println(StorageClient.getInstance().bucket());
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob = bucket.create(name, image, "IMAGE/PNG");
        URL url = blob.signUrl(365, TimeUnit.DAYS);
        return url.toString();
    }
    
    public static void main(String[] args) throws IOException {
        FirebaseHelper.upload(new byte[3], "test.png");
    }
    
}
