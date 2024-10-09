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
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 14);
                contentStream.beginText();
                
                // Define a posição inicial (ajuste o valor Y conforme necessário)
                contentStream.newLineAtOffset(50, 750);

                // Adiciona título do relatório
                contentStream.showText("Relatório de Equipamento");
                contentStream.endText();

                // Alterando a fonte para o conteúdo
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);

                // Nova posição após o título (diminui o valor Y para descer)
                float yPosition = 730;

                // Adiciona informações do equipamento
                yPosition = addTexto(contentStream, "Nome do Equipamento: " + equipamento.getNomeEqui(), 50, yPosition);
                yPosition = addTexto(contentStream, "Código do Equipamento: " + equipamento.getCodEquip(), 50, yPosition);
                yPosition = addTexto(contentStream, "Data de Compra: " + equipamento.getDataCompraEquip(), 50, yPosition);
                yPosition = addTexto(contentStream, "Tipo: " + equipamento.getTipoEquip(), 50, yPosition);
                yPosition = addTexto(contentStream, "Fornecedor: " + equipamento.getFornecEquip(), 50, yPosition);
                yPosition = addTexto(contentStream, "Quantidade de Sensores: " + equipamento.getQtdSensorEquip(), 50, yPosition);
                yPosition = addTexto(contentStream, "Status: " + equipamento.getStatusEquip(), 50, yPosition);
                yPosition -= 20; // Espaçamento extra

                // Adiciona informações de sensores
                yPosition = addTexto(contentStream, "Sensores:", 50, yPosition);
                if (equipamento.getSensores() != null && !equipamento.getSensores().isEmpty()) {
                    for (Sensor sensor : equipamento.getSensores()) {
                        yPosition = addTexto(contentStream, "  Nome: " + sensor.getNomeSen() + ", Tipo: " + sensor.getFornecSen(), 50, yPosition);
                    }
                } else {
                    yPosition = addTexto(contentStream, "  Nenhum sensor registrado", 50, yPosition);
                }
                yPosition -= 20;

                // Adiciona manutenções
                yPosition = addTexto(contentStream, "Manutenções:", 50, yPosition);
                if (equipamento.getManutencoes() != null && !equipamento.getManutencoes().isEmpty()) {
                    for (Manutencao manutencao : equipamento.getManutencoes()) {
                        yPosition = addTexto(contentStream, "  Codigo: " + manutencao.getIdManut() + 
                                                      ", Tipo: " + manutencao.getTipoManut() +
                                                      ", Status: " + manutencao.getStatusManut(), 50, yPosition);
                    }
                } else {
                    yPosition = addTexto(contentStream, "  Nenhuma manutenção registrada", 50, yPosition);
                }
                yPosition -= 20;
            }

            // Salva o documento em um arquivo
            document.save("relatorio_equipamento.pdf");
            JOptionPane.showMessageDialog(null, "PDF gerado com sucesso!");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Função auxiliar para adicionar texto ao PDF e ajustar a posição vertical
    private static float addTexto(PDPageContentStream contentStream, String text, float x, float y) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(text);
        contentStream.endText();
        return y - 15; // Subtrai o valor para descer a linha, ajustando o espaçamento
    }
}
