package com.company.frame;

import com.company.DB.AppPreferences;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class welcome extends JFrame {

    public welcome() {
        setTitle("Welcome");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        this.setVisible(true);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to our Note App");
        welcomeLabel.setBounds(50, 50, 300, 30);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginButton.setBounds(50, 100, 100, 30);
        registerButton.setBounds(160, 100, 100, 30);

        add(welcomeLabel);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                dispose();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register register = new register();
                register.setVisible(true);
            }
        });
    }


}

