package com.example.Models;

import java.util.List;
import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Equipamento {
    private String nomeEqui;
    private String dataCompraEquip;
    private String tipoEquip;
    private String fornecEquip;
    private int qtdSensorEquip;
    private String statusEquip;
    private List<Sensor> sensores;
    private List<Manutencao> manutencoes;
    private List<QrCode> qrcodes;
}
