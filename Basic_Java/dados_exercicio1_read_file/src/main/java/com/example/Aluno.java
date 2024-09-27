package com.example;

import java.util.List;
import java.util.ArrayList;

public class Aluno {
    private String nome;
    private List<Double> nota;

    List<Double> notas = new ArrayList<>();
    double media = 0;

    // Criando meu construtor
    public Aluno(String nome, Double nota1, Double nota2, Double nota3) {
        this.nome = nome;
        notas.add(nota1);
        notas.add(nota2);
        notas.add(nota3);

    }

    // metodo
    public double mediaNotas() {
        for (Double nota : notas) {
            media += nota;
        }
        media /= notas.size();
        return media;
    }

    // Maior nota
    public double maiorNota() {
        double maiorNota = 0;
        for (Double nota : notas) {
            if (nota > maiorNota) {
                maiorNota = nota;
            }
        }
        return maiorNota;
    }

    // Menot nota
    public double menorNota() {
        double menorNota = 10;
        for (Double nota : notas) {
            if (nota < menorNota) {
                menorNota = nota;
            }
        }
        return menorNota;
    }
}
