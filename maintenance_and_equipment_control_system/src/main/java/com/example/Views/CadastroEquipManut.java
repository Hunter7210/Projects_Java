package com.example.Views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import org.bson.Document;
import com.example.Models.EmpresaManu;

public class CadastroEquipManut {

    private JFrame frame;
    private JPanel painelCadastro;

    // Campos para a empresa
    private JTextField campoCNPJ;
    private JTextField campoNomeEmpresa;
    private JTextField campoEndereco;
    private JTextField campoTelefone;
    private JTextField campoCidade;
    private JTextField campoEmail;

    private List<EmpresaManu> empresas = new ArrayList<>(); // Lista de empresas usando o modelo EmpresaManu
    private List<Document> empresasManut = new ArrayList<>(); // Lista de empresas para persistência

    public List<Document> prepararInterface() {
        // Configuração do JFrame
        frame = new JFrame("Cadastro de Equipamentos para Manutenção");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        // Painel para cadastro de empresas
        painelCadastro = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Campo para CNPJ
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCadastro.add(new JLabel("CNPJ da Empresa:"), gbc);
        campoCNPJ = new JTextField(20);
        gbc.gridx = 1;
        painelCadastro.add(campoCNPJ, gbc);

        // Campo para nome da empresa
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelCadastro.add(new JLabel("Nome da Empresa:"), gbc);
        campoNomeEmpresa = new JTextField(20);
        gbc.gridx = 1;
        painelCadastro.add(campoNomeEmpresa, gbc);

        // Campo para endereço
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelCadastro.add(new JLabel("Endereço:"), gbc);
        campoEndereco = new JTextField(20);
        gbc.gridx = 1;
        painelCadastro.add(campoEndereco, gbc);

        // Campo para telefone
        gbc.gridx = 0;
        gbc.gridy = 3;
        painelCadastro.add(new JLabel("Telefone:"), gbc);
        campoTelefone = new JTextField(20);
        gbc.gridx = 1;
        painelCadastro.add(campoTelefone, gbc);

        // Campo para cidade
        gbc.gridx = 0;
        gbc.gridy = 4;
        painelCadastro.add(new JLabel("Cidade:"), gbc);
        campoCidade = new JTextField(20);
        gbc.gridx = 1;
        painelCadastro.add(campoCidade, gbc);

        // Campo para e-mail
        gbc.gridx = 0;
        gbc.gridy = 5;
        painelCadastro.add(new JLabel("E-mail:"), gbc);
        campoEmail = new JTextField(20);
        gbc.gridx = 1;
        painelCadastro.add(campoEmail, gbc);

        // Botão para adicionar empresa
        JButton btnAdicionar = new JButton("Adicionar Empresa");
        gbc.gridx = 1;
        gbc.gridy = 6;
        painelCadastro.add(btnAdicionar, gbc);

        // Botão para finalizar cadastro
        JButton btnFinalizar = new JButton("Finalizar Cadastro");
        gbc.gridy = 8;
        painelCadastro.add(btnFinalizar, gbc);

        // Ação ao clicar no botão "Adicionar Empresa"
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarEmpresa();
            }
        });

        // Ação ao clicar no botão "Finalizar Cadastro"
        btnFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Fecha a janela
            }
        });

        // Adiciona o painel ao frame
        frame.add(painelCadastro, BorderLayout.CENTER);
        frame.setVisible(true);

        // Retorna a lista de documentos (empresasManut) quando finalizar
        return empresasManut;
    }

    private void adicionarEmpresa() {
        // Obtém dados dos campos
        String cnpj = campoCNPJ.getText().trim();
        String nome = campoNomeEmpresa.getText().trim();
        String endereco = campoEndereco.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String cidade = campoCidade.getText().trim();
        String email = campoEmail.getText().trim();

        // Valida os dados
        if (cnpj.isEmpty() || nome.isEmpty() || endereco.isEmpty() || telefone.isEmpty() || cidade.isEmpty()
                || email.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cria uma nova empresa e adiciona à lista
        EmpresaManu novaEmpresa = new EmpresaManu(cnpj, nome, endereco, telefone, cidade, email);
        empresas.add(novaEmpresa);

        // Cria o documento BSON para a empresa e adiciona à lista de documentos
        Document docEmpresa = new Document("cnpj", cnpj)
                .append("nome", nome)
                .append("endereco", endereco)
                .append("telefone", telefone)
                .append("cidade", cidade)
                .append("email", email);
        empresasManut.add(docEmpresa);

        limparCampos(); // Limpa os campos de entrada
    }

    private void limparCampos() {
        campoCNPJ.setText("");
        campoNomeEmpresa.setText("");
        campoEndereco.setText("");
        campoTelefone.setText("");
        campoCidade.setText("");
        campoEmail.setText("");
    }
}
