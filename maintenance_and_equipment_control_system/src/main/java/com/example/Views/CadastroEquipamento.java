package com.example.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

import com.example.Controllers.EquipamentoController;

public class CadastroEquipamento extends JFrame {
    private JPanel panielManut;
    private JPanel panielSensor;
    
    
    private JTextField nomeField;
    private JTextField dataCompraField;
    private JTextField tipoField;
    private JTextField fornecedorField;
    private JTextField qtdSensorField;
    private JComboBox<String> statusComboBox;
    private JComboBox<String> statusComboBoxManut;

    private List<Document> sensores = new ArrayList<>();
    private List<Document> manutencoes = new ArrayList<>();
    private List<Document> qrcodes = new ArrayList<>();

    private JButton salvarButton;
    private JButton cancelarButton;
    private JButton adicionarSensorButton;
    private JButton adicionarManutencaoButton;
    private JButton adicionarQrCodeButton;

    private EquipamentoController controller;

    public CadastroEquipamento() {
        controller = new EquipamentoController(); // Instanciando o controlador

        /* Setando configurações */
        setTitle("Cadastro de Equipamento");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        /* Criação do painel principal */
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 2, 10, 10));

        /* Adicionando os iens ao painel principal */

        /* Adicionando os TextFields */
        panel.add(new JLabel("Nome do Equipamento:"));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Data de Compra:"));
        dataCompraField = new JTextField();
        panel.add(dataCompraField);

        panel.add(new JLabel("Tipo de Equipamento:"));
        tipoField = new JTextField();
        panel.add(tipoField);

        panel.add(new JLabel("Fornecedor:"));
        fornecedorField = new JTextField();
        panel.add(fornecedorField);

        panel.add(new JLabel("Quantidade de Sensores:"));
        qtdSensorField = new JTextField();
        panel.add(qtdSensorField);

        /* Add comboBox */
        panel.add(new JLabel("Status do Equipamento:"));
        String[] statusOptions = { "Ativo", "Inativo", "Em Manutenção" };
        statusComboBox = new JComboBox<>(statusOptions);
        panel.add(statusComboBox);

        /* Add Buttons */
        adicionarSensorButton = new JButton("Adicionar Sensor");
        adicionarManutencaoButton = new JButton("Adicionar Manutenção");
        adicionarQrCodeButton = new JButton("Adicionar QR Code");

        panel.add(adicionarSensorButton);
        panel.add(adicionarManutencaoButton);
        panel.add(adicionarQrCodeButton);

        salvarButton = new JButton("Salvar");
        cancelarButton = new JButton("Cancelar");

        panel.add(salvarButton);
        panel.add(cancelarButton);

        /* Criação dos eventos para os brns */
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarEquipamento();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        adicionarSensorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastroSensor cs = new CadastroSensor();
                cs.cadastrarSensor(); 
                
            }
        });

        adicionarManutencaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarManutencao();
            }
        });

        adicionarQrCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarQrCode();
            }
        });

        add(panel);
        setVisible(true);
    }

  

    private void adicionarManutencao() {
        String tipoManut = JOptionPane.showInputDialog(this, "Tipo de Manutenção:");
        String[] statusOptionsManut = {"Ativo", "Inativo", "Em Manutenção"};
        statusComboBoxManut = new JComboBox<>(statusOptionsManut);

        String dataIniManut = JOptionPane.showInputDialog(this, "Data Início da Manutenção:");
        String dataFimManut = JOptionPane.showInputDialog(this, "Data Fim da Manutenção:");

        List<Document> empresasManut = new ArrayList<>();

        int qtdEmpresas = Integer.parseInt(JOptionPane.showInputDialog(this, "Quantas empresas responsaveis pela manutenção?"));
        for (int i = 0; i < qtdEmpresas; i++) {
            String nomeEmpresa = JOptionPane.showInputDialog(this, "Nome da empresa " + (i + 1) + ":");
            empresasManut.add(new Document("nomeEmpresa", nomeEmpresa));
        }

        Document manutencao = controller.createManutencao(dataIniManut, dataFimManut, tipoManut, statusOptionsManut.toString(),
                empresasManut);
        manutencoes.add(manutencao);
        JOptionPane.showMessageDialog(this, "Manutenção adicionada!");
    }

    private void adicionarQrCode() {
        String imgQr = JOptionPane.showInputDialog(this, "Imagem do QR Code:");
        String titQr = JOptionPane.showInputDialog(this, "Título do QR Code:");
        String descQr = JOptionPane.showInputDialog(this, "Descrição do QR Code:");

        Document qrCode = controller.createQRCode(imgQr, titQr, descQr);
        qrcodes.add(qrCode);
        JOptionPane.showMessageDialog(this, "QR Code adicionado!");
    }

    private void salvarEquipamento() {
        String nome = nomeField.getText();
        String dataCompra = dataCompraField.getText();
        String tipo = tipoField.getText();
        String fornecedor = fornecedorField.getText();
        int qtdSensores = Integer.parseInt(qtdSensorField.getText());
        String status = (String) statusComboBox.getSelectedItem();

        controller.createEquipamento(nome, dataCompra, tipo, fornecedor, qtdSensores, status, sensores, manutencoes,
                qrcodes);
        JOptionPane.showMessageDialog(this, "Equipamento cadastrado com sucesso!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CadastroEquipamento::new);
    }
}
