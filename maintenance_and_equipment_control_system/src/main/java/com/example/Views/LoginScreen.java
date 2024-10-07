package com.example.Views;

import javax.swing.*;

import com.example.Controllers.FuncionarioController;

import java.awt.*;

public class LoginScreen extends JFrame {
    public LoginScreen() {
        // Configurações da janela
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2)); // 3 linhas, 2 colunas

        // Campos de entrada
        JLabel reLabel = new JLabel("Registro (RE):");
        JTextField reField = new JTextField();

        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaField = new JPasswordField();

        // Botão de login
        JButton loginButton = new JButton("Entrar");

        // Adiciona os componentes ao painel
        add(reLabel);
        add(reField);

        add(senhaLabel);
        add(senhaField);

        add(new JLabel()); // Célula vazia
        add(loginButton); // Botão de login

        loginButton.addActionListener(e -> {
            String re = reField.getText();
            String senha = new String(senhaField.getPassword());

            if (re.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            FuncionarioController fc = new FuncionarioController();
            boolean loginSuccess = fc.loginFunc(re, senha);

            if (loginSuccess) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                this.dispose(); // Fecha a tela de login
                new EquipamentoScreen(); // Abre a próxima tela
            } else {
                JOptionPane.showMessageDialog(this, "Credenciais inválidas.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Exibe a janela
        setVisible(true);
    }
}
