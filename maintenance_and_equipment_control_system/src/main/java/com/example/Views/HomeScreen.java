package com.example.Views;

import javax.swing.*;
import java.awt.*;

public class HomeScreen extends JPanel {
    public HomeScreen() {
        setLayout(new BorderLayout());

        // Adiciona um título
        JLabel titleLabel = new JLabel("Bem-vindo à HomeScreen", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Adiciona um botão de exemplo
        JButton exampleButton = new JButton("Clique aqui");/* 
        exampleButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Você clicou no botão!")); */
        add(exampleButton, BorderLayout.CENTER);

        // Adiciona uma área de texto de exemplo
        JTextArea infoArea = new JTextArea("Informações adicionais podem ser exibidas aqui.");
        infoArea.setEditable(false);
        add(infoArea, BorderLayout.SOUTH);
    }
}
