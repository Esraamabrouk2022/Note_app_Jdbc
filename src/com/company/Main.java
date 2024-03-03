package com.company;

import com.company.DB.AppPreferences;
import com.company.frame.home;
import com.company.frame.welcome;

import javax.swing.*;
import java.util.prefs.BackingStoreException;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            if (AppPreferences.getUserId() == -1) {
                new welcome();
            }else {
                new home();
            }
        });
    }
}
