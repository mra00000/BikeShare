package com.fpt.prm.bikeshare.Helper;

import com.fpt.prm.bikeshare.Entity.User;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class AppEnvironment {
    private static User currentUser;
    private static GoogleSignInClient googleSignInClient;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static GoogleSignInClient getGoogleSignInClient() {
        return googleSignInClient;
    }

    public static void setGoogleSignInClient(GoogleSignInClient googleSignInClient) {
        AppEnvironment.googleSignInClient = googleSignInClient;
    }
}
