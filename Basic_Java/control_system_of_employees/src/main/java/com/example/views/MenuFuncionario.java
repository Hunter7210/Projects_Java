package com.example.views;

import javax.swing.JOptionPane;

import com.example.controllers.FuncionariosController;
import com.example.models.Funcionario;

public class MenuFuncionario {
    JOptionPane pane = new JOptionPane();
    FuncionariosController funcionariosControler = new FuncionariosController();
    Funcionario funcionario = new Funcionario();

    int operacao = 0;
    String[] opcoes = {
            "Adicionar Funcionario",
            "Listar Funcionarios",
            "Buscar Funcionarios",
            "Remover Funcionarios",
            "Media Salarial Funcionarios",
            "Sair"
    };

    public void exibirLista() {
        // Cria um menu com opções

        try {
            do {
                try {
                    operacao = JOptionPane.showOptionDialog(null,
                            "---- Gerenciamento de Cadastro ----",
                            "Menu",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            opcoes,
                            opcoes[0]);

                } catch (NumberFormatException e) {
                    System.err.println(e);
                    operacao = 0;
                }
                /*
                 * operacao = Integer.parseInt(JOptionPane.showInputDialog("Digite a operação",
                 * null));
                 */
                switch (operacao) {
                    case 0:

                        String nome = JOptionPane.showInputDialog("Digite o nome");
                        int idade = Integer.parseInt(JOptionPane.showInputDialog("Digite o idade"));
                        double salario = Double.parseDouble(JOptionPane.showInputDialog("Digite o salario"));

                        Funcionario funcionario = new Funcionario(nome, idade, salario);

                        funcionariosControler.addFuncionario(funcionario);

                        break;
                    case 1:
                        funcionariosControler.listarFuncionario();
                        break;
                    case 2:
                        String busca = JOptionPane.showInputDialog("Digite o nome para buscar: ");
                        funcionariosControler.buscarFuncionario(busca);
                        break;
                    case 3:
                        String buscaRemo = JOptionPane.showInputDialog("Digite o nome para remover: ");
                        funcionariosControler.removeFuncionario(buscaRemo);
                        break;

                    case 4:
                        
                            funcionariosControler.mediaSalario();
                        
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Escolha um opção válida");
                }

            } while (operacao != 5);
            {
                /* throw new AgendaCheiaException("Agenda Cheia"); */

            }
        } catch (

        Exception e) {
            System.err.println(e);
        }
    }

}
