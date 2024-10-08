package com.example.Views;

import javax.swing.*;
import com.example.Controllers.ManutencaoController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EquipamentoDetailScreen extends JFrame {

    ManutencaoController mc = new ManutencaoController();
    CadastroManutencao cm = new CadastroManutencao();
    private String codEquip; // Para armazenar o código do equipamento

    public EquipamentoDetailScreen(String codEquipamento) {
        this.codEquip = codEquipamento; // Armazena o código do equipamento
        setTitle("Detalhes do Equipamento - " + codEquipamento);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para fechar apenas esta tela
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1)); // 4 linhas para os botões

        // Botões
        JButton btnAddManutencao = new JButton("Adicionar Manutenção");
        JButton btnUpdateManutencao = new JButton("Atualizar Manutenção");
        JButton btnDeleteManutencao = new JButton("Excluir Manutenção");
        JButton btnVoltar = new JButton("Voltar");

        // Adiciona os botões ao painel
        mainPanel.add(btnAddManutencao);
        mainPanel.add(btnUpdateManutencao);
        mainPanel.add(btnDeleteManutencao);
        mainPanel.add(btnVoltar);

        // Adiciona ação ao botão "Adicionar Manutenção"
        btnAddManutencao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.adicionarManutencao(codEquipamento); // Crie a tela para adicionar manutenção
                dispose(); // Fecha a tela atual
            }
        });

        // Adiciona ação ao botão "Atualizar Manutenção"
        btnUpdateManutencao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Abre a tela com a lista de manutenções
                new ListaManutencaoScreen(codEquip); // Abre a tela de listagem de manutenções
            }
            /*
             * String manutencaoId =
             * JOptionPane.showInputDialog("Digite o ID da manutenção a ser atualizada:");
             * String novoStatus =
             * JOptionPane.showInputDialog("Digite o novo status da manutenção:");
             * 
             * if (manutencaoId != null && novoStatus != null) {
             * mc.updateStatusManutencao(codEquip, manutencaoId, novoStatus); // Atualiza o
             * status da manutenção
             * JOptionPane.showMessageDialog(null,
             * "Status da manutenção atualizado com sucesso.");
             * }
             * }
             */ });

        // Adiciona ação ao botão "Excluir Manutenção"
        btnDeleteManutencao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String manutencaoId = JOptionPane.showInputDialog("Digite o ID da manutenção a ser excluída:");

                if (manutencaoId != null) {
                    mc.deleteManutencao(codEquip, manutencaoId); // Exclui a manutenção
                    JOptionPane.showMessageDialog(null, "Manutenção excluída com sucesso.");
                }
            }
        });

        // Ação para voltar à tela anterior
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela atual
            }
        });

        // Adiciona o painel principal à JFrame
        add(mainPanel);
        setVisible(true);
    }
}
