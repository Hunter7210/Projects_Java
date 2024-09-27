package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeituraArquivo {
    String src_file = "C:\\Users\\DevTarde\\Documents\\Diogo\\Tarde\\JavaProjectsTarde\\dados.txt";

    public void teste() {
        try (BufferedReader br = new BufferedReader(
                new FileReader(
                        src_file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}