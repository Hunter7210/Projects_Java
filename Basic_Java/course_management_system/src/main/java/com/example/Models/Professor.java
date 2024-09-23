package com.example.Models;

public class Professor extends Pessoa {
    private double salario;


    @Override
    public String exibirInformacoes(Pessoa pessoa) {
        return "Salario: " +salario ;/*+    super.exibirInformacoes(pessoa); */
    }


    public double getSalario() {
        return salario;
    }


    public void setSalario(double salario) {
        this.salario = salario;
    }
    
}
