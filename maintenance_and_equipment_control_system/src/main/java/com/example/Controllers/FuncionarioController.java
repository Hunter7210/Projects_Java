package com.example.Controllers;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.example.Connection.MongoConnection;
import com.example.Models.Funcionario;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class FuncionarioController {
    public static void createFuncionario(String nomeFunc, String reFunc, String setorFunc, String cargoFunc,
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

    public Funcionario readFuncionario(ObjectId id) {
        MongoDatabase database = MongoConnection.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Funcionarios");
        Document found = collection.find(new Document("id", id)).first();

        if (found != null) {
            return new Funcionario(
                    id,
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
}
