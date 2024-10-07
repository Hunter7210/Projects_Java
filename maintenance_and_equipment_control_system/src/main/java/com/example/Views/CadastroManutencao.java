package com.example.Views;

import javax.swing.*;
import org.bson.Document;
import com.example.Controllers.EquipamentoController;
import com.example.Controllers.ManutencaoController;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CadastroManutencao {

    EquipamentoController equipamentoController = new EquipamentoController();

    private JPanel painelManut;
    private JComboBox<String> statusComboBoxtipoManut;
    private JComboBox<String> statusComboBoxManut;
    private JTextField dataIniManut;
    private JTextField dataFimManut;
    private JTextField dataPrevisFimManut;
    private JTextField dataPrevisIniManut;

    List<Document> empresasManut = new ArrayList<>();

    public List<Document> adicionarManutencao(String codEquip) {

        /* Configuração do painel principal */
        painelManut = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre componentes

        /* Tipo de Manutenção */
        String[] statusOptionsTipoManut = { "Preventiva", "Corretiva", "Preditiva" };
        statusComboBoxtipoManut = new JComboBox<>(statusOptionsTipoManut);
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelManut.add(new JLabel("Tipo de Manutenção:"), gbc);
        gbc.gridx = 1;
        painelManut.add(statusComboBoxtipoManut, gbc); // Adicionando o ComboBox de tipo

        /* Status da Manutenção */
        String[] statusOptionsManut = { "Em Progresso", "Encerrada", "Prevista" };
        statusComboBoxManut = new JComboBox<>(statusOptionsManut);
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelManut.add(new JLabel("Status da Manutenção:"), gbc);
        gbc.gridx = 1;
        painelManut.add(statusComboBoxManut, gbc);

        /* Filtrando os campos para inserção */
        statusComboBoxManut.addActionListener(e -> {
            int selectedIndex = statusComboBoxManut.getSelectedIndex();
            updateManutencaoFields(selectedIndex);
        });

        // Inicializa campos de data
        dataIniManut = new JTextField(15);
        dataFimManut = new JTextField(15);
        dataPrevisFimManut = new JTextField(15);
        dataPrevisIniManut = new JTextField(15);

        updateManutencaoFields(0); // Atualiza os campos inicialmente

        /* Botão para adicionar e redirecionar para empresaManut */
        JButton btnAdicionar = new JButton("Adicionar Empresa");
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui você pode adicionar a lógica para abrir a tela de empresaManut
                CadastroEquipManut em = new CadastroEquipManut();
                empresasManut.addAll(em.prepararInterface());
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 4; // Definindo a posição do botão
        gbc.gridwidth = 2; // O botão ocupa duas colunas
        painelManut.add(btnAdicionar, gbc);

        /* Botão para enviar os dados da manutenção */
        JButton btnEnviar = new JButton("Enviar Manutenção");

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManutencaoController manutencaoController = new ManutencaoController();

                String statusComboBoxtipoManutFormat = statusComboBoxtipoManut.getSelectedItem().toString();
                String statusComboBoxManutFormat = statusComboBoxManut.getSelectedItem().toString();
                String dataIniManutFormat = dataIniManut.getText();
                String dataFimManutFormat = dataFimManut.getText();
                String dataPrevisIniManutFormat = dataPrevisIniManut.getText();
                String dataPrevisFimManutFormt = dataPrevisFimManut.getText();

                // Adiciona a nova manutenção ao equipamento
                manutencaoController.updateManutencao(codEquip, dataIniManutFormat, dataFimManutFormat,
                        statusComboBoxtipoManutFormat, statusComboBoxManutFormat, dataPrevisFimManutFormt,
                        dataPrevisIniManutFormat, empresasManut);

                JOptionPane.showMessageDialog(null, "Manutenção adicionada com sucesso!");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 5; // Posição abaixo do botão Adicionar Empresa
        gbc.gridwidth = 2;
        painelManut.add(btnEnviar, gbc);

        /* Adiciona o painel ao JFrame */
        JFrame frame = new JFrame("Adicionar Manutenção");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(painelManut);
        frame.pack();
        frame.setVisible(true);

        return empresasManut;
    }

    private void updateManutencaoFields(int statusIndex) {
        // Remove campos de data anteriores
        painelManut.remove(dataIniManut);
        painelManut.remove(dataFimManut);
        painelManut.remove(dataPrevisFimManut);
        painelManut.remove(dataPrevisIniManut);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes

        gbc.gridx = 0;
        gbc.gridy = 2; // Para o primeiro campo de data

        switch (statusIndex) {
            case 0: // Em Progresso
                painelManut.add(new JLabel("Data de Início:"), gbc);
                gbc.gridx = 1;
                painelManut.add(dataIniManut, gbc);

                gbc.gridx = 0;
                gbc.gridy = 3; // Próximo campo
                painelManut.add(new JLabel("Data Prevista para Término:"), gbc);
                gbc.gridx = 1;
                painelManut.add(dataPrevisFimManut, gbc);
                break;

            case 1: // Encerrada
                painelManut.add(new JLabel("Data de Início:"), gbc);
                gbc.gridx = 1;
                painelManut.add(dataIniManut, gbc);

                gbc.gridx = 0;
                gbc.gridy = 3; // Próximo campo
                painelManut.add(new JLabel("Data de Fim da Manutenção:"), gbc);
                gbc.gridx = 1;
                painelManut.add(dataFimManut, gbc);
                break;

            case 2: // Prevista
                gbc.gridx = 0;
                gbc.gridy = 2;
                painelManut.add(new JLabel("Data Prevista para Início:"), gbc);
                gbc.gridx = 1;
                painelManut.add(dataPrevisIniManut, gbc);
                break;
        }

        painelManut.revalidate(); // Atualiza o layout
        painelManut.repaint(); // Redesenha o painel
    }
}
