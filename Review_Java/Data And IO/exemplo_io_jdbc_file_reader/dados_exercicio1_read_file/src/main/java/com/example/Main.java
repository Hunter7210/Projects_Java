package com.example;

public class Main {
    public static void main(String[] args) {
        AlunoIOReader al = new AlunoIOReader();
        System.out.println("A media da turma é " + al.mediaAlunos());
        System.out.println(al.alunoComMaiorNota());
        System.out.println(al.alunoComMenorNota());


    }
}