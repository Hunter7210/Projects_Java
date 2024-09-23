package com.example.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.Models.Aluno;
import com.example.Models.Professor;

public class Curso {

    private String nomeCurso;

    private List<Aluno> alunos;
    private Professor professor;
    private List<Curso> cursos;

    public Curso(String nomeCurso) {
        this.nomeCurso = nomeCurso;

        alunos = new ArrayList<>();
    }

    //Adicionar Professor no curso
    public void addProfessor(Professor professor) {
        this.professor = professor;
    }

    //Adicionar Aluno no curso
    public void addAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    //Lançar notas
    public void lancarNotas(String nomeAluno, double nota) {
        
    }








    public void exibirProfessores() {

        if (professores.isEmpty()) {
            System.out.println("Não ha nenhum funcionario cadastrado");
        } else {
            // Percorrer até que (FOREACH)
            for (Professor professor : professores) { // Para cada linha do meu ArrayList crie um objeto chamado
                                                      // funcionario
                System.out.println(professor.toString());
            }
        }
    }

    public void exibirAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Não ha nenhum funcionario cadastrado");
        } else {
            // Percorrer até que (FOREACH)
            for (Aluno aluno : alunos) { // Para cada linha do meu ArrayList crie um objeto chamado
                                         // funcionario
                System.out.println(aluno.toString());
            }
        }
    }

    public String buscarAluno(String nome) {
        if (alunos.isEmpty()) {
            System.out.println("Não ha nenhum aluno cadastrado");
        } else {

            for (Aluno aluno : alunos) {
                try {
                    if (aluno.getNome().equalsIgnoreCase(nome)) {
                        System.out.println(aluno.toString());
                        return aluno.getNome();
                    } else {
                        throw new Exception("Funcionario não encontrado!");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }
        return nome;
    }

    public String buscarProfessor(String nome) {
        if (alunos.isEmpty()) {
            System.out.println("Não ha nenhum aluno cadastrado");
        } else {

            for (Professor professor : professores) {
                try {
                    if (professor.getNome().equalsIgnoreCase(nome)) {
                        System.out.println(professor.toString());
                        return professor.getNome();
                    } else {
                        throw new Exception("Funcionario não encontrado!");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }
        return nome;
    }

    public void addCurso(Curso curso, String nome, String busca) {

        String nomeAluno = buscarAluno(nome);
        String nomeProfessor = buscarProfessor(busca);

        cursos.add(curso);
    }

    /* Metodo para calcular a media salarial */
    /*
     * public void mediaNota() {
     * double media = 0;
     * double soma = 0;
     * 
     * for (int i = 0; i < alunos.size(); i++) {
     * soma += alunos.get(i).getNota();
     * 
     * }
     * 
     * media = soma / alunos.size();
     * System.out.println(media);
     * }
     */
}
