package com.example.Views;

import javax.swing.*;

import com.example.Controllers.FuncionarioController;

import java.awt.*;

public class CadastroFuncionarioScreen extends JFrame {

    public CadastroFuncionarioScreen() {
        // Configurações da janela
        setTitle("Cadastro de Funcionário");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2)); // 8 linhas, 2 colunas

        // Campos de entrada
        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();

        JLabel reLabel = new JLabel("Registro:");
        JTextField reField = new JTextField();

        JLabel setorLabel = new JLabel("Setor:");
        JTextField setorField = new JTextField();

        JLabel cargoLabel = new JLabel("Cargo:");
        JTextField cargoField = new JTextField();

        JLabel telefoneLabel = new JLabel("Telefone:");
        JTextField telefoneField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaField = new JPasswordField();

        // Botão de cadastro
        JButton cadastrarButton = new JButton("Cadastrar");

        // Adiciona os componentes ao painel
        add(nomeLabel);
        add(nomeField);

        add(reLabel);
        add(reField);

        add(setorLabel);
        add(setorField);

        add(cargoLabel);
        add(cargoField);

        add(telefoneLabel);
        add(telefoneField);

        add(emailLabel);
        add(emailField);

        add(senhaLabel);
        add(senhaField);

        add(new JLabel()); // Célula vazia
        add(cadastrarButton); // Botão de cadastro

        // Ação do botão de cadastro
        // Ação do botão de cadastro ou login
        cadastrarButton.addActionListener(e -> {
            String nomeFielFormat = nomeField.getText();
            String reFieldFormat = reField.getText();
            String setorFieldFormat = setorField.getText();
            String cargoFieldFormat = cargoField.getText();
            String telefoneFieldFormat = telefoneField.getText();
            String emailFieldFormat = emailField.getText();

            // Para o campo de senha, use getPassword(), que retorna um char[]
            String senhaFieldFormat = new String(senhaField.getPassword());

            // Agora, você já tem a senha como String
            FuncionarioController fc = new FuncionarioController();
            fc.createFuncionario(nomeFielFormat, reFieldFormat, setorFieldFormat, cargoFieldFormat, telefoneFieldFormat,
                    emailFieldFormat, senhaFieldFormat);
        });

        // Exibe a janela
        setVisible(true);
    }

}
