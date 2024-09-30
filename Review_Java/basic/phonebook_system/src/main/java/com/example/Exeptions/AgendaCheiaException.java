package com.example.Exeptions;

//Criando uma Exception
public class AgendaCheiaException extends Exception { // Herda todos os metodos da minha classe Exception
    // Construtor
    public AgendaCheiaException(String mensagem) {
        super(mensagem);
    }
}
