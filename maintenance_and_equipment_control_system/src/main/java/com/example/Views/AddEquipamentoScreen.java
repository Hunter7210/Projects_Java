package com.example.Views;

import com.example.Controllers.EquipamentoController;

import javax.swing.*;

import org.bson.Document;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddEquipamentoScreen extends JFrame {

    // Componentes de entrada para os dados do equipamento
    private JTextField nomeEquiField;
    private JTextField codEquipField;
    private JTextField dataCompraEquipField;
    private JTextField tipoEquipField;
    private JTextField fornecEquipField;
    private JTextField qtdSensorEquipField;
    private JComboBox<String> statusEquipComboBox;

    List<Document> sensor = new ArrayList<>();

    public AddEquipamentoScreen() {
        // Configurações básicas da janela
        setTitle("Adicionar Novo Equipamento");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));

        // Labels e campos de texto para cada atributo do equipamento
        panel.add(new JLabel("Nome do Equipamento:"));
        nomeEquiField = new JTextField();
        panel.add(nomeEquiField);

        panel.add(new JLabel("Código do Equipamento:"));
        codEquipField = new JTextField();
        panel.add(codEquipField);

        panel.add(new JLabel("Data de Compra (dd/mm/yyyy):"));
        dataCompraEquipField = new JTextField();
        panel.add(dataCompraEquipField);

        panel.add(new JLabel("Tipo do Equipamento:"));
        tipoEquipField = new JTextField();
        panel.add(tipoEquipField);

        panel.add(new JLabel("Fornecedor:"));
        fornecEquipField = new JTextField();
        panel.add(fornecEquipField);

        panel.add(new JLabel("Quantidade de Sensores:"));
        qtdSensorEquipField = new JTextField();
        panel.add(qtdSensorEquipField);

        panel.add(new JLabel("Status:"));
        String[] statusOptions = { "Ativo", "Inativo" };
        statusEquipComboBox = new JComboBox<>(statusOptions);
        panel.add(statusEquipComboBox);

        // Botão para add sensor
        JButton addButtonSensor = new JButton("Adicionar Sensor");
        addButtonSensor.addActionListener(new AddSensor());

        // Botão de adicionar
        JButton addButton = new JButton("Adicionar Equipamento");
        addButton.addActionListener(new AddEquipamentoListener());

        panel.add(addButton);
        panel.add(addButtonSensor);

        // Adiciona o painel à janela
        add(panel);
        setVisible(true);
    }

    // Listener para lidar com o clique no botão "Adicionar Equipamento"
    private class AddEquipamentoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Pega os valores dos campos de texto
            String nomeEqui = nomeEquiField.getText();
            String codEquip = codEquipField.getText();
            String dataCompraEquip = dataCompraEquipField.getText();
            String tipoEquip = tipoEquipField.getText();
            String fornecEquip = fornecEquipField.getText();
            int qtdSensorEquip;
            try {
                qtdSensorEquip = Integer.parseInt(qtdSensorEquipField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Quantidade de sensores deve ser um número inteiro.");
                return;
            }
            String statusEquip = (String) statusEquipComboBox.getSelectedItem();

            // Validações básicas
            if (nomeEqui.isEmpty() || codEquip.isEmpty() || dataCompraEquip.isEmpty() || tipoEquip.isEmpty()
                    || fornecEquip.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.");
                return;
            }

            List<Document> manut = new ArrayList<>();
            List<Document> qr = new ArrayList<>();
            EquipamentoController eq = new EquipamentoController();
            eq.createEquipamento(nomeEqui, codEquip, dataCompraEquip, tipoEquip, fornecEquip, qtdSensorEquip,
                    statusEquip, sensor, manut, qr);
            // Limpar os campos após adicionar
            limparCampos();
        }
    }

    // Listener para lidar com o clique no botão "Adicionar Equipamento"
    private class AddSensor implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CadastroSensor cs = new CadastroSensor();
            sensor.addAll(cs.cadastrarSensor());
        }
    }

    // Método para limpar os campos de entrada
    private void limparCampos() {
        nomeEquiField.setText("");
        codEquipField.setText("");
        dataCompraEquipField.setText("");
        tipoEquipField.setText("");
        fornecEquipField.setText("");
        qtdSensorEquipField.setText("");
        statusEquipComboBox.setSelectedIndex(0);
    }
}
