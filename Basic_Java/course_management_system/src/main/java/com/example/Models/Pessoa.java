package com.example.Models;

import java.util.ArrayList;

public class Pessoa {
    /* Atributos */
    private String nome;
    private String CPF;

    public void exibirInformacoes(Pessoa pessoa) {

        System.out.println(nome);
        System.out.println(CPF);
    }

    /* Getters and Setters */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }

}
