package com.example;

public class BancoService {
    // atributo
    private ContaBancariaRepository repository;

    // construtor
    public BancoService(ContaBancariaRepository repository) {
        this.repository = repository;
    }

    // depositar
    public void depositar(String numeroConta, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser positivo.");
        }
        ContaBancaria conta = repository.encontrarPorNumero(numeroConta);
        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }
        conta.depositar(valor);
        repository.atualizar(conta);
    }

    // sacar
    public void sacar(String numeroConta, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo.");
        }
        ContaBancaria conta = repository.encontrarPorNumero(numeroConta);
        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }
        conta.sacar(valor);
        repository.atualizar(conta);
    }

    // consultar saldo
    public double consultarSaldo(String numeroConta) {
        ContaBancaria conta = repository.encontrarPorNumero(numeroConta);
        if (conta == null) {
            throw new IllegalArgumentException("Conta não encontrada.");
        }
        return conta.getSaldo();
    }
}
