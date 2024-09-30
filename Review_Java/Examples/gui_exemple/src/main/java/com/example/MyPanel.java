package com.example;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Herda as caracteristicas da classe JFrame
public class MyPanel extends JFrame {

    // Contrutor
    public MyPanel() {
        super("Exemplo Swing");

        // Configs do Frame
        this.setSize(400, 300); // pixels
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Cria um JPanel (painel)
        JPanel panel = new JPanel();

        this.add(panel);
        
        // Adiciona um JButton (botão)
        JButton js = new JButton("Salvar");
        panel.add(js);

        // Adiciona um ActionListener(evento) ao botão
        js.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Botão foi clicado!");
            }
        });

        // Torna a janela visível
        this.setVisible(true);
    }

}
