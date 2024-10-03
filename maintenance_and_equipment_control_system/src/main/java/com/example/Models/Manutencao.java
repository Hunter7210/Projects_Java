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
    private String dataIniManut;
    private String dataFimManut;
    private String tipoManut;
    private String statusManut;
}
