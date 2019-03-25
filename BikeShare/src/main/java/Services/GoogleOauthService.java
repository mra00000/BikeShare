package Services;

import DAO.UserDAO;
import Model.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class GoogleOauthService {
    private static final String API_KEY = "300209814116-ksobt2t7jm04dekiagfdj2tldshhb454.apps.googleusercontent.com";

    public static String getEmailByIdToken(String token) throws GeneralSecurityException, IOException {
        HttpTransport transport = new ApacheHttpTransport();
        JsonFactory factory = new JacksonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, factory)
                .setAudience(Collections.singletonList(API_KEY))
                .build();
        GoogleIdToken idToken = verifier.verify(token);
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = (String) payload.get("email");
            return email;
        } else {
            return null;
        }
    }
}
