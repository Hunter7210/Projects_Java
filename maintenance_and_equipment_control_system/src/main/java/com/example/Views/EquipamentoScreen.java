package com.example.Views;

import javax.swing.*;

import com.example.Models.Dados;
import com.example.Models.EmpresaManu;
import com.example.Models.Equipamento;
import com.example.Models.Manutencao;
import com.example.Models.QrCode;
import com.example.Models.Sensor;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoScreen extends JFrame {

    public EquipamentoScreen() {
        // Configurações básicas do JFrame
        setTitle("Lista de Equipamentos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Barra superior (navbar)
        JPanel navbarPanel = new JPanel(new BorderLayout());
        navbarPanel.setBackground(Color.LIGHT_GRAY);
        JLabel titleLabel = new JLabel("Sistema de Manutenção - Equipamentos");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel logoLabel = new JLabel(new ImageIcon("logo.png")); // Substituir com a logo
        navbarPanel.add(logoLabel, BorderLayout.WEST);
        navbarPanel.add(titleLabel, BorderLayout.CENTER);
        add(navbarPanel, BorderLayout.NORTH);

        // Sidebar com botões
        JPanel sidebarPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        sidebarPanel.setPreferredSize(new Dimension(150, 0));
        JButton btnDashboard = new JButton("Add Equipamento");
        JButton btnEquipamentos = new JButton("Equipamentos");
        JButton btnManutencao = new JButton("Manutenções");
        sidebarPanel.add(btnDashboard);
        sidebarPanel.add(btnEquipamentos);
        sidebarPanel.add(btnManutencao);
        add(sidebarPanel, BorderLayout.WEST);

        // Painel principal para exibição dos equipamentos
        JPanel equipamentosPanel = new JPanel();
        equipamentosPanel.setLayout(new BoxLayout(equipamentosPanel, BoxLayout.Y_AXIS));

        // Carregando equipamentos e criando os cards
        List<Equipamento> listaEquipamentos = carregarEquipamentos();
        for (Equipamento equipamento : listaEquipamentos) {
            JPanel cardEquipamento = criarCardEquipamento(equipamento);
            equipamentosPanel.add(cardEquipamento);
        }

        // Colocando a lista em um JScrollPane
        JScrollPane scrollPane = new JScrollPane(equipamentosPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Adiciona um evento ao botão
        btnDashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de login
                new AddEquipamentoScreen();
                dispose(); // Fecha a tela atual (opcional)
            }
        });

        setVisible(true);
    }

    // Método para criar um card de exibição de equipamento
    private JPanel criarCardEquipamento(Equipamento equipamento) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(600, 150));

        // Cabeçalho do equipamento
        JLabel nomeEquipLabel = new JLabel("Equipamento: " + equipamento.getNomeEqui());
        nomeEquipLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Informações principais
        JTextArea detalhesArea = new JTextArea(
                "Código: " + equipamento.getCodEquip() +
                        "\nData de Compra: " + equipamento.getDataCompraEquip() +
                        "\nTipo: " + equipamento.getTipoEquip() +
                        "\nFornecedor: " + equipamento.getFornecEquip() +
                        "\nQuantidade de Sensores: " + equipamento.getQtdSensorEquip() +
                        "\nStatus: " + equipamento.getStatusEquip());
        detalhesArea.setEditable(false);
        detalhesArea.setBackground(Color.WHITE);

        // Botão de detalhes para expandir informações
        JButton detalhesButton = new JButton("Ver Detalhes");
        detalhesButton.addActionListener(e -> mostrarDetalhesEquipamento(equipamento));

        // Adiciona os componentes ao card
        card.add(nomeEquipLabel, BorderLayout.NORTH);
        card.add(detalhesArea, BorderLayout.CENTER);
        card.add(detalhesButton, BorderLayout.SOUTH);

        return card;
    }

    // Método para exibir detalhes adicionais do equipamento em um novo JFrame
    private void mostrarDetalhesEquipamento(Equipamento equipamento) {
        JFrame detalhesFrame = new JFrame("Detalhes do Equipamento");
        detalhesFrame.setSize(400, 300);
        detalhesFrame.setLocationRelativeTo(null);

        JPanel detalhesPanel = new JPanel(new GridLayout(0, 1));

        // Exibe os sensores
        JLabel sensoresLabel = new JLabel("Sensores:");
        detalhesPanel.add(sensoresLabel);
        for (Sensor sensor : equipamento.getSensores()) {
            detalhesPanel.add(new JLabel("Sensor: " + sensor.getNomeSen() + " | Tipo: " + sensor.getFunSen()));
        }

        // Exibe as manutenções
        JLabel manutencoesLabel = new JLabel("Manutenções:");
        detalhesPanel.add(manutencoesLabel);
        for (Manutencao manutencao : equipamento.getManutencoes()) {
            detalhesPanel
                    .add(new JLabel(
                            "Manutenção: " + manutencao.getTipoManut() + " | Data: " + manutencao.getStatusManut()));
        }

        // Exibe os QR Codes
        JLabel qrCodesLabel = new JLabel("QR Codes:");
        detalhesPanel.add(qrCodesLabel);
        for (QrCode qrCode : equipamento.getQrcodes()) {
            detalhesPanel
                    .add(new JLabel("QR Code: " + qrCode.getDescQr() + " | Gerado em: " + qrCode.getTitQr()));
        }

        // Adiciona o painel ao frame de detalhes
        detalhesFrame.add(new JScrollPane(detalhesPanel));
        detalhesFrame.setVisible(true);
    }

    // Simula a carga de equipamentos (adapte conforme sua lógica de persistência)
    private List<Equipamento> carregarEquipamentos() {
        List<Equipamento> equipamentos = new ArrayList<>();
        List<Dados> dados1 = new ArrayList<>();
        List<EmpresaManu> empre = new ArrayList<>();
        List<Sensor> sensor = new ArrayList<>();
        List<Manutencao> manuten = new ArrayList<>();
        List<QrCode> qr = new ArrayList<>();
        // Simulação de dados
        sensor.add(new Sensor("Sensor A", "Temperatura", "Funcionar", dados1));
        sensor.add(new Sensor("Sensor A", "Temperatura", "Funcionar", dados1));
        sensor.add(new Sensor("Sensor A", "Temperatura", "Funcionar", dados1));
        manuten.add(new Manutencao(empre, "01/01/2024", "234", "234", "123", "123", "123"));
        manuten.add(new Manutencao(empre, "01/01/2024", "234", "234", "123", "123", "123"));
        qr.add(new QrCode("QR123", "15/08/2023", "re"));

        equipamentos.add(new Equipamento("Máquina 1", "EQ001", "01/01/2022", "Tipo A", "Fornecedor X", 5, "Ativo",
                sensor, manuten, qr));
        equipamentos.add(new Equipamento("Máquina 2", "EQ002", "05/05/2023", "Tipo B", "Fornecedor Y", 3, "Inativo",
                sensor, manuten, qr));

        return equipamentos;
    }
}
