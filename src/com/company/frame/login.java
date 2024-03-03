package com.company.frame;

import com.company.DB.AppPreferences;
import com.company.DB.EditData;
import com.company.DB.getData;
import com.company.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;


    public login() {
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(10, 10, 400, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(null); // Use null layout for manual component placement

        JLabel title=new JLabel("Login Form");


        JLabel userLabel = new JLabel("Enter your username : ");
        usernameField=new JTextField();
        JLabel passwordLabel = new JLabel("Enter your password : ");
        passwordField=new JPasswordField();
        JButton button = new JButton("Submit");
        title.setBounds(150,60,100,30);
        userLabel.setBounds(50, 100, 100, 30);
        passwordLabel.setBounds(50, 180, 100, 30);
        usernameField.setBounds(150, 100, 150, 30);
        passwordField.setBounds(150, 180, 150, 30);
        button.setBounds(50,250,100,30);
        add(title);
        add(userLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userId = getData.getUserIdByUsernameAndPassword(usernameField.getText(), passwordField.getText());
                    if (userId != -1) {
                        AppPreferences.saveUserId(userId);
                        new home();
                        dispose();
//                        JOptionPane.showMessageDialog(login.this, "Login successfully");
                    }else {
                        JOptionPane.showMessageDialog(login.this, "Incorrect username or password!");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}