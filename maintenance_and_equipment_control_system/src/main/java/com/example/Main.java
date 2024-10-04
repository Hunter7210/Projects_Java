package com.example;

import com.example.Views.ControlePainel;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // Cria o JFrame
        JFrame frame = new JFrame("Exemplo de CardLayout");
        ControlePainel mainPanel = new ControlePainel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
