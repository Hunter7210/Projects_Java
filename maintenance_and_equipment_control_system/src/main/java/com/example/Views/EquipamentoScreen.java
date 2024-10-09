package com.example.Views;

import javax.swing.*;

import org.bson.Document;

import com.example.Controllers.EquipamentoController;
import com.example.Controllers.GerarRelatorioPDF;
import com.example.Controllers.SensorController;
import com.example.Models.Dados;
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
    private JPanel equipamentosPanel; // Mover isso para o escopo da classe
    private JScrollPane scrollPane; // Também mover para o escopo da classe

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
        JButton btnDashboard = new JButton("Manuteções");
        JButton btnEquip = new JButton("Adicionar Equipamento");
        JButton btnAtualizar = new JButton("Atualizar"); // Botão para atualizar a página
        sidebarPanel.add(btnDashboard);
        sidebarPanel.add(btnEquip);
        sidebarPanel.add(btnAtualizar); // Adiciona o botão de atualizar
        add(sidebarPanel, BorderLayout.WEST);

        // Painel principal para exibição dos equipamentos
        equipamentosPanel = new JPanel();
        equipamentosPanel.setLayout(new BoxLayout(equipamentosPanel, BoxLayout.Y_AXIS));

        // Carregar os equipamentos inicialmente
        atualizarPagina();

        // Colocando a lista em um JScrollPane
        scrollPane = new JScrollPane(equipamentosPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Ações dos botões
        btnDashboard.addActionListener(e -> {
            ManutencaoScreen manutencaoScreen = new ManutencaoScreen();
            manutencaoScreen.setVisible(true);
        });

        btnEquip.addActionListener(e -> {
            new AddEquipamentoScreen().setVisible(true);
        });

        btnAtualizar.addActionListener(e -> {
            atualizarPagina(); // Chama o método de atualização quando o botão for pressionado
        });

        setVisible(true);
    }

    // Método para criar um card de exibição de equipamento
    private JPanel criarCardEquipamento(Equipamento equipamento) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(600, 150));

        JLabel nomeEquipLabel = new JLabel("Equipamento: " + equipamento.getNomeEqui());
        nomeEquipLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JTextArea detalhesArea = new JTextArea(
                "Código: " + equipamento.getCodEquip() +
                        "\nData de Compra: " + equipamento.getDataCompraEquip() +
                        "\nTipo: " + equipamento.getTipoEquip() +
                        "\nFornecedor: " + equipamento.getFornecEquip() +
                        "\nQuantidade de Sensores: " + equipamento.getQtdSensorEquip() +
                        "\nStatus: " + equipamento.getStatusEquip());
        detalhesArea.setEditable(false);
        detalhesArea.setBackground(Color.WHITE);

        JButton detalhesButton = new JButton("Ver Detalhes");
        detalhesButton.addActionListener(e -> mostrarDetalhesEquipamento(equipamento));

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

        System.out.println("Equipamento: " + equipamento.getCodEquip());
        System.out.println("Sensores: " + equipamento.getSensores());

        System.out.println(equipamento);

        for (Sensor sensor : equipamento.getSensores()) {
            // Exibe informações básicas do sensor
            JLabel sensorLabel = new JLabel("Sensor: " + sensor.getNomeSen() + " | Tipo: " + sensor.getFornecSen());
            detalhesPanel.add(sensorLabel);

            // Adiciona uma nova JLabel para exibir os dados do sensor
            if (sensor.getDados() != null && !sensor.getDados().isEmpty()) {
                for (Dados dado : sensor.getDados()) {
                    detalhesPanel
                            .add(new JLabel(
                                    "   Valor: " + dado.getDadosDad() + " | UnidMedida: " + dado.getUnidMedidDad()));
                }
            } else {
                detalhesPanel.add(new JLabel("   Nenhum dado disponível."));
            }
        }

        JButton btnRelatorio = new JButton("Gerar Relatório");
        detalhesPanel.add(btnRelatorio);
        // Adiciona um evento ao botão
        btnRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerarRelatorioPDF.gerarPDF(equipamento);
            }
        });

        // Adiciona o painel ao frame de detalhes
        detalhesFrame.add(new JScrollPane(detalhesPanel));
        detalhesFrame.setVisible(true);
    }

    // Método para atualizar a página de equipamentos
    private void atualizarPagina() {
        equipamentosPanel.removeAll(); // Remove todos os componentes antigos

        List<Equipamento> listaEquipamentos = carregarEquipamentos();
        for (Equipamento equipamento : listaEquipamentos) {
            JPanel cardEquipamento = criarCardEquipamento(equipamento);
            equipamentosPanel.add(cardEquipamento);
        }

        equipamentosPanel.revalidate(); // Atualiza o layout
        equipamentosPanel.repaint(); // Re-renderiza o painel
    }

    // Método para carregar equipamentos (mesmo de antes)
    private List<Equipamento> carregarEquipamentos() {
        EquipamentoController controlador = new EquipamentoController();
        List<Equipamento> equipamentos = new ArrayList<>();
        List<Document> documentos = controlador.readTodosEquipamentos();

        for (Document doc : documentos) {
            String nomeEqui = doc.getString("nomeEqui");
            String codEquip = doc.getString("codEquip");
            String dataCompraEquip = doc.getString("dataCompraEquip");
            String tipoEquip = doc.getString("tipoEquip");
            String fornecEquip = doc.getString("fornecEquip");
            int qtdSensorEquip = doc.getInteger("qtdSensorEquip");
            String statusEquip = doc.getString("statusEquip");

            SensorController sc = new SensorController();
            List<Sensor> sensores = sc.readSensoresPorEquipamento(codEquip);
            List<Manutencao> manutencoes = new ArrayList<>();
            List<QrCode> qrCodes = new ArrayList<>();

            Equipamento equipamento = new Equipamento(nomeEqui, codEquip, dataCompraEquip, tipoEquip, fornecEquip,
                    qtdSensorEquip, statusEquip, sensores, manutencoes, qrCodes);
            equipamentos.add(equipamento);
        }

        return equipamentos;
    }
}
