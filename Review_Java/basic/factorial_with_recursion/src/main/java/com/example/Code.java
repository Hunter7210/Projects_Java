package com.example;

import java.util.Scanner;

public class Code {

    Scanner sc = new Scanner(System.in);
    int numero = -1;
    long resultado = 1;

    //Calculo do fatorial 
    public long calculoFatorial(int n){
        if (n==0 || n==1) {
            resultado = 1;
        } else {
            resultado = n*calculoFatorial(n-1); //Utilizando recursão
        }
        return resultado;
    }

    //Calcular o fatorial
    public void calculadora() throws Exception{
        System.out.println("Digite um numero para calcular fatorial: ");
        numero = sc.nextInt();
        
        if (numero<0) {
            throw new Exception("O numero deve ser positivo");    
        }
    
        try {
            System.out.println(calculoFatorial(numero));
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
