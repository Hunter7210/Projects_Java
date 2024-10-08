package com.example.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeScreen extends JFrame {

    public HomeScreen() {
        // Configurações da janela
        setTitle("Sistema de Manutenção");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel superior (navbar)
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Texto da navbar
        JLabel titleLabel = new JLabel("Sistema de Manutenção");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);

        // Logo da navbar
        ImageIcon logoIcon = new ImageIcon("./assets/img/IMGSistema.png"); // Substitua pelo caminho da sua logo
        JLabel logoLabel = new JLabel(logoIcon);
        topPanel.add(logoLabel);

        // Adiciona o painel superior à janela
        add(topPanel, BorderLayout.NORTH);

        // Painel central para a imagem
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        // Imagem central
        ImageIcon imageIcon = new ImageIcon("C:/Users/DevTarde/Documents/Heitor/4_Termo/Java/Projects_Java/maintenance_and_equipment_control_system/src/main/java/com/example/assets/img/IMGSistema.png"); // Substitua pelo caminho da sua imagem
        
        // Redimensiona a imagem
        Image image = imageIcon.getImage(); // Converte ImageIcon em Image
        Image scaledImage = image.getScaledInstance(800, 600, Image.SCALE_SMOOTH); // Ajuste a largura e altura conforme necessário
        ImageIcon scaledIcon = new ImageIcon(scaledImage); // Converte de volta para ImageIcon

        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(imageLabel, BorderLayout.CENTER);

        // Botão abaixo da imagem
        JButton actionButton = new JButton("Iniciar Manutenção");
        actionButton.setPreferredSize(new Dimension(200, 50));
        actionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(actionButton, BorderLayout.SOUTH);

        // Adiciona um evento ao botão
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de login
                new LoginScreen();
                dispose(); // Fecha a tela atual (opcional)
            }
        });

        // Adiciona o painel central à janela
        add(centerPanel, BorderLayout.CENTER);

        // Exibe a janela
        setVisible(true);
    }

}
