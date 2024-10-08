package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Utilização do lombok
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Produto {
    //Atributod
    private String nome;
    private double preco;

    //Metodo para exibir o produto
    @Override
    public String toString(){
        return "Nome: " + nome + " Preco: " + preco;
    }

}
