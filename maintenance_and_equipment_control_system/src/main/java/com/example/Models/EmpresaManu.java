package com.example.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaManu {
    private String cnpjEmpresa;
    private String nomeEmpresa;
    private String enderecoEmpresa;
    private String telefoneEmpresa;
    private String cidadeEmpresa;
    private String emailEmpresa;
}
