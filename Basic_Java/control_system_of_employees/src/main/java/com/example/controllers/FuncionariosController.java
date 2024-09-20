package com.example.controllers;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.example.models.Funcionario;

public class FuncionariosController {

    ArrayList<Funcionario> funcionarios = new ArrayList<>();

    /* Criação de um CRUD */

    /* ADD */
    public void addFuncionario(Funcionario funcionario) {
        if (funcionarios.isEmpty()) {
            /* Return erro */
        } else {
            funcionarios.add(funcionario);
            System.out.println("Cadastrado com sucesso");
        }

    }

    /* GET */
    public void buscarFuncionario() {
        if (funcionarios.isEmpty()) {
            /* Return erro */
        } else {
            for (int i = 0; i < funcionarios.size(); i++) {
                funcionarios.get(i);

                System.out.println(funcionarios.get(i));
            }
        }

    }

    /* Remove */
    public void removeFuncionario() {
        String busca = JOptionPane.showInputDialog("Digite o nome para buscar: ");

        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getNome().equalsIgnoreCase(busca)) {
                String funcionari = funcionarios.get(i).getNome();
                funcionarios.remove(funcionarios.get(i));
                System.out.println("Funcionario"+funcionari + "removido com sucesso");
            }
        }

    }

    /* Metodo para calcular a media salarial */
    public void mediaSalario() {
        double media = 0;
        double soma = 0;

        for (int i = 0; i < funcionarios.size(); i++) {
            soma += funcionarios.get(i).getSalario();

        }

        media = soma / funcionarios.size();
        System.out.println(media);
    }
}
