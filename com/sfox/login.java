package com.sfox;

import com.sfox.Load.Loader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login extends JFrame{
    private JPanel panel1;
    private JTextField usernameText;
    private JTextField passwordText;
    private JButton loginButton;
    private JButton registerButton;


    public login() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loader l = new Loader();
                ResultSet rs = l.SelectionUser(l.connectDB(),usernameText.getText(),passwordText.getText());
                try {

                    if(rs.next()==false){
                        JOptionPane.showMessageDialog(panel1, "Username or password are incorrect !");

                    }else {
                        rekrutGui mainDirect = new rekrutGui();
                        //mainDirect.setChosen("experience");
                        //System.out.println(mainDirect.getChosen());
                        mainDirect.setVisible(true);
                        dispose();
                    }
                    System.out.println(rs);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register register = new Register();
                register.setVisible(true);
                dispose();
            }
        });
    }
}
