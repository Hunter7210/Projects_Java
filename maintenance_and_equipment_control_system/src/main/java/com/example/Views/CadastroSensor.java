package com.example.Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.bson.Document;
import com.example.Controllers.EquipamentoController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class CadastroSensor {

    private JPanel painelSensor;
    private JTextField nomeSen;
    private JTextField fornecSen;
    private JTextArea funSen;

    private JButton btnTempo;

    private List<Document> acionamento = new ArrayList<>();
    private List<Document> sensores = new ArrayList<>();

    EquipamentoController eq = new EquipamentoController();

    public void cadastrarSensor() {
        // Criando painel principal com borda e layout mais espaçado
        painelSensor = new JPanel();
        painelSensor.setLayout(new GridBagLayout()); // Usando GridBagLayout para melhor controle de layout
        painelSensor.setBorder(new EmptyBorder(10, 10, 10, 10)); // Borda para adicionar espaçamento
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margens entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Adicionando campos ao painel com layout aprimorado
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelSensor.add(new JLabel("Nome do Sensor:"), gbc);

        nomeSen = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        painelSensor.add(nomeSen, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelSensor.add(new JLabel("Fornecedor do Sensor:"), gbc);

        fornecSen = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        painelSensor.add(fornecSen, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        painelSensor.add(new JLabel("Função do Sensor:"), gbc);

        funSen = new JTextArea(4, 20); // Aumenta o tamanho da área de texto para melhor visibilidade
        funSen.setLineWrap(true);
        funSen.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(funSen); // Adiciona rolagem para a área de texto
        gbc.gridx = 1;
        gbc.gridy = 2;
        painelSensor.add(scrollPane, gbc);
   
        /* Adicionando btnTempo */
        btnTempo = new JButton("Adicionar Dados");
        painelSensor.add(btnTempo);


        // Definindo fontes e ajustando a aparência
        Font fonte = new Font("Arial", Font.PLAIN, 14);
        nomeSen.setFont(fonte);
        fornecSen.setFont(fonte);
        funSen.setFont(fonte);
        
                btnTempo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CadastroDados pgCadDad = new CadastroDados();
                        pgCadDad.cadastrarDados();
                    }
                });
        
        // Exibindo um diálogo para entrada de dados com painel mais espaçoso
        int result = JOptionPane.showConfirmDialog(null, painelSensor, "Cadastrar Sensor", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            // Capturando os dados digitados pelo usuário
            String nomeSenText = nomeSen.getText();
            String fornecSenText = fornecSen.getText();
            String funSenText = funSen.getText();
            
            // Criando um documento para o sensor
            Document sensor = eq.createSensor(nomeSenText, fornecSenText, funSenText, acionamento);
            sensores.add(sensor);

            // Mensagem de confirmação
            JOptionPane.showMessageDialog(null, "Sensor adicionado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        }


    }
}
