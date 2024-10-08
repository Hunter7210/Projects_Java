package com.example.Models;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manutencao {
    private List<EmpresaManu> empresaManutencao;
    private String idManut;
    private String tipoManut;
    private String statusManut;
    private String dataIniManut;
    private String dataFimManut;
    private String dataPrevisFimManut;
    private String dataPrevisIniManut;
}
