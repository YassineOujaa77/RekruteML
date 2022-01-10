package com.sfox;

import com.sfox.DAO.User;
import com.sfox.Load.Loader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame{
    private JTextField username;
    private JPanel panel1;
    private JTextField password;
    private JButton register;
    private JButton goBackButton;


    public Register() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameText = username.getText();
                String passwordText = password.getText();
                User user = new User(usernameText,passwordText);
                Loader l = new Loader();
                l.insertUser(l.connectDB(),user);



            }
        });
    }
}
