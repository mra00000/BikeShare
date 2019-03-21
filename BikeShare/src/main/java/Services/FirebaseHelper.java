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
        if (FirebaseApp.getApps().size() == 0) {
             String credential = "{\n" +
                "  \"type\": \"service_account\",\n" +
                "  \"project_id\": \"bikeshare-fb429\",\n" +
                "  \"private_key_id\": \"6acfa56ab4beb4336990a3b067e7a7a16f4e23aa\",\n" +
                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCpM5BXpgMj3RaO\\nQgbYP0GcuC9sLHfVb93aDur69fbvP7aKJaB2J8oZBi5Xg2bYfmtL5bx3BGDCYI9x\\nWNj/VJNh/zltkpFwDfBDLTjQGW1nngJMDzzsQVpDqgAISiDORh2/9kD4jLOVMrWX\\nuFmt4pJn3m1G8l6roR5GVOrd7xSwrgbultbKuSkk2dMXlHJhgtVVJBC0OKmiHcWK\\n/zUnLE8jaYCw66Xc/RPVRUWXUBKhHxmVPeaVq6wTgmMQbFbFEv8G0ysWpwCQtjfn\\niPpmCsqKMzm4IHhA7uX9nHpkXuRRBwXzcZllK4LDTwoqwmDln9rCUGy2myWHeL/1\\nrTavb4x7AgMBAAECggEAAq73ZM1bnYCbMiOcDEq3pZ61FPHWfV4Ib1JLlId1yBEK\\nVnrjSf42jn5ENqoZrL4XpPzlfTyfXx617QQ4b+ytaChqlOog5AgeVopoHvtzz7AN\\nkZtaQtWg8uj/QQX1R1ka/5I4262dXRE7GJyECdB0XvggRo0qRm4BAaMalO1F0uXv\\nSNr3BeZFHSRZnpYS5yT5U0oxIvU/omNVPmkySiB4VauFwnMzmnALLhG7AimG6AFS\\nIyGM1bsT9Pv0lDigNotwCMZtxFUSCzzYTJuHTASu0dy0bzoi4H40TEwmfjpVRGnd\\nZ9Y02Jr6YLMXHxOFNZqARe4L/pqCfePpfNFsxlT9CQKBgQDaOLDV1T+jMtwJqEiI\\nQxlW2IRMur1l6hpDVAttYIm8CrNI3yIiVc2IG2CWgTVhNTDSZzTNzXf7QvsU1zu/\\nc5oYBKDc5omL1qDnM0kqWyQyn8o2Nop1xzfmABdtv3Tc0Usv8LVW8owXhDW5sAum\\nKsxhROr82xCXQRHncGB934buAwKBgQDGfl12DXGqbs4h0SAP97gEG3R9ZoK2fNd4\\niQaEonmFSHJhUFpoBI56hp3on5y8SrhmPS2ELl5ulgNep5AvT5aB1kFHOi9X1YSp\\nFQ9GOhzqLpZ6f7UnjXeWp/wXLtRXsMTz2GKvW6ocFg6W+bpXAMqcXyxXdt9a0r9I\\niyZ7UyJ6KQKBgFptBXIGZNFmoOFrl2rHizd+jwwQka0qnelqaTXQvCJsuLAorkdW\\nEM4mshaLwife17VISQxC6UX4En0nVz3VVHRBbrs62KJWtOQ5T0c67zv/FNb0MNnv\\nTI2FWjADFxTtsV9TOoGmDkEsaurgNWOM9pYT7eALkstqjlW+Lm3ac1orAoGADDfH\\nBPc64eTEJ2y57oGvAAG4n+dpcFYq5S56Cjv+Aug8siTCC9kL9BrJKYJ5oVVwGEr3\\nvEUmZuQw/OiPhXn+p6tKhVndhCiOa+Hx+L6m/Ps1JAEsY2zfyZQ5fivRwjwNQ300\\nRdr8k5H//+cLQ0O3NPzeu9uDUBHc2FhIXBr36lECgYBIlXTa9/3fKxBvnGAtpc2I\\nkm+F/Rb423B6GNZPkc3SXBYQcZEwLtesKgYHyYJu3az/gqyhoZ8VHCpuEpwpK+pI\\nQxnT6QTWiAuLL2Jdu1RnE/mTEhHuXddwd2RC0j5BWVAMLVEVUpfZ4Reoq/bT8sQB\\nE6UiHXJxCpbhxMClzpbmiQ==\\n-----END PRIVATE KEY-----\\n\",\n" +
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
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob = bucket.create(name, image, "IMAGE/PNG");
        URL url = blob.signUrl(365, TimeUnit.DAYS);
        return url.toString();
    }
    
    public static void main(String[] args) {
        String s = "|a";
        System.out.println(s.split("[|]").length);
    }
    
}
