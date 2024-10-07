package com.example.Controllers;

import java.util.List;

import org.bson.Document;

import com.example.Connection.MongoConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class ManutencaoController {

    // Função de exemplo para criar manutenção
    public Document createManutencao(String dataIniManut, String dataFimManut, String tipoManut,
            String statusManut, String dataPrevisFimManut, String dataPrevisIniManut, List<Document> empresasManut) {
        return new Document("tipoManut", tipoManut)
                .append("statusManut", statusManut)
                .append("dataIniManut", dataIniManut)
                .append("dataFimManut", dataFimManut)
                .append("dataPrevisIniManut", dataPrevisIniManut)
                .append("dataPrevisFimManut", dataPrevisFimManut)
                .append("EmpresaManu", empresasManut);
    }


    // Função de exemplo para criar e adicionar manutenção a um equipamento
    // existente
    public void updateManutencao(String codEquip, String dataIniManut, String dataFimManut, String tipoManut,
            String statusManut, String dataPrevisFimManut, String dataPrevisIniManut, List<Document> empresasManut) {

        // Conecta ao banco de dados MongoDB
        MongoDatabase database = MongoConnection.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Equipamento");

        // Cria o documento de manutenção
        Document novaManutencao = new Document("tipoManut", tipoManut)
                .append("statusManut", statusManut)
                .append("dataIniManut", dataIniManut)
                .append("dataFimManut", dataFimManut)
                .append("dataPrevisIniManut", dataPrevisIniManut)
                .append("dataPrevisFimManut", dataPrevisFimManut)
                .append("EmpresaManu", empresasManut);

        // Realiza o update: adiciona a nova manutenção à lista existente de manutenções
        collection.updateOne(
                Filters.eq("codEquip", codEquip), // Localiza o equipamento pelo nome
                Updates.push("Manutencao", novaManutencao) // Adiciona a nova manutenção à lista de manutenções
        );

        System.out.println("Manutenção adicionada ao equipamento: " + codEquip);
    }

    // Função para listar todas as manutenções de um equipamento
    public List<Document> listarManutencoes(String codEquip) {
        // Conecta ao banco de dados MongoDB
        MongoDatabase database = MongoConnection.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Equipamento");

        // Busca o equipamento pelo nome
        Document equipamento = collection.find(Filters.eq("codEquip", codEquip)).first();

        if (equipamento != null) {
            // Retorna a lista de manutenções do equipamento
            List<Document> manutencoes = equipamento.getList("Manutencao", Document.class);
            System.out.println("Manutenções do equipamento " + codEquip + ": " + manutencoes);
            return manutencoes;
        } else {
            System.out.println("Equipamento não encontrado: " + codEquip);
            return null;
        }
    }
}
