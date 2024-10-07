package com.example.Views;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.bson.Document;

import com.example.Controllers.EquipamentoController;


public class CadastroDados {

    EquipamentoController eq = new EquipamentoController();

    private JPanel painelDados;
    private JTextField dadosDad;
    private JTextField unidMedDad;

    List<Document> dadosEquip = new ArrayList<>();

    public List<Document> cadastrarDados() {
        // Inicializando o painel com layout e borda
        painelDados = new JPanel(new GridBagLayout());
        painelDados.setBorder(new EmptyBorder(15, 15, 15, 15)); // Borda para espaçamento ao redor do painel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // Espaçamento entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Adicionando os componentes com layout organizado
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelDados.add(new JLabel("Dados:"), gbc);

        dadosDad = new JTextField(15); // Campo de texto com tamanho ajustado
        gbc.gridx = 1;
        gbc.gridy = 0;
        painelDados.add(dadosDad, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelDados.add(new JLabel("Unidade de Medida:"), gbc);

        unidMedDad = new JTextField(15); // Campo de texto para unidade de medida
        gbc.gridx = 1;
        gbc.gridy = 1;
        painelDados.add(unidMedDad, gbc);

        // Definindo uma fonte padrão para os campos de texto e labels
        Font fonte = new Font("Arial", Font.PLAIN, 14);
        dadosDad.setFont(fonte);
        unidMedDad.setFont(fonte);

        // Exibindo o diálogo com o painel bem formatado
        int result = JOptionPane.showConfirmDialog(null, painelDados, "Adicionar Dados", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                // Formatando os dados inseridos
                double dadosFormat = Double.parseDouble(dadosDad.getText());
                String unidMedFormat = unidMedDad.getText();

                // Adicionando os dados formatados à lista
                dadosEquip.add(eq.createDados(dadosFormat, unidMedFormat));
                JOptionPane.showMessageDialog(null, "Dados adicionados com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                // Mensagem de confirmação
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido para os dados.",

                        "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            }
        }
        return dadosEquip;
    }
}
