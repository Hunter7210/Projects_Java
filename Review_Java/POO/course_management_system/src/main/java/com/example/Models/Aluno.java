package com.example.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aluno extends Pessoa implements Avaliavel {
    private String matricula;
    private double nota;


    public Aluno(String nome, String cpf, String matricula) {
        super(nome, cpf);
        this.matricula = matricula;
        this.nota = 0.0;
    }

    @Override
    // Polimorfismo - sobreescrita
    public String exibirInfo() {
        super.exibirInfo();
        return "Matricula: " + matricula;
    }

    @Override
    public void avaliarDesempenho() {
        if (nota >= 7) {
            System.out.println("Aluno Aprovado");
        } else if (nota>=5 && nota<7){
            System.out.println("Aluno de Recuperação");
        } else {
            System.out.println("Aluno Reprovado");
        }
    }

}
