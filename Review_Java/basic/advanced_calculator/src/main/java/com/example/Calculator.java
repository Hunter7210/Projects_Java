package com.example;

import java.util.Scanner;

public class Calculator {

    Scanner sc = new Scanner(System.in);

    double a;
    double b;
    double resultado;
    int escolha;

    public int menu() {
        System.out.println("Selecione uma operação:");
        System.out.println("1 - Soma");
        System.out.println("2 - Subtração");
        System.out.println("3 - Divisão");
        System.out.println("4 - Multiplicação");
        System.out.println("5 - Raiz Quadrada");
        System.out.println("0 - Sair");

        int opcao = sc.nextInt();
        return opcao;
    }

    public void addDados() {
        int selecao = menu();
        switch (selecao) {
            case 1:
                System.out.println("Digite o primeiro valor");
                a = sc.nextDouble();
                System.out.println("Digite o segundo valor");
                b = sc.nextDouble();
                soma(a, b);
                break;
            case 2:
                System.out.println("Digite o primeiro valor");
                a = sc.nextDouble();
                System.out.println("Digite o segundo valor");
                b = sc.nextDouble();
                subtracao(a, b);
                break;
            case 3:
                System.out.println("Digite o primeiro valor");
                a = sc.nextDouble();
                System.out.println("Digite o segundo valor");
                b = sc.nextDouble();
                divisao(a, b);
                break;
            case 4:
                System.out.println("Digite o primeiro valor");
                a = sc.nextDouble();
                System.out.println("Digite o segundo valor");
                b = sc.nextDouble();
                multiplicacao(a, b);
                break;
            case 5:
                System.out.println("Digite o primeiro valor");
                a = sc.nextDouble();
                try {
                    raiz(a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 6:

                break;

            default:
                System.out.println("Opção inválida! Tente novamente.");
                break;

        }
    }

    public void soma(double a, double b) {
        resultado = a + b;
        exibindoResultado(resultado);
    }

    public void subtracao(double a, double b) {
        resultado = a - b;
        exibindoResultado(resultado);
    }

    public void divisao(double a, double b) {
        try {
            resultado = a / b;

        } catch (ArithmeticException e) {
            System.err.println(e);
        }
        exibindoResultado(resultado);
    }

    public void multiplicacao(double a, double b) {
        resultado = a * b;
        exibindoResultado(resultado);
    }

    public void raiz(double a) throws Exception {
        if (a < 0) {
            throw new Exception("Não existe raiz de n Negativo");
        }
        try {
            resultado = Math.sqrt(a);

        } catch (Exception e) {
            System.err.println(e);
        }
        exibindoResultado(resultado);
    }

    public void exibindoResultado(double resultado) {
        System.err.println("O Resultado é: " + resultado);
    }

}
