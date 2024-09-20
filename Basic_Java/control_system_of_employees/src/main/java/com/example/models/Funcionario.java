package com.example.models;

public class Funcionario {
    private String nome;
    private int idade;
    private double salario;

    // Criando o Construtor default
    public Funcionario() {
        super();
    }
    @Override // Sobrescrevendo o metodo padrão toString para o meu proprio toString
    public String toString() {
        return "Nome: " + nome + ", Idade: " + idade  +"Salairo: "+salario;
    }


    // Criando o Construtor
    public Funcionario(String nome, int idade, double salario) {
        this.nome = nome;
        this.idade = idade;
        this.salario = salario;
    }

    // Criando os GETTERS and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

}
