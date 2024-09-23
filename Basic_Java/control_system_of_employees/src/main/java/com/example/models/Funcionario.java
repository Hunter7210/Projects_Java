package com.example.models;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class Funcionario {

    // Atributos
    private String nome;
    private int idade;
    private double salario;

    // Criando o Construtor default
    public Funcionario() {
        // super(); //É usado para referenciar uma outra classe;
    }

    @Override // Sobrescrevendo o metodo padrão toString para o meu proprio toString
    public String toString() {

        return "Nome: " + nome + ", Idade: " + idade + ", Salario: " + salario;
    }

    // Método para mostrar as informações visualmente
    public void showInfo() {
        JOptionPane.showMessageDialog(null, this.toString(), "Informações do Funcionário",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Criando o Construtor, ele é reponsavel por instanciar o objeto
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
