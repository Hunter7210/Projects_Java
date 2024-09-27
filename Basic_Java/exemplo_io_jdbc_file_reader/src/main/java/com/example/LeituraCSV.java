package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeituraCSV {
    String arquivoCSV = "dados.csv";
    String linha = "";
    String divisor = ","; // O divisor usado no CSV, neste caso é a vírgula

    public void teste() {
        // Para abrir o try_catch primeiro ele estabelece conexão com o arquivo
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {

            //Lendo e imprimindo o cabeçaçalho (primeira linha)
            if ((linha = br.readLine())!= null) {
                String[] colunas = linha.split(divisor);
                System.out.println("Cabeçalho");
                for (String coluna : colunas) {
                    System.out.println(coluna + "");
                }
                System.out.println("\n---------------------");
            }
            
            //Lendo e imprimindo o conteudo do arquivo
            System.out.println("Conteudo");
            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(divisor);
                for (String valor : valores) {
                    System.out.println(valor + " ");
                }
                System.out.println();
            }
        

        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
