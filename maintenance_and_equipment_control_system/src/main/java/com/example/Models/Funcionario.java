package com.example.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {
    private String nomeFunc;
    private String reFunc;
    private String setorFunc;
    private String cargoFunc;
    private String telefoneFunc;
    private String emailFunc;
    private String senhaFunc;
}
