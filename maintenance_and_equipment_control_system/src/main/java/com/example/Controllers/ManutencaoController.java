package com.example.Controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.example.Connection.MongoConnection;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
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
    public void updateManutencao(String codEquip, String idManut, String dataIniManut, String dataFimManut,
            String tipoManut,
            String statusManut, String dataPrevisFimManut, String dataPrevisIniManut, List<Document> empresasManut) {

        // Conecta ao banco de dados MongoDB
        MongoDatabase database = MongoConnection.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Equipamento");

        // Cria o documento de manutenção
        Document novaManutencao = new Document("tipoManut", tipoManut)
                .append("idManut", codFormatManut())
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

    public List<Document> readTodasManutencoes() {
        List<Document> manutencoes = new ArrayList<>();
        try {
            MongoDatabase database = MongoConnection.connectToDatabase();
            MongoCollection<Document> collection = database.getCollection("Manutencao");

            // Buscar todos os documentos
            FindIterable<Document> results = collection.find(); // Find sem filtros busca todos os itens

            // Iterar sobre os resultados e adicioná-los à lista
            for (Document doc : results) {
                manutencoes.add(doc);
            }

            return manutencoes;
        } catch (Exception e) {
            System.err.println("Erro ao tentar buscar as manutenções: " + e.getMessage());
            return Collections.emptyList(); // Retorna uma lista vazia em caso de erro
        }
    }

    public List<Document> getManutencoes(String codEquip) {
        List<Document> manutencoes = new ArrayList<>();
        try {
            MongoDatabase database = MongoConnection.connectToDatabase();
            MongoCollection<Document> collection = database.getCollection("Equipamento");

            // Busca o documento do equipamento específico
            Document equipamento = collection.find(Filters.eq("codEquip", codEquip)).first();

            if (equipamento != null && equipamento.containsKey("Manutencao")) {
                List<Document> listaManutencao = (List<Document>) equipamento.get("Manutencao");
                for (Document manutencao : listaManutencao) {
                    manutencoes.add(manutencao); // Adiciona o documento de manutenção diretamente à lista
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao tentar buscar as manutenções: " + e.getMessage());
        }
        return manutencoes; // Retorna uma lista de documentos representando as manutenções
    }

    public void updateStatusManutencao(String codEquip, String manutencaoId, String novoStatus) {
        try {
            // Conecta ao banco de dados MongoDB
            MongoDatabase database = MongoConnection.connectToDatabase();
            MongoCollection<Document> collection = database.getCollection("Equipamento");

            // Atualiza o status da manutenção
            collection.updateOne(
                    Filters.eq("codEquip", codEquip), // Localiza o equipamento pelo código
                    Updates.set("Manutencao.$[elem].statusManut", novoStatus), // Atualiza o status da manutenção
                    new UpdateOptions()
                            .arrayFilters(Arrays.asList(Filters.eq("elem._id", manutencaoId))) // Filtra a
                                                                                               // manutenção
                                                                                               // a ser
                                                                                               // atualizada
            );

            System.out.println("Status da manutenção atualizado para o equipamento: " + codEquip);
        } catch (MongoWriteException e) {
            System.err.println("Erro ao atualizar status da manutenção: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("ID da manutenção inválido: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteManutencao(String codEquip, String manutencaoId) {
        // Conecta ao banco de dados MongoDB
        MongoDatabase database = MongoConnection.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Equipamento");

        // Remove a manutenção da lista de manutenções
        collection.updateOne(
                Filters.eq("codEquip", codEquip), // Localiza o equipamento pelo código
                Updates.pull("Manutencao", Filters.eq("idManut", manutencaoId)) // Remove a
                                                                                              // manutenção
                                                                                              // com o ID
                                                                                              // especificado
        );

        System.out.println("Manutenção removida do equipamento: " + codEquip);
    }

    public String codFormatManut() {
        MongoDatabase database = MongoConnection.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Manutencao");

        long numeroCod = collection.countDocuments();
        String formatado;
        if (numeroCod < 1000) {
            DecimalFormat df = new DecimalFormat("0000");
            formatado = df.format(numeroCod + 1);
        } else {
            formatado = String.valueOf(numeroCod); // Converte diretamente para string
        }
        System.out.println(formatado);
        return formatado;
    }

}
