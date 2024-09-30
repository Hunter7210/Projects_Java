package com.example;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//Criação da classe Vendas
public class Vendas {
    // atributos
    private Map<String, List<Produto>> vendasCPF; // Utilização de Map

    // construtor
    public Vendas() {
        vendasCPF = new HashMap<>();
    }

    // Metodos da classe

    // Cadastro de venda
    public void cadastrarVenda(String cpf, Produto p) {
        // Verificando se ha alguma venda cadastrada
        for (String cpfUsuario : vendasCPF.keySet()) {
            // Verifica se ha algum cpf cadastrado
            if (cpfUsuario.equals(cpf)) {
                // Copia a lista de produtos do meu usuario
                List<Produto> produtosCPF = vendasCPF.get(cpf);
                // Adiciona um novo produto a esta list
                produtosCPF.add(p);
                // Atualiza/Reescreve o hashmap
                vendasCPF.put(cpf, produtosCPF);
                return;
                // PARA O MESMO CPF TEMOS INFINITOS PRODUTOS
            }
        }
        // Adicionando a primeira compra
        List<Produto> produtoCPFVazio = new ArrayList<>();
        produtoCPFVazio.add(p);

        vendasCPF.put(cpf, produtoCPFVazio);
    }

    // Listar Produtos de apenas um Usuario
    public List<Produto> listarProdUsu(String cpf) {
        List<Produto> listaCPF = vendasCPF.getOrDefault(cpf, Collections.emptyList()); // Se caso não encontrar o meu
                                                                                       // cpf ele adiciona um lista
                                                                                       // vazia de forma automatica
        return listaCPF;
    }

    // Listar com Filtro Usando STREAM
    public List<Produto> listarProdutosFiltro(String cpf, double valorMinimo) throws Exception {
        List<Produto> listaCPFFiltro = vendasCPF.getOrDefault(cpf, Collections.emptyList());

        if (listaCPFFiltro.isEmpty()) { // Se a lista for vazio o cpf não existe né
            throw new Exception("Cpf não encontrado");
        } else {
            // Criando uma nova lista
            List<Produto> resultado = listaCPFFiltro.stream().filter(p -> p.getPreco() >= valorMinimo)
                    .collect(Collectors.toList());
            return resultado;
        }

    }

}
