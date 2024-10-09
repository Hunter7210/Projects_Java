package com.example.Views;

import javax.swing.*;
import org.bson.Document;
import com.example.Controllers.ManutencaoController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ListaManutencaoScreen extends JFrame {

    private ManutencaoController mc = new ManutencaoController();
    private String codEquip;
    private String idManuUnic; // Variável de instância
    private JPanel currentCard; // Variável para armazenar o card atual
    private JPanel listPanel; // Variável de instância para o painel de lista

    public ListaManutencaoScreen(String codEquipamento) {
        this.codEquip = codEquipamento;
        setTitle("Lista de Manutenções - " + codEquipamento);
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha esta tela
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Criando a Navbar
        JPanel navbar = new JPanel();
        navbar.setBackground(new Color(70, 130, 180)); // Cor da navbar
        navbar.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel titleLabel = new JLabel("Lista de Manutenções");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        navbar.add(titleLabel);
        mainPanel.add(navbar, BorderLayout.NORTH);

        // Obtém a lista de manutenções
        List<Document> manutencoes = mc.getManutencoes(codEquipamento); // Método que retorna a lista de manutenções

        // Painel para exibir as manutenções
        listPanel = new JPanel();  // Definido como variável de instância
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento

        // Cria cards para cada manutenção
        for (Document manutencao : manutencoes) {
            if (manutencao != null) {
                JPanel card = new JPanel();
                card.setLayout(new GridLayout(7, 1)); // Usando GridLayout para organizar a exibição
                card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                card.setPreferredSize(new Dimension(550, 140)); // Aumentado para acomodar mais informações
                card.setBackground(Color.WHITE);

                // Extrai informações da manutenção
                String idManut = manutencao.getString("idManut");
                String tipoManut = manutencao.getString("tipoManut");
                String statusManut = manutencao.getString("statusManut");
                String dataIniManut = manutencao.getString("dataIniManut");
                String dataFimManut = manutencao.getString("dataFimManut");
                String dataPrevisIniManut = manutencao.getString("dataPrevisIniManut");
                String dataPrevisFimManut = manutencao.getString("dataPrevisFimManut");

                // Cria JLabels para cada informação
                JLabel idLabel = new JLabel("ID Manutenção: " + idManut);
                JLabel tipoLabel = new JLabel("Tipo de Manutenção: " + tipoManut);
                JLabel statusLabel = new JLabel("Status: " + statusManut);
                JLabel dataIniLabel = new JLabel("Data Inicial: " + dataIniManut);
                JLabel dataFimLabel = new JLabel("Data Final: " + dataFimManut);
                JLabel dataPrevisIniLabel = new JLabel("Data Previsão Inicial: " + dataPrevisIniManut);
                JLabel dataPrevisFimLabel = new JLabel("Data Previsão Final: " + dataPrevisFimManut);

                // Adiciona os JLabels ao card
                card.add(idLabel);
                card.add(tipoLabel);
                card.add(statusLabel);
                card.add(dataIniLabel);
                card.add(dataFimLabel);
                card.add(dataPrevisIniLabel);
                card.add(dataPrevisFimLabel);

                // Adiciona um MouseListener ao card
                card.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        idManuUnic = idManut; // Armazena o ID da manutenção clicada

                        // Restaura a cor do card atual, se existir
                        if (currentCard != null) {
                            currentCard.setBackground(Color.WHITE); // Restaura a cor original do card atual
                        }

                        // Define o novo card como o card atual
                        currentCard = card;
                        card.setBackground(new Color(0, 102, 204)); // Azul escuro ao clicar
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        // Muda a cor ao passar o mouse, se não for o card atual
                        if (card != currentCard) {
                            card.setBackground(new Color(230, 230, 250)); // Cor ao passar o mouse
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // Restaura a cor original se não for o card atual
                        if (card != currentCard) {
                            card.setBackground(Color.WHITE); // Restaura a cor original
                        }
                    }
                });

                listPanel.add(card);
                listPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre os cards
            }
        }

        // Adiciona a lista de manutenções em um JScrollPane
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Barra de rolagem
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Botões de ação
        JButton btnCadastrar = new JButton("Cadastrar Manutenção");
        JButton btnAtualizar = new JButton("Atualizar Manutenção");
        JButton btnRemover = new JButton("Remover Manutenção");
        JButton btnVoltar = new JButton("Voltar");

        // Adicionando ActionListeners aos botões
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroManutencao().adicionarManutencao(codEquipamento);
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idManuUnic != null) {
                    update(idManuUnic,codEquipamento);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma manutenção para atualizar.");
                }
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idManuUnic != null) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esta manutenção?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        delete(idManuUnic, codEquipamento);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma manutenção para remover.");
                }
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela atual
            }
        });

        // Adicionando os botões ao painel
        buttonPanel.add(btnCadastrar);
        buttonPanel.add(btnAtualizar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnVoltar);

        // Adiciona o painel de botões ao painel principal
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adiciona o painel principal à JFrame
        add(mainPanel);
        setVisible(true);
    }

    public void update(String idManut, String idEquip) {
        String novoTipo = JOptionPane.showInputDialog("Digite o novo tipo de manutenção:");
        if (novoTipo != null && !novoTipo.trim().isEmpty()) {
            mc.updateStatusManutencao(idEquip, idManut, novoTipo);
            JOptionPane.showMessageDialog(null, "Manutenção atualizada com sucesso.");
            dispose(); // Fecha a tela atual
            new ListaManutencaoScreen(idEquip); // Reabre a tela para exibir a atualização
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum valor foi inserido.");
        }
    }

    public void delete(String idManut, String idEquip) {
        mc.deleteManutencao(codEquip,idManut);
        JOptionPane.showMessageDialog(null, "Manutenção removida com sucesso.");
        dispose(); // Fecha a tela atual
        new ListaManutencaoScreen(idEquip); // Reabre a tela para exibir a atualização
    }

    public void atualizarListaManutencao() {
        // Atualiza a lista de manutenções após adicionar, remover ou atualizar uma manutenção
        listPanel.removeAll(); // Remove todos os componentes do listPanel

        // Obtém a lista de manutenções atualizada
        List<Document> manutencoes = mc.getManutencoes(codEquip);
        for (Document manutencao : manutencoes) {
            JPanel card = new JPanel();
            card.setLayout(new GridLayout(7, 1));
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            card.setPreferredSize(new Dimension(550, 140));
            card.setBackground(Color.WHITE);

            String idManut = manutencao.getString("idManut");
            String tipoManut = manutencao.getString("tipoManut");
            String statusManut = manutencao.getString("statusManut");
            String dataIniManut = manutencao.getString("dataIniManut");
            String dataFimManut = manutencao.getString("dataFimManut");
            String dataPrevisIniManut = manutencao.getString("dataPrevisIniManut");
            String dataPrevisFimManut = manutencao.getString("dataPrevisFimManut");

            JLabel idLabel = new JLabel("ID Manutenção: " + idManut);
            JLabel tipoLabel = new JLabel("Tipo de Manutenção: " + tipoManut);
            JLabel statusLabel = new JLabel("Status: " + statusManut);
            JLabel dataIniLabel = new JLabel("Data Inicial: " + dataIniManut);
            JLabel dataFimLabel = new JLabel("Data Final: " + dataFimManut);
            JLabel dataPrevisIniLabel = new JLabel("Data Previsão Inicial: " + dataPrevisIniManut);
            JLabel dataPrevisFimLabel = new JLabel("Data Previsão Final: " + dataPrevisFimManut);

            card.add(idLabel);
            card.add(tipoLabel);
            card.add(statusLabel);
            card.add(dataIniLabel);
            card.add(dataFimLabel);
            card.add(dataPrevisIniLabel);
            card.add(dataPrevisFimLabel);

            listPanel.add(card);
            listPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        listPanel.revalidate(); // Atualiza o painel
        listPanel.repaint();    // Re-renderiza o painel
    }
}
