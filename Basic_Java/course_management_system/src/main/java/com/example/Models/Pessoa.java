package com.example.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Pessoa {
    /* Atributos */
    private String nome;
    private String cpf;

    public String exibirInformacoes(Pessoa pessoa) {
        return "Nome " + nome + ", CPF " + cpf;
    }

}
