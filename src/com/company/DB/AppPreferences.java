package com.company.DB;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class AppPreferences {
    private static final String USER_ID_KEY = "user_id";

    public static void saveUserId(int userId) {
        Preferences prefs = Preferences.userNodeForPackage(AppPreferences.class);
        prefs.putInt(USER_ID_KEY, userId);
    }

    public static void deleteUserId() throws BackingStoreException {
        Preferences prefs = Preferences.userNodeForPackage(AppPreferences.class);
        prefs.remove(USER_ID_KEY);
    }

    public static int getUserId() {
        Preferences prefs = Preferences.userNodeForPackage(AppPreferences.class);
        return prefs.getInt(USER_ID_KEY, -1); // Default value if the key is not found
    }
}
