package com.example.Models;

import org.bson.types.ObjectId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {
    private ObjectId id;
    private String nomeFunc;
    private String reFunc;
    private String setorFunc;
    private String cargoFunc;
    private String telefoneFunc;
    private String emailFunc;
    private String senhaFunc;
}
