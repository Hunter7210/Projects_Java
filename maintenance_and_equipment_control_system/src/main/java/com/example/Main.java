package com.example;

import javax.swing.SwingUtilities;

import com.example.Views.CadastroEquipamento;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CadastroEquipamento::new);
    }
}
