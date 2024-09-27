package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LeituraJDBC {

    public void test() {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/myDB", "postgres",
                    "postgres");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");

            while (rs.next()) {
                System.out.println("id: " + rs.getInt("id") + ", Nome:" + rs.getString("nome"));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
