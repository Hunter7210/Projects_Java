package com.example.Views;

import javax.swing.*;
import com.example.Controllers.ManutencaoController;
import java.awt.*;
import java.util.List;

public class ListaManutencaoScreen extends JFrame {

    private ManutencaoController mc = new ManutencaoController();
    private String codEquip;

    public ListaManutencaoScreen(String codEquipamento) {
        this.codEquip = codEquipamento;
        setTitle("Lista de Manutenções - " + codEquipamento);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha esta tela
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Obtém a lista de manutenções
        List<String> manutencoes = mc.getManutencoes(codEquipamento); // Método que retorna a lista de manutenções

        // Cria um painel para exibir a lista
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String manutencao : manutencoes) {
            listModel.addElement(manutencao); // Adiciona cada manutenção ao modelo da lista
        }

        JList<String> manutencaoList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(manutencaoList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Botão para fechar a tela
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose()); // Fecha a tela ao clicar
        mainPanel.add(btnFechar, BorderLayout.SOUTH);

        // Adiciona o painel principal à JFrame
        add(mainPanel);
        setVisible(true);
    }
}
