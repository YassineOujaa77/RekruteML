package com.sfox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Choice  extends  JFrame{
    private JButton byCompanyButton;
    private JButton byExperienceNeededButton;
    private JPanel jpanel;

    public Choice() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(jpanel);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        byExperienceNeededButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PredictionGUI predictionGUI= new PredictionGUI("prediction","experience");
                predictionGUI.setChosen("experience");
                //System.out.println(mainDirect.getChosen());
                predictionGUI.setVisible(true);
                dispose();
            }
        });
        byCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PredictionGUI predictionGUI= new PredictionGUI("prediction","societe");
                predictionGUI.setChosen("societe");
                //System.out.println(mainDirect.getChosen());
                predictionGUI.setVisible(true);
                dispose();
            }
        });
    }
}
