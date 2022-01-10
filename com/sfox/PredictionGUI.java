package com.sfox;

import com.sfox.ModeL.ClassificationTask;
import com.sfox.ModeL.DATALoader;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PredictionGUI extends JFrame{
    private JPanel predictPanel;
    private JButton predireButton;
    private JButton retournerButton;
    private JLabel predictionLabel;
    private JComboBox nivEtudCombo;
    private JComboBox experienceCombo;
    private JComboBox tContratCombo;
    private JComboBox chosenCombo;
    private JComboBox secteurCombo;
    private JComboBox villeCombo;
    private JComboBox fonctionCombo;
    private JLabel chosenlabel;
    private JLabel predictedLabel;
    private  String chosen ;


    public PredictionGUI(String title,String chosen) {
        this.setTitle(title);
        this.setContentPane(predictPanel);
        this.setSize(1500,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DATALoader dload =new DATALoader();
        villeCombo.setModel(dload.getVilleList());
        tContratCombo.setModel(dload.getTypeContratList());
        nivEtudCombo.setModel(dload.getNivEtudeList());
        secteurCombo.setModel(dload.getSecteurList());
        fonctionCombo.setModel(dload.getFonctionList());
        this.chosen = chosen;
        if(this.chosen.equals("experience")){

            chosenlabel.setText("societe");
            chosenCombo.setModel(dload.getSocieteList());
            predictedLabel.setText("experience needed");
            //experienceCombo.setModel(dload.getExperienceList());

        }else {
            //societeCombo.setModel(dload.getSocieteList());
            chosenlabel.setText("experience");
            chosenCombo.setModel(dload.getExperienceList());
            predictedLabel.setText("Company needed");
        }






        predireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DATALoader dload = new DATALoader();
                String predLabel;
                ClassificationTask ct = new ClassificationTask();
//                change db from Test to Emploi
                Instances data = dload.retrieveData("Test");
                System.out.println(data);
                String secteur,fonction,ville,titre,nEtude,experience,typeContrat,societe;
                double[] vals = new double[data.numAttributes()];
                if(chosen.equals("experience")){
                    secteur=secteurCombo.getSelectedItem().toString();
                    fonction=fonctionCombo.getSelectedItem().toString();
                    societe=chosenCombo.getSelectedItem().toString();
                    ville=villeCombo.getSelectedItem().toString();
                    nEtude=nivEtudCombo.getSelectedItem().toString();
//              experience=experienceCombo.getSelectedItem().toString();
                    typeContrat = tContratCombo.getSelectedItem().toString();
//                titre= titre
//                ajouter le label ou feuille d'arbre
                    data.setClassIndex(data.attribute("experience").index());
//

                    vals[0] = data.attribute(0).indexOfValue(secteur); //secteur
                    vals[1] = data.attribute(1).indexOfValue(ville); //ville
//
                    vals[2] = data.attribute(2).indexOfValue(nEtude); // niveau etude
                    vals[3] = data.attribute(3).indexOfValue(""); // experience
                    vals[4] = data.attribute(4).indexOfValue(fonction); // fonction ***
                    vals[5] = data.attribute(5).indexOfValue(societe); // societe
                    vals[6] = data.attribute(6).indexOfValue(typeContrat); // typecontrat


                }else {
                    secteur=secteurCombo.getSelectedItem().toString();
                    fonction=fonctionCombo.getSelectedItem().toString();
                    experience=chosenCombo.getSelectedItem().toString();
                    ville=villeCombo.getSelectedItem().toString();
                    nEtude=nivEtudCombo.getSelectedItem().toString();
//              experience=experienceCombo.getSelectedItem().toString();
                    typeContrat = tContratCombo.getSelectedItem().toString();
//                titre= titre
//                ajouter le label ou feuille d'arbre
                    data.setClassIndex(data.attribute("societe").index());
//

                    vals[0] = data.attribute(0).indexOfValue(secteur); //secteur
                    vals[1] = data.attribute(1).indexOfValue(ville); //ville
//
                    vals[2] = data.attribute(2).indexOfValue(nEtude); // niveau etude
                    vals[3] = data.attribute(3).indexOfValue(experience); // experience
                    vals[4] = data.attribute(4).indexOfValue(fonction); // fonction ***
                    vals[5] = data.attribute(5).indexOfValue(""); // societe
                    vals[6] = data.attribute(6).indexOfValue(typeContrat); // typecontrat
                }

                try{

                    Instance inst = new DenseInstance(2.0,vals);
                    inst.setDataset(data);
                    J48 tree = ct.classify(data);
                    ct.evaluate(data);
                    double label = tree.classifyInstance(inst);
                    predLabel = data.classAttribute().value((int) label);
                    predictionLabel.setText(predLabel);
                }catch(Exception ex){
                    System.out.println("Error in GUI predireListener" + ex.getMessage());
                }

            }

        });


        retournerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rekrutGui rekrut = new rekrutGui();
                rekrut.setVisible(true);
                dispose();
            }
        });
    }

    public void setChosen(String chosen) {
        this.chosen = chosen;
    }
}
