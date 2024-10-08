package com.example.Controllers;

import com.example.Models.Equipamento;
import com.example.Models.Manutencao;
import com.example.Models.QrCode;
import com.example.Models.Sensor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import javax.swing.*;
import java.io.IOException;

public class GerarRelatorioPDF {

    // Método para gerar o PDF, recebendo um único equipamento
    public static void gerarPDF(Equipamento equipamento) {
        // Cria um novo documento
        try (PDDocument document = new PDDocument()) {
            // Adiciona uma página ao documento
            PDPage page = new PDPage();
            document.addPage(page);

            // Prepara o conteúdo da página
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Define a fonte para o título
                  contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12); // Definindo a
                                                                                                     // fonte
                                                                                                     // corretamente
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 750); // Posição inicial

                // Adiciona título do relatório
                contentStream.showText("Relatório de Equipamento");
                contentStream.newLine();
                contentStream.endText();

                // Altera a fonte para o conteúdo
                  contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12); // Definindo a
                                                                                                     // fonte
                                                                                                     // corretamente
                
                // Inicia o bloco de texto para conteúdo
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 720); // Posição inicial do conteúdo

                // Adiciona informações do equipamento ao PDF
                contentStream.showText("Nome do Equipamento: " + equipamento.getNomeEqui());
                contentStream.newLine();
                contentStream.showText("Código do Equipamento: " + equipamento.getCodEquip());
                contentStream.newLine();
                contentStream.showText("Data de Compra: " + equipamento.getDataCompraEquip());
                contentStream.newLine();
                contentStream.showText("Tipo: " + equipamento.getTipoEquip());
                contentStream.newLine();
                contentStream.showText("Fornecedor: " + equipamento.getFornecEquip());
                contentStream.newLine();
                contentStream.showText("Quantidade de Sensores: " + equipamento.getQtdSensorEquip());
                contentStream.newLine();
                contentStream.showText("Status: " + equipamento.getStatusEquip());
                contentStream.newLine();
                contentStream.newLine(); // Espaço extra entre seções

                // Adiciona sensores
                contentStream.showText("Sensores:");
                contentStream.newLine();
                if (equipamento.getSensores() != null && !equipamento.getSensores().isEmpty()) {
                    for (Sensor sensor : equipamento.getSensores()) {
                        contentStream.showText("  - " + sensor.toString());
                        contentStream.newLine();
                    }
                } else {
                    contentStream.showText("  Nenhum sensor registrado");
                    contentStream.newLine();
                }

                contentStream.newLine(); // Espaço extra entre seções

                // Adiciona manutenções
                contentStream.showText("Manutenções:");
                contentStream.newLine();
                if (equipamento.getManutencoes() != null && !equipamento.getManutencoes().isEmpty()) {
                    for (Manutencao manutencao : equipamento.getManutencoes()) {
                        contentStream.showText("  - " + manutencao.toString());
                        contentStream.newLine();
                    }
                } else {
                    contentStream.showText("  Nenhuma manutenção registrada");
                    contentStream.newLine();
                }

                contentStream.newLine(); // Espaço extra entre seções

                // Adiciona QR Codes
                contentStream.showText("QR Codes:");
                contentStream.newLine();
                if (equipamento.getQrcodes() != null && !equipamento.getQrcodes().isEmpty()) {
                    for (QrCode qrcode : equipamento.getQrcodes()) {
                        contentStream.showText("  - " + qrcode.toString());
                        contentStream.newLine();
                    }
                } else {
                    contentStream.showText("  Nenhum QR Code registrado");
                    contentStream.newLine();
                }

                contentStream.endText();
            }

            // Salva o documento em um arquivo
            document.save("relatorio_equipamento.pdf");
            JOptionPane.showMessageDialog(null, "PDF gerado com sucesso!");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
