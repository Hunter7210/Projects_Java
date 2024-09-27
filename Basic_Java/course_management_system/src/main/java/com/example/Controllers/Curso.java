package com.example.Controllers;

import java.util.ArrayList;

import com.example.Models.Aluno;
import com.example.Models.Professor;

public class Curso {

    private String nomeCurso;

    ArrayList<Aluno> alunos = new ArrayList<>();
    ArrayList<Professor> professores = new ArrayList<>();

    public void exibirProfessores() {
        for (int i = 0; i < professores.size(); i++) {
            System.out.println(professores.get(i));
        }
    }

    public void exibirAlunos() {
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println(alunos.get(i));
        }
    }

    public void adicionarAluno(Aluno aluno) {
        for (int i = 0; i < alunos.size(); i++) {
            alunos.add(aluno);
        }
    }

    public void exibirInformacoesCurso() {
        System.out.println();
    }


 /* Metodo para calcular a media salarial */
 public void mediaNota() {
    double media = 0; 
    double soma = 0;

    for (int i = 0; i < alunos.size(); i++) {
        soma += alunos.get(i).getNota();

    }

    media = soma / alunos.size();
    System.out.println(media);
}
}
