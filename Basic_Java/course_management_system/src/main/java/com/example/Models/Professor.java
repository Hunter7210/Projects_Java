package com.example.Models;

public class Professor extends Pessoa {
    private double salario;

    public Professor(String nome, String cpf, String matricula) {
        super(nome, cpf);
        this.salario = salario;

    }

    @Override
    // Polimorfismo - sobreescrita
    public String exibirInfo() {
        super.exibirInfo();
        return "Salario: " + salario;
    }
}
