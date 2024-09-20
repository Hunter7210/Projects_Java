package com.example.views;

import javax.swing.JOptionPane;

import com.example.Controllers.AgendaTelefonica;
import com.example.Exeptions.AgendaCheiaException;
import com.example.models.Contato;

public class AgendaVisu {
    JOptionPane pane = new JOptionPane();
    AgendaTelefonica agenda = new AgendaTelefonica(5);

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
                            "---- Agenda Telefônica ----",
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
                            String telefone = JOptionPane.showInputDialog("Digite o telefone");

                            Contato contato = new Contato(nome, telefone);
                            agenda.addContato(contato);

                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        break;
                    case 1:
                        agenda.listarContatos();
                        break;
                    case 2:
                        agenda.buscarNome();
                        break;
                    case 3:
                        agenda.removerContato();
                        break;
                    case 4:
                        break;
                    default:
                        break;
                }

            } while (operador != 5);
            {
                throw new AgendaCheiaException("Agenda Cheia");

            }
        } catch (

        Exception e) {
            System.err.println(e);
        }
    }

}
