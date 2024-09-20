package com.example.Controllers;

import javax.swing.JOptionPane;

import com.example.Exeptions.AgendaCheiaException;
import com.example.Exeptions.ContatoNãoEncontradoException;
import com.example.models.Contato;

public class AgendaTelefonica {
    // atributos
    private Contato[] contatos; // Criação de um Array da minha classe contato chamada contatos
    private int contator;

    public AgendaTelefonica(int maxContato) {
        contatos = new Contato[maxContato];
        contator = 0;
    }

    // Metodos do CRUD

    // add
    public void addContato(Contato contato) throws AgendaCheiaException { // Adicionando um objeto de Contato
        if (contator >= contatos.length) { // Verifica o tamanho maximo de contatos presentes na lista
            throw new AgendaCheiaException("Agenda Cheia"); // Chama minha Exception persolalizada
        }
        contatos[contator] = contato;
        contator++;
        System.out.println("Contato Adcionado com sucesso");

    }

    // listar
    public void listarContatos() {
        if (contator == 0) {
            System.out.println("Agenda vazia");
        } else {
            for (int i = 0; i < contator; i++) {

                System.out.println(contatos[i].toString());
            }
        }
    }

    // buscar
    public Contato buscarNome() {
        String busca = JOptionPane.showInputDialog("Digite o nome para buscar: ");

        for (int i = 0; i < contator; i++) {
            if (contatos[i].getNome().equalsIgnoreCase(busca)) {
                
                return contatos[i];
            }
        }
        throw new ContatoNãoEncontradoException("Contato não encontrado");
    }

    // remover
    public void removerContato() {
        boolean encontrado = false;
        String busca = JOptionPane.showInputDialog("Digite o nome para remover: ");
        for (int i = 0; i < contator; i++) {
            if (contatos[i].getNome().equalsIgnoreCase(busca)) {
                encontrado = true;
                contatos[i] = contatos[contator - 1];
                contatos[contator - 1] = null;
                contator--;
                System.out.println("Contato removido com sucessos");
            }
        }
        if (!encontrado) {
            throw new ContatoNãoEncontradoException("Contato não encontrado");
        }
    }

}
