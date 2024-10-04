package com.example.Views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class LoginFunc extends JFrame {
    public LoginFunc() {
        setTitle("Próxima Tela");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Bem-vindo à próxima tela!", JLabel.CENTER);
        add(label, BorderLayout.CENTER);
    }
}
