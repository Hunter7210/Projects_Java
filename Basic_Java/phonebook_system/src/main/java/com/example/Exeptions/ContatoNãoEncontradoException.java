package com.example.Exeptions;

/**
 * ContatoNãoEncontradoException
 */
public class ContatoNãoEncontradoException extends RuntimeException{
    public ContatoNãoEncontradoException(String message) {
        super(message);
    }
}