package com.example.Controllers;

import org.bson.Document;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.Connection.MongoConnection;
import com.example.Models.Equipamento;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

//Classe para realizar o controle dos equipamentos
public class EquipamentoController {

    // Criação de um CRUD para equipamentos

    // CREATE

    // Criação do metodo para realizar a criação de novos equipamentos, os
    // parametros incluem Listas e Strings
    public void createEquipamento(String nomeEqui, String codEquip, String dataCompraEquip, String tipoEquip,
            String fornecEquip,
            int qtdSensorEquip, String statusEquip, List<Document> sensores, List<Document> manutencoes,
            List<Document> qrcodes) {

        // Realizando a conexão com o MongoDbB
        MongoDatabase database = MongoConnection.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Equipamento");

        // Cria um documento temporário para conter todos os parâmetros e organiza-los
        // em pares chave-valor
        Document equipamento = new Document("nomeEqui", nomeEqui)
                .append("codEquip", codEquipFormat(codEquip))
                .append("dataCompraEquip", dataCompraEquip)
                .append("tipoEquip", tipoEquip)
                .append("fornecEquip", fornecEquip)
                .append("qtdSensorEquip", qtdSensorEquip)
                .append("statusEquip", statusEquip)
                .append("Sensor", sensores)
                .append("Manutencao", manutencoes)
                .append("QRCODE", qrcodes);

        // Metodo responsável por inserir um novo item
        collection.insertOne(equipamento);

        // Mensagem para a conclusão do equipamento
        System.out.println("Equipamento inserido com sucesso.");
    }

    // Função de exemplo para criar sensor
    public Document createSensor(String nomeSen, String fornecSen, String funSen, List<Document> acionamentos) {
        return new Document("nomeSen", nomeSen)
                .append("fornecSen", fornecSen)
                .append("funSen", funSen)
                .append("Acionamento", acionamentos);
    }

    // Função de exemplo para criar acionamento
    public Document createDados(double dadosDad, String unidMedDad) {
        return new Document("timeStampDad", timeStamp())
                .append("dadosDad", dadosDad)
                .append("unidMedDad", unidMedDad);
    }

    // Função de exemplo para criar QR Code
    public Document createQRCode(String imgQr, String titQr, String descQr) {
        return new Document("imgQr", imgQr)
                .append("titQr", titQr)
                .append("descQr", descQr);
    }

    /* READ */
    
    public List<Document> readEquipamento(String codEquip) {
        MongoDatabase database = MongoConnection.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Equipamento");

        
          Document equipamento = collection.find(Filters.eq("codEquip", codEquip)).first();
        if (equipamento != null) {
            System.out.println(equipamento.toJson());
        } else {
            System.out.println("Equipamento não encontrado.");
        }
    }

    /* UPDATE */
    public void updateEquipamento(String nomeEqui, String novoStatus) {
        MongoDatabase database = MongoConnection.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Equipamento");

        collection.updateOne(Filters.eq("nomeEqui", nomeEqui),
                Updates.set("statusEquip", novoStatus));

        System.out.println("Status do equipamento atualizado para: " + novoStatus);
    }

    /* DELETE */
    public void deleteEquipamento(String nomeEqui) {
        MongoDatabase database = MongoConnection.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Equipamento");

        collection.deleteOne(Filters.eq("nomeEqui", nomeEqui));
        System.out.println("Equipamento deletado com sucesso.");
    }

    /* Metodo para buscar Data e Hora do sistema */
    public String timeStamp() {
        // Obtém a data e hora atuais
        LocalDateTime dataHoraAtual = LocalDateTime.now();

        // Define o formato de exibição
        DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Formata a data e hora
        String dataHoraFormatada = dataHoraAtual.format(formatacao);

        return dataHoraFormatada;
    }

    public String codEquipFormat(String codEquipamento) {
        MongoDatabase database = MongoConnection.connectToDatabase();
        MongoCollection<Document> collection = database.getCollection("Equipamento");

        long numeroCod = collection.countDocuments();
        String formatado;
        if (numeroCod < 1000) {
            DecimalFormat df = new DecimalFormat("0000");
            formatado = codEquipamento + df.format(numeroCod + 1);
        } else {
            formatado = String.valueOf(numeroCod); // Converte diretamente para string
        }
        System.out.println(formatado);
        return formatado;
    }

}