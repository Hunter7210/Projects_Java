package com.example.views;

import javax.swing.JOptionPane;

import com.example.controllers.FuncionariosController;
import com.example.models.Funcionario;

public class MenuFuncionario {
    JOptionPane pane = new JOptionPane();
    FuncionariosController funcionariosControler = new FuncionariosController();
    Funcionario funcionario = new Funcionario();
    int operador = 0;
    String[] opcoes = {
            "Adicionar Contato",
            "Listar Contatos",
            "Buscar Contatos",
            "Remover Contatos",
            "Sair"
    };

    public void exibirLista() {
        // Cria um menu com opções

        try {
            do {
                try {
                    operador = JOptionPane.showOptionDialog(null,
                            "---- Gerenciamento de Cadastro ----",
                            "Menu",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            opcoes,
                            opcoes[0]);

                } catch (NumberFormatException e) {
                    System.err.println(e);
                    operador = 0;
                }
                /*
                 * operador = Integer.parseInt(JOptionPane.showInputDialog("Digite a operação",
                 * null));
                 */
                switch (operador) {
                    case 0:
                        try {
                            String nome = JOptionPane.showInputDialog("Digite o nome");
                            int idade = Integer.parseInt(JOptionPane.showInputDialog("Digite o idade"));
                            double salario = Double.parseDouble(JOptionPane.showInputDialog("Digite o salario"));

                            Funcionario funcionario = new Funcionario(nome, idade, salario);
                            
                            funcionariosControler.addFuncionario(funcionario);

                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    case 1:
                        funcionariosControler.buscarFuncionario();
                        break;
                    case 2:
                        try {
                            funcionariosControler.removeFuncionario();

                        } catch (Exception e) {
                            System.err.println(e);
                        }
                        break;
                    case 3:
                        try {
                            funcionariosControler.mediaSalario();
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                        break;
                    case 4:
                        break;
                    default:
                        break;
                }

            } while (operador != 5);
            {
                /* throw new AgendaCheiaException("Agenda Cheia"); */

            }
        } catch (

        Exception e) {
            System.err.println(e);
        }
    }

}
