package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LeituraJDBC {

    public void test() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mongodb://localhost:27017/recipe_book_project", "mongo",
                    "mongo");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");

            while (rs.next()) {
                System.out.println("Nome Receita:" + rs.getString("nomeReceita"));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
