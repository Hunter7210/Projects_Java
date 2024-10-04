package com.example.Views;

import javax.swing.*;
import java.awt.*;

public class ControlePainel extends JPanel {
    private CardLayout cardLayout;

    public ControlePainel() {
        // Inicializa o CardLayout
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        
        // Criação dos painéis
        HomeScreen homeScreen = new HomeScreen();
        JPanel painel2 = new JPanel(); // Exemplo de segundo painel
        JPanel painel3 = new JPanel(); // Exemplo de terceiro painel

        // Configurações dos Painéis
        painel2.add(new JLabel("Este é o Painel 2"));
        painel3.add(new JLabel("Este é o Painel 3"));

        // Adiciona os painéis ao CardLayout
        add(homeScreen, "Painel 1");
        add(painel2, "Painel 2");
        add(painel3, "Painel 3");

        // Painel de navegação
        JPanel navPanel = new JPanel();
        JButton btn1 = new JButton("Mostrar Painel 1");
        JButton btn2 = new JButton("Mostrar Painel 2");
        JButton btn3 = new JButton("Mostrar Painel 3");

        // Adiciona os ouvintes de ação para os botões
        btn1.addActionListener(e -> cardLayout.show(this, "Painel 1"));
        btn2.addActionListener(e -> cardLayout.show(this, "Painel 2"));
        btn3.addActionListener(e -> cardLayout.show(this, "Painel 3"));

        navPanel.add(btn1);
        navPanel.add(btn2);
        navPanel.add(btn3);

        // Cria um painel principal que irá conter o painel de navegação e o CardLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(navPanel, BorderLayout.NORTH);
        mainPanel.add(this, BorderLayout.CENTER);

       
    }

}
