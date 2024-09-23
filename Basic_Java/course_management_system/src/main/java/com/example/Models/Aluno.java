package com.example.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Aluno extends Pessoa {
    private String matricula;
    private double nota;

    public String exibirInformacoes(Pessoa pessoa) {
    return "Matriculo: "+matricula + "Nota: "+ nota; /* super.exibirInformacoes(pessoa); */
    }

    public Aluno(String nome, String cpf, String matricula) {
       super(nome,cpf);
       this.matricula = matricula;
       this.nota = 0.0;
    }
    //Polimorfismo - sobreescrita
}
