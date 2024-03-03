package com.company.frame;
import com.company.DB.EditData;
import com.company.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class register extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;

    public  register(){
        setTitle("Register Form");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(10, 10, 400, 500);
        setLayout(null); // Use null layout for manual component placement

        JLabel title = new JLabel("Register Form");

        JLabel userLabel = new JLabel("Enter your username : ");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Enter your passwoord : ");
        passwordField = new JPasswordField();

        JLabel emailLabel = new JLabel("Enter your email");
        emailField = new JTextField();


        JButton registerButton = new JButton("Submit");

        title.setBounds(150, 60, 100, 30);
        userLabel.setBounds(50, 100, 100, 30);
        passwordLabel.setBounds(50, 180, 100, 30);
        emailLabel.setBounds(50, 260, 100, 30);

        usernameField.setBounds(150, 100, 150, 30);
        passwordField.setBounds(150, 180, 150, 30);
        emailField.setBounds(150, 260, 150, 30);
        registerButton.setBounds(160, 320, 100, 30);

        add(title);
        add(userLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(emailLabel);
        add(emailField);

        add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean userAdded = EditData.addUser(new User(usernameField.getText(), emailField.getText(), passwordField.getText()));
                    if (userAdded) {
                        JOptionPane.showMessageDialog(register.this, "Register successfully ... Please Login!");
                        new login();
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(register.this, "Register Failed");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}


