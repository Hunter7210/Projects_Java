package com.example.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.example.EquipamentoNotFoundException;
import com.example.Connection.MongoConnection;
import com.example.Models.Dados;
import com.example.Models.Sensor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class SensorController {
    public List<Sensor> readSensoresPorEquipamento(String codEquip) {
        List<Sensor> sensores = new ArrayList<>();
        try {
            MongoDatabase database = MongoConnection.connectToDatabase();
            MongoCollection<Document> collection = database.getCollection("Equipamento");

            // Busca o equipamento específico
            Document equipamentoDoc = collection.find(Filters.eq("codEquip", codEquip)).first();
            if (equipamentoDoc != null) {
                // Aqui você precisa extrair a lista de sensores do documento do equipamento
                List<Document> listaSensores = (List<Document>) equipamentoDoc.get("Sensor");
                if (listaSensores != null) {
                    for (Document sensorDoc : listaSensores) {
                        String nomeSen = sensorDoc.getString("nomeSen");
                        String fornecSen = sensorDoc.getString("fornecSen");
                        String funSen = sensorDoc.getString("funSen");

                        // Supondo que DadoSensor é uma classe que você criou
                        List<Dados> dados = new ArrayList<>();
                        List<Document> listaDados = (List<Document>) sensorDoc.get("Dados"); // Extrai os dados do
                                                                                                // sensor
                        System.out.println("DADOS:"+dados);
                        if (listaDados != null) {
                            for (Document dadoDoc : listaDados) {
                                String timeStamp = dadoDoc.getString("timeStampDad");
                                String nomeDado = dadoDoc.getString("unidMedidDad");
                                double valorDado = dadoDoc.getDouble("dadosDad");
                                dados.add(new Dados(timeStamp, valorDado, nomeDado));
                            }
                        }


                        // Adiciona o sensor à lista
                        sensores.add(new Sensor(nomeSen, fornecSen ,funSen, dados));
                    }
                }
            } else {
                throw new EquipamentoNotFoundException("Equipamento com código " + codEquip + " não encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao tentar buscar os sensores: " + e.getMessage());
        }
        return sensores;
    }

}
