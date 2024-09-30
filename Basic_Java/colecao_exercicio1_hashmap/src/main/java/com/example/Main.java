package com.example;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        Vendas vendas = new Vendas();
        String operacao;

        do {
            operacao = JOptionPane.showInputDialog(
                    "----GERENCIAMENTO DE VENDAS----\n 1 - Cadastrar Venda \n 2- Listar Vendas pro CPF \n 3- Listar Vendas por CPF e Valor Mininmo\n 4- Sair");
            switch (operacao) {
                case "1":
                    String cpfVenda = JOptionPane.showInputDialog("Informe o CPF do Cliente: ");
                    String nomeProduto = JOptionPane.showInputDialog("Informe o nome do Produto");
                    double precoProduto = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do Produto"));

                    Produto produto = new Produto(nomeProduto, precoProduto);
                    vendas.cadastrarVenda(cpfVenda, produto);
                    JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso", nomeProduto, 1);
                    break;

                case "2":
                    String cpfCliente = JOptionPane.showInputDialog("Informe o CPF do Cliente");
                    System.out.println(vendas.listarProdUsu(cpfCliente).toString());
                    break;
                case "3":
                    String cpfUsuario = JOptionPane.showInputDialog("Informe o CPF do Cliente");
                    double valorMinimo = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor minimo"));

                    try {
                        System.out.println(vendas.listarProdutosFiltro(cpfUsuario, valorMinimo).toString());
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    break;
                case "4":

                    break;

                default:
                    break;
            }

        } while (operacao != "4");

    }
}