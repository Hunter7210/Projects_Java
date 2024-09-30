package com.example.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
abstract class Produto {

    private String nome;
    private double preco;

    public double calcularPeso(double peso1, double peso2) {
        return peso1 + peso2;
    }

}
