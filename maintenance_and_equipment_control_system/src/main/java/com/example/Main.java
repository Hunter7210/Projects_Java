package com.example;

import javax.swing.SwingUtilities;

import com.example.Views.HomeScreen;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater( HomeScreen::new);
    }
}
