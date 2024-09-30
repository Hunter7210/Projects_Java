package com.example;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridLayout;

public class BasicCalculator extends JPanel {

    public BasicCalculator() {
        this.setLayout(new GridLayout(4, 5));
        this.criandoBtns();
    }

    public void criandoBtns() {
        // Criando um vetor com os valores de "0" a "9"
        String[] valoresArray = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

        // Criando uma lista para armazenar os valores
        List<String> valoresList = new ArrayList<>();

        // Usando um loop for para adicionar os valores do vetor à lista
        for (int i = 0; i < valoresArray.length; i++) {
            valoresList.add(valoresArray[i]);
        }

        // Aqui você pode implementar a lógica para criar os botões, se necessário
        for (String valor : valoresList) {
            // Exemplo: criarBotao(valor);

            this.add(new JButton(valor));
        }
        
    }

}
