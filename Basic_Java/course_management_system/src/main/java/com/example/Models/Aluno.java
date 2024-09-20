package com.example.Models;

public class Aluno extends Pessoa {
    private String matricula;
    private double nota;

    @Override
    public void exibirInformacoes(Pessoa pessoa) {
        System.out.println(matricula);
        System.out.println(nota);
        super.exibirInformacoes(pessoa);
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
