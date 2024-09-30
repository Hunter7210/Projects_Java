package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Usando o lombok para otimizar a escrita
@AllArgsConstructor // Cria um Contrutor Cheio 
@NoArgsConstructor // Cria um Construtor vazio
@Getter // Cria os Getter
@Setter // Cria os Setter
public class Contato {
    /* Atributos */
    private String nome;
    private String telefone;

    @Override // Sobrescrevendo o metodo padrão toString para o meu proprio toString
    public String toString() {
        return "Nome: " + nome + ", telefone: " + telefone;
    }

}