package com.example;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AlunoIOReader {
    String arquivo = "C:\\Users\\DevTarde\\Documents\\Heitor\\4_Termo\\Java\\Projects_Java\\Basic_Java\\arquivo.txt";
    List<Aluno> alunos;
    double mediaTurma = 0;

    // Contrutor
    public AlunoIOReader() {
        alunos = new ArrayList<>();
        readerFileIO();
    }

    // Criação do metodo
    public void readerFileIO() {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha = "";
            while ((linha = br.readLine()) != null) {
                String[] conteudo = linha.split(",");

                // Cria um objeto da classe aluno
                Aluno aluno = new Aluno(
                        conteudo[0],
                        Double.parseDouble(conteudo[1]),
                        Double.parseDouble(conteudo[2]),
                        Double.parseDouble(conteudo[3]));

                alunos.add(aluno);

            }

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public double mediaAlunos() {
        for (Aluno aluno : alunos) {
            mediaTurma += aluno.mediaNotas();
        }
        mediaTurma /= alunos.size();
        return mediaTurma;
    }

    public String alunoComMaiorNota() {
        double maiorNota = 0;
        String nomeAluno = "";
        for (Aluno aluno : alunos) {
            if (maiorNota < aluno.maiorNota()) {
                maiorNota = aluno.maiorNota();
                nomeAluno = aluno.getNome();
            }
        }
        return "O aluno " + nomeAluno + " teve a maior nota";
    }

    public String alunoComMenorNota() {
        double menorNota = 10;
        String nomeAluno = "";
        for (Aluno aluno : alunos) {
            if (menorNota > aluno.menorNota()) {

                menorNota = aluno.menorNota();
                nomeAluno = aluno.getNome();
            }
        }
        return "O aluno " + menorNota + " teve a menor nota";
    }
}
