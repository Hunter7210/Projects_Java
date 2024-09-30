package com.example;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.GridLayout;

public class ScreenCalculator extends JFrame {

    public ScreenCalculator() {

        super("Calculadora avançada"); // Criando o painel

        JTabbedPane jtb = new JTabbedPane();
        jtb.addTab("Calculadora Normal", new BasicCalculator());
        jtb.addTab("Calculadora Cientifica", new JPanel(new GridLayout(4, 5)));
        this.setVisible(true);
    }
}
