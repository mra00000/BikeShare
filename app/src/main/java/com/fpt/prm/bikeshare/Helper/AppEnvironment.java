package com.fpt.prm.bikeshare.Helper;

import com.fpt.prm.bikeshare.Entity.User;

public class AppEnvironment {
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }
}
