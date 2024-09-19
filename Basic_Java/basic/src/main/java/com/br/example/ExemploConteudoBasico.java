package com.br.example;

public class ExemploConteudoBasico {
    public void operacoes() {
        // Variaveis do tipo primitiva
        int a = 20, b = 30;
        double c = 7.8;
        char letra = 'J';
        boolean teste = false;

        // Para teste ser true é necessario que as duas condições sejam verdadeiras
        teste = (a > b) && (c < 8);

        System.out.println("Resultado a+b = " + (a + b));
        System.out.println("Resultado para teste = " + teste);
        System.out.println("Exiba " + letra);

    }

    public void controleFluxo() {

        // If-Else
        int idade = 18;
        if (idade >= 18) {
            System.out.println("Maior de idade");
        } else {
            System.out.println("Menor de idade");
        }

        // Switch
        int dia = 2;
        switch (dia) {
            case 1:
                System.out.println("Segunda-feira");
                break;
            case 2:
                System.out.println("Terça-feira");
                break;
            default:
                System.out.println("Outro dia");
        }

        /* Laços de repetição */
        // For Loop
        for (int i = 1; i <= 5; i++) {
            System.out.println("Contagem: " + i);
        }

        // While Loop
        int contador = 5;
        while (contador > 0) {
            System.out.println("Contador: " + contador);
            contador--;
        }

    }

    public void array() {
        int[] numeros = { 10, 20, 30, 40, 50 };
        System.out.println(numeros[0]); // 10

        // Percorrendo o array
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Elemento: " + numeros[i]);
        }

    }

    public void tryCa() {
        try {
            int resultado = 10 / 0;  // Lança ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Erro: Divisão por zero!");
        }
        
        try {
            int[] array = new int[3];
            System.out.println(array[5]);  // Lança ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Erro: Índice fora do limite do array!");
        }
        
    }
}
