package com.example;

import com.example.Connection.MongoConnection;
import com.example.Controllers.FuncionarioController;
import com.example.Models.Funcionario;
import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {
        // Conecta ao banco de dados
        MongoConnection.connectToDatabase();

        // Dados do funcionário a ser inserido
        String nomeFunc = "João Silva";
        String reFunc = "12345";
        String setorFunc = "TI";
        String cargoFunc = "Desenvolvedor";
        String telefoneFunc = "(11) 91234-5678";
        String emailFunc = "joao.silva@example.com";
        String senhaFunc = "senhaSegura";

        FuncionarioController fc = new FuncionarioController();
        // Chama o método para criar funcionário
        // fc.createFuncionario(nomeFunc, reFunc, setorFunc, cargoFunc, telefoneFunc, emailFunc, senhaFunc);
        // fc.loginFunc(reFunc, senhaFunc);
        Funcionario funcionario =  fc.readFuncionario(reFunc);
                // Cria a interface gráfica
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Detalhes do Funcionário");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new GridLayout(0, 2));

            if (funcionario != null) {
                // Adiciona os detalhes do funcionário aos componentes da interface
                frame.add(new JLabel("Nome:"));
                frame.add(new JLabel(funcionario.getNomeFunc()));
                
                frame.add(new JLabel("RE:"));
                frame.add(new JLabel(funcionario.getReFunc()));
                
                frame.add(new JLabel("Setor:"));
                frame.add(new JLabel(funcionario.getSetorFunc()));
                
                frame.add(new JLabel("Cargo:"));
                frame.add(new JLabel(funcionario.getCargoFunc()));
                
                frame.add(new JLabel("Telefone:"));
                frame.add(new JLabel(funcionario.getTelefoneFunc()));
                
                frame.add(new JLabel("Email:"));
                frame.add(new JLabel(funcionario.getEmailFunc()));
            } else {
                // Mensagem se o funcionário não for encontrado
                frame.add(new JLabel("Funcionário não encontrado."));
            }

            frame.setVisible(true);
        });
        
    }
}