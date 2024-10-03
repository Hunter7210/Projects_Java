package com.example.Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.BorderLayout;

public class HomeScreen extends JFrame{
    
    public HomeScreen() {

        // Painel superior com texto e logo
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.YELLOW);
        topPanel.setLayout(new BorderLayout());
        
        JLabel textLabel = new JLabel("Gerenciamento de Manutenção e Funcionamento!");

        //Colocar uma imagem
        JLabel logoLabel = new JLabel("LOGO");
        
        topPanel.add(textLabel, BorderLayout.WEST);
        topPanel.add(logoLabel, BorderLayout.EAST);

        // Painel central para o espaço da imagem
        JPanel centerPanel = new JPanel();
        
        //Imagem principal 
        ImageIcon imageIcon = new ImageIcon("assets/img/bannerPrinci.jpg");
        JLabel imageLabel = new JLabel(imageIcon, SwingConstants.CENTER);
        
        //Centralizando Painel
        centerPanel.add(imageLabel, BorderLayout.CENTER);
       
        // Painel inferior com botão
        JPanel bottomPanel = new JPanel();
        
        JButton button = new JButton("Começe aqui!");
        
        bottomPanel.add(button);

        // Adiciona os painéis ao frame
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Configurações do frame
        setTitle("Sistema para gerenciamento de manutenções e maquinas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeScreen guiExample = new HomeScreen();
            guiExample.setVisible(true);
        });
    }
}

