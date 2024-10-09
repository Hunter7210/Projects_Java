package com.example.Views;

import com.example.Controllers.EquipamentoController;
import com.example.Models.EmpresaManu;
import com.example.Models.Manutencao;

import javax.swing.*;
import org.bson.Document;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;  

public class ManutencaoScreen extends JFrame {

    public ManutencaoScreen() {
        setTitle("Lista de Manutenções");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Barra de navegação
        JPanel navbarPanel = new JPanel();
        navbarPanel.setLayout(new BorderLayout());
        navbarPanel.setBackground(new Color(30, 144, 255)); // Cor de fundo da navbar

        JLabel titleLabel = new JLabel("Sistema de Manutenção de Equipamentos", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        navbarPanel.add(titleLabel, BorderLayout.CENTER);

        // Imagem na barra de navegação
        JLabel logoLabel = new JLabel(new ImageIcon("caminho/para/sua/imagem.png")); // Altere para o caminho da sua
                                                                                     // imagem
        navbarPanel.add(logoLabel, BorderLayout.EAST);

        // Barra lateral
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(3, 1)); // 3 botões
        sidebarPanel.setPreferredSize(new Dimension(200, 0));
        sidebarPanel.setBackground(new Color(240, 240, 240));

        JButton btnManutencao = new JButton("Empresas Parceiras");
        JButton btnRelatorios = new JButton("Gerar Relatório");

        sidebarPanel.add(btnManutencao);
        sidebarPanel.add(btnRelatorios);

        // Painel para exibir os dados (cards)
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new GridLayout(0, 1, 10, 10)); // Grid com espaçamento

        // Carregar equipamentos
        EquipamentoController controlador = new EquipamentoController();
        List<Document> documentos = controlador.readTodosEquipamentos();

        // Adiciona cada equipamento com suas manutenções em um card
        for (Document doc : documentos) {
            String nomeEquipamento = doc.getString("nomeEqui");
            String codEquip = doc.getString("codEquip");
            List<Manutencao> manutencoes = extrairManutencoes(doc); // Método para extrair manutenções

            // Criar card para as manutenções do equipamento
            JPanel cardPanel = new JPanel();
            cardPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
            cardPanel.setBackground(Color.WHITE);
            cardPanel.setPreferredSize(new Dimension(0, 100));

            JLabel equipLabel = new JLabel("Equipamento: " + nomeEquipamento);
            equipLabel.setFont(new Font("Arial", Font.BOLD, 16));
            cardPanel.add(equipLabel);

            // Exibe as manutenções
            if (manutencoes.isEmpty()) {
                cardPanel.add(new JLabel("Nenhuma manutenção registrada."));
            } else {
                for (Manutencao manutencao : manutencoes) {
                    String manutencaoInfo = "Tipo: " + manutencao.getTipoManut() +
                            " | Data Início: " + manutencao.getDataIniManut() +
                            " | Data Fim: " + manutencao.getDataFimManut();
                    cardPanel.add(new JLabel(manutencaoInfo));
                }
            }

            // Adiciona um evento de clique no card
            cardPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Abre a tela de detalhes do equipamento
                    new ListaManutencaoScreen(codEquip); // Passa o nome do equipamento
                    dispose(); // Fecha a tela atual (opcional)
                }
            });

            // Adiciona o card ao painel de dados
            dataPanel.add(cardPanel);
        }

        // Adiciona a barra de navegação, a barra lateral e o painel de dados ao painel
        // principal
        mainPanel.add(navbarPanel, BorderLayout.NORTH);
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
        mainPanel.add(new JScrollPane(dataPanel), BorderLayout.CENTER); // Adiciona um scroll se necessário

        // Adiciona o painel principal à JFrame
        add(mainPanel);
    }

    private List<Manutencao> extrairManutencoes(Document doc) {
        List<Manutencao> manutencoes = new ArrayList<>();
        List<Document> listaManutencoes = (List<Document>) doc.get("Manutencao");

        if (listaManutencoes != null) {
            for (Document manutencaoDoc : listaManutencoes) {
                String tipoManut = manutencaoDoc.getString("tipoManut");
                String idManut = manutencaoDoc.getString("idManut");
                String dataManut = manutencaoDoc.getString("dataManut");
                String dataIniManut = manutencaoDoc.getString("dataIniManut");
                String dataFimManut = manutencaoDoc.getString("dataFimManut");
                String dataPrevisFimManut = manutencaoDoc.getString("dataPrevisFimManut");
                String dataPrevisIniManut = manutencaoDoc.getString("dataPrevisIniManut");

                // Extraindo as empresas de manutenção
                List<EmpresaManu> empresaManutencao = extrairEmpresasManutencao(manutencaoDoc);

                // Adiciona a manutenção à lista
                manutencoes.add(new Manutencao(
                        empresaManutencao, // Adiciona a lista de empresas de manutenção
                        idManut,
                        tipoManut,
                        dataManut,
                        dataIniManut,
                        dataFimManut,
                        dataPrevisFimManut,
                        dataPrevisIniManut));
            }
        }
        return manutencoes;
    }

    private List<EmpresaManu> extrairEmpresasManutencao(Document manutencaoDoc) {
        List<EmpresaManu> empresas = new ArrayList<>();
        List<Document> listaEmpresas = (List<Document>) manutencaoDoc.get("empresaManutencao"); // Ajuste o campo
                                                                                                // conforme necessário

        if (listaEmpresas != null) {
            for (Document empresaDoc : listaEmpresas) {
                String cnpjEmpresa = empresaDoc.getString("cnpjEmpresa"); // Ajuste os campos conforme necessário
                String nomeEmpresa = empresaDoc.getString("nomeEmpresa"); // Ajuste os campos conforme necessário
                String enderecoEmpresa = empresaDoc.getString("enderecoEmpresa"); // Ajuste os campos conforme
                                                                                  // necessário
                String telefoneEmpresa = empresaDoc.getString("telefoneEmpresa"); // Ajuste os campos conforme
                                                                                  // necessário
                String cidadeEmpresa = empresaDoc.getString("cidadeEmpresa"); // Ajuste os campos conforme necessário
                String emailEmpresa = empresaDoc.getString("emailEmpresa"); // Ajuste os campos conforme necessário

                empresas.add(new EmpresaManu( // Supondo que você tenha um construtor em EmpresaManu
                        cnpjEmpresa,
                        nomeEmpresa,
                        enderecoEmpresa,
                        telefoneEmpresa,
                        cidadeEmpresa,
                        emailEmpresa));
            }
        }
        return empresas;
    }
}
