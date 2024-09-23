package com.example.controllers;

import java.util.ArrayList;
import java.util.List; //Serve apenas para a interface

import javax.swing.JOptionPane;

import com.example.models.Funcionario;

public class FuncionariosController {

    private List<Funcionario> funcionarios;

    public FuncionariosController() {
        funcionarios = new ArrayList<>(); // Lista dinamica
    }

    /* Criação de um CRUD */

    /* ADD */
    public void addFuncionario(Funcionario funcionario) {
        try {
            funcionarios.add(funcionario);
            System.out.println("Cadastrado com sucesso");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /* GET */
    public void listarFuncionario() {
        if (funcionarios.isEmpty()) {
            System.out.println("Não ha nenhum funcionario cadastrado");
        } else {
            // Percorrer até que (FOREACH)
            for (Funcionario funcionario : funcionarios) { // Para cada linha do meu ArrayList crie um objeto chamado
                                                           // funcionario
                System.out.println(funcionario.toString());
            }
        }

    }

    public void buscarFuncionario(String nome) {
        if (funcionarios.isEmpty()) {
            System.out.println("Não ha nenhum funcionario cadastrado");
        } else {

            for (Funcionario funcionario : funcionarios) {
                try {
                    if (funcionario.getNome().equalsIgnoreCase(nome)) {
                        System.out.println(funcionario.toString());
                    } else {
                        throw new Exception("Funcionario não encontrado!");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }

    }

    /* Remove */
    public void removeFuncionario(String nome) {

        try {
            for (Funcionario funcionario : funcionarios) {

                if (funcionario.getNome().equalsIgnoreCase(nome)) {
                    String nomeFunc = funcionario.getNome();
                    funcionarios.remove(funcionario); // REMOVE o objeto do meu funcionario
                    System.out.println("Funcionario " + nomeFunc + " removido com sucesso");

                } else {
                    throw new Exception("Funcionario não encontrado!");
                }
            }
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    /* Metodo para calcular a media salarial */
    public double mediaSalario() {
        try {
            double mediaSal = 0;
            if (funcionarios.isEmpty()) {
                return 0;
            }
            for (Funcionario funcionario : funcionarios) {
                mediaSal += funcionario.getSalario();
            }

            System.out.println("Média salarial " + mediaSal);

            return mediaSal / funcionarios.size();
        } catch (ArithmeticException e) {
            System.err.println(e);
        }
        return 0;
    }
}
