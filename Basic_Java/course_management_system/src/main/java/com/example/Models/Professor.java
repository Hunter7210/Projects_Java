package com.example.Models;

public class Professor extends Pessoa {
    private double salario;


    @Override
    public void exibirInformacoes(Pessoa pessoa) {
        System.out.println(salario);
        super.exibirInformacoes(pessoa);
    }


    public double getSalario() {
        return salario;
    }


    public void setSalario(double salario) {
        this.salario = salario;
    }
    
}
