package com.example.Views;

import javax.swing.JOptionPane;

public class Menu {

    int operacao = 0;

    String[] opcoes = {
            "Exibir Notas",
            "Lançar Notas",
            "Adicionar professor",
            "Adicionar aluno",
    };

    public void exibirMenu() {
        try {
            do {
                try {
                    operacao = JOptionPane.showOptionDialog(null, "---- Gerenciamento de Cadastro ----", "Menu",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
                } catch (NumberFormatException e) {
                    System.err.println(e);
                    operacao = 0;
                }

                switch (operacao) {
                    case 0:
                        
                        break;
                
                    default:
                        break;
                }
            } while (operacao != 4);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
