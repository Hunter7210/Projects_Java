package com.example.Views;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CadastroFunc extends JFrame{

    private JPanel mainPanel; // Painel principal que contém as "telas"

    HomeScreen hm = new HomeScreen();

    
    // Método para trocar de painel
    private void switchToNextPanel() {
        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        cl.show(mainPanel, "Next"); // Muda para o painel da próxima tela
    }
}
