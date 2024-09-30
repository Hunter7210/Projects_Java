package com.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Eletronico extends Produto implements Transportavel{
    //atributo
    private double volume;
    private double peso;

    public Eletronico(String nome, double preco, double volume) {
        super(nome, preco);
        this.volume = volume;
    }

    // Calular Peso da Abstrata
    @Override
    public double calcularPeso(){
        peso = volume * 150;
        return peso;
    }

    // Calcular Frete da Interface Transportavel
    @Override
    public double calcularFrete(){
        double valorFrete = peso * 1;
        return valorFrete;
    }
}