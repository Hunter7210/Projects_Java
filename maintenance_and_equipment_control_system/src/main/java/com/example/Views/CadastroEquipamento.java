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

    private JTextField nomeField;
    private JTextField codField;
    private JTextField dataCompraField;
    private JTextField tipoField;
    private JTextField fornecedorField;
    private JTextField qtdSensorField;
    private JComboBox<String> statusComboBox;

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

        panel.add(new JLabel("Sigla do Equipamento (Três Letras):"));
        codField = new JTextField();
        panel.add(codField);

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

                CadastroManutencao cm = new CadastroManutencao();
                String codE = codField.getText();
                
                cm.adicionarManutencao(codE);
            }
        });

        /*
         * adicionarQrCodeButton.addActionListener(new ActionListener() {
         * 
         * @Override
         * public void actionPerformed(ActionEvent e) {
         * adicionarQrCode();
         * }
         * });
         */

        add(panel);
        setVisible(true);
    }

    /*
     * private void adicionarQrCode() {
     * String imgQr = JOptionPane.showInputDialog(this, "Imagem do QR Code:");
     * String titQr = JOptionPane.showInputDialog(this, "Título do QR Code:");
     * String descQr = JOptionPane.showInputDialog(this, "Descrição do QR Code:");
     * 
     * Document qrCode = controller.createQRCode(imgQr, titQr, descQr);
     * qrcodes.add(qrCode);
     * JOptionPane.showMessageDialog(this, "QR Code adicionado!");
     * }
     */

    private void salvarEquipamento() {
        String nome = nomeField.getText();
        String cod = codField.getText();
        String dataCompra = dataCompraField.getText();
        String tipo = tipoField.getText();
        String fornecedor = fornecedorField.getText();
        int qtdSensores = Integer.parseInt(qtdSensorField.getText());
        String status = statusComboBox.getSelectedItem().toString();

        controller.createEquipamento(nome, cod, dataCompra, tipo, fornecedor, qtdSensores, status, sensores,
                manutencoes,
                qrcodes);
        JOptionPane.showMessageDialog(this, "Equipamento cadastrado com sucesso!");
    }
}
