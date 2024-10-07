package com.example.Controllers;

import org.bson.Document;

import com.example.Connection.MongoConnection;
import com.example.Models.Funcionario;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class FuncionarioController {

    /* Criando um metodo para criar Funcioncionario */
    public void createFuncionario(String nomeFunc, String reFunc, String setorFunc, String cargoFunc,
            String telefoneFunc, String emailFunc, String senhaFunc) {
        MongoDatabase database = MongoConnection.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Funcionarios");

        Document funcionario = new Document("nomeFunc", nomeFunc)
                .append("reFunc", reFunc)
                .append("setorFunc", setorFunc)
                .append("cargoFunc", cargoFunc)
                .append("telefoneFunc", telefoneFunc)
                .append("emailFunc", emailFunc)
                .append("senhaFunc", senhaFunc);

        collection.insertOne(funcionario);
        System.out.println("Funcionário inserido com sucesso.");
    }

    /* Metodo para retornar apenas o usuario especificado */
    public Funcionario readFuncionario(String reFunc) {
        MongoDatabase database = MongoConnection.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Funcionarios");
        Document found = collection.find(new Document("reFunc", reFunc)).first();

        if (found != null) {
            return new Funcionario(
                    found.getString("nomeFunc"),
                    found.getString("reFunc"),
                    found.getString("setorFunc"),
                    found.getString("cargoFunc"),
                    found.getString("telefoneFunc"),
                    found.getString("emailFunc"),
                    found.getString("senhaFunc"));
        }
        return null; // Retorna null se não encontrado
    }

    // Atualizar um funcionário
    public void updateFuncionario(String nomeFunc, String novoCargo, String novoSetor) {
        MongoDatabase database = MongoConnection.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Funcionarios");
        collection.updateOne(
                new Document("nomeFunc", nomeFunc),
                new Document("$set", new Document("cargoFunc", novoCargo).append("setorFunc", novoSetor)));
    }

    // Deletar um funcionário
    public void deleteFuncionario(String nomeFunc) {
        MongoDatabase database = MongoConnection.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Funcionarios");
        collection.deleteOne(new Document("nomeFunc", nomeFunc));
    }

    // Criando um metodo para o login do usuario
    public boolean loginFunc(String reFunc, String senhaFunc) {
        // Conectando com o banco de dados
        MongoDatabase database = MongoConnection.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Funcionarios");

        // Buscando o funcionário pelo reFunc
        Document funcionario = collection.find(new Document("reFunc", reFunc)).first();

        // Verificando se o funcionario foi encontrado
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado");
            return false;

        }

        // Obtendo a senha do funcionario
        String senhaBanco = funcionario.getString("senhaFunc"); // Busca no meu banco a sneha do funcionario

        if (senhaBanco != null && senhaBanco.equals(senhaFunc.trim())) {
            System.out.println("Login realizado com sucesso.");
            return true; // Retorna verdadeiro se o login for bem-sucedido
        } else {
            System.out.println("Senha incorreta.");
            return false; // Retorna falso se a senha estiver incorreta
        }

    }

}
