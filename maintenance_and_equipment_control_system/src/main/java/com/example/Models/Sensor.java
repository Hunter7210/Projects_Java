package com.example.Models;


import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {
    private String nomeSen;
    private String fornecSen;
    private String funSen;
    private List<Acionamento> acionamentos;
}
