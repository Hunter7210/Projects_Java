package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LeituraAPI {
    private String src = "https://api.github.com/users/Hunter7210";

    public void test() {
        try {
            // URL da API pública do GitHub para obter informações de um usuário
            URL url = new URL(src);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Verificando se a conexão esta bem sucedida
            int status = con.getResponseCode();
            if (status != 200) {
                throw new RuntimeException("HTTP error code : " + status);
            }

            // Ler a responsta da API
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer content = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();

            // Imprimir o conteudo da resposta
            System.out.println(content.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
