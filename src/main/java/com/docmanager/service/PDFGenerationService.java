package com.docmanager.service;
import com.docmanager.dto.apis.APIServiceDTO;
import com.docmanager.dto.apis.EndpointDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PDFGenerationService {
    public byte[] generateApiDocumentation(APIServiceDTO apiService) throws DocumentException {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();
            addMetadata(document, apiService);
            addTitle(document, apiService);
            addServiceInfo(document, apiService);
            addInfrastructureInfo(document, apiService);
            addDataSourcesInfo(document, apiService);
            addConsumerInfo(document, apiService);
            addEndpointsInfo(document, apiService);
            document.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new DocumentException("Failed to generate PDF: " + e.getMessage());
        }
    }
    private void addMetadata(Document document, APIServiceDTO apiService) {
        document.addTitle(apiService.getName() + " - API Documentation");
        document.addSubject("Technical Documentation for " + apiService.getTrigramme());
        document.addKeywords("API, Documentation, " + apiService.getTrigramme());
        document.addCreator("Doc Manager System");
    }
    private void addTitle(Document document, APIServiceDTO apiService) throws DocumentException {
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);
        Paragraph title = new Paragraph(apiService.getName() + " (" + apiService.getTrigramme() + ")", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // Add generation date
        Font dateFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC, BaseColor.GRAY);
        Paragraph date = new Paragraph("Documentation generated on: " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), dateFont);
        date.setAlignment(Element.ALIGN_RIGHT);
        date.setSpacingAfter(20);
        document.add(date);
    }
    private void addServiceInfo(Document document, APIServiceDTO apiService) throws DocumentException {
        Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.DARK_GRAY);
        Paragraph sectionTitle = new Paragraph("Service Overview", sectionFont);
        sectionTitle.setSpacingBefore(15);
        sectionTitle.setSpacingAfter(10);
        document.add(sectionTitle);

        // Description
        Font contentFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.BLACK);
        Font labelFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.DARK_GRAY);

        Paragraph description = new Paragraph();
        description.add(new Chunk("Description: ", labelFont));
        description.add(new Chunk(apiService.getDescription() != null ? apiService.getDescription() : "N/A", contentFont));
        description.setSpacingAfter(10);
        document.add(description);

        // Database schema
        Paragraph schema = new Paragraph();
        schema.add(new Chunk("Database Schema: ", labelFont));
        schema.add(new Chunk(apiService.getDatabaseSchema() != null ? apiService.getDatabaseSchema() : "N/A", contentFont));
        schema.setSpacingAfter(15);
        document.add(schema);

        document.add(Chunk.NEWLINE);
    }
    private void addInfrastructureInfo(Document document, APIServiceDTO apiService) throws DocumentException {
        Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.DARK_GRAY);
        Paragraph sectionTitle = new Paragraph("Infrastructure", sectionFont);
        sectionTitle.setSpacingBefore(15);
        sectionTitle.setSpacingAfter(10);
        document.add(sectionTitle);

        if (apiService.getInfrastructure() != null) {
            Font contentFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.BLACK);
            Font labelFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.DARK_GRAY);

            // Create a table for infrastructure information
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            addTableRow(table, "INT Environment", apiService.getInfrastructure().getUrlInt(), labelFont, contentFont);
            addTableRow(table, "UAT Environment", apiService.getInfrastructure().getUrlUat(), labelFont, contentFont);
            addTableRow(table, "OAT Environment", apiService.getInfrastructure().getUrlOat(), labelFont, contentFont);
            addTableRow(table, "PROD Environment", apiService.getInfrastructure().getUrlProd(), labelFont, contentFont);

            document.add(table);
        } else {
            document.add(new Paragraph("No infrastructure information available."));
        }

        document.add(Chunk.NEWLINE);
    }
    private void addDataSourcesInfo(Document document, APIServiceDTO apiService) throws DocumentException {
        Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.DARK_GRAY);
        Paragraph sectionTitle = new Paragraph("Data Sources", sectionFont);
        sectionTitle.setSpacingBefore(15);
        sectionTitle.setSpacingAfter(10);
        document.add(sectionTitle);

        if (apiService.getDataSources() != null) {
            Font contentFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.BLACK);

            // Create a table for data sources
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            PdfPCell headerCell = new PdfPCell(new Phrase("Data Source", new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
            headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(headerCell);

            headerCell = new PdfPCell(new Phrase("Used", new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));
            headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(headerCell);

            table.addCell("RabbitMQ");
            table.addCell(apiService.getDataSources().isRabbitMQ() ? "Yes" : "No");

            table.addCell("Common Database");
            table.addCell(apiService.getDataSources().isCommonDB() ? "Yes" : "No");

            table.addCell("Dedicated Database");
            table.addCell(apiService.getDataSources().isDedicatedDB() ? "Yes" : "No");

            table.addCell("S3 Storage");
            table.addCell(apiService.getDataSources().isS3() ? "Yes" : "No");

            document.add(table);
        } else {
            document.add(new Paragraph("No data sources information available."));
        }

        document.add(Chunk.NEWLINE);
    }
    private void addConsumerInfo(Document document, APIServiceDTO apiService) throws DocumentException {
        Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.DARK_GRAY);

        // Client Consumers
        Paragraph clientTitle = new Paragraph("Client Consumers", sectionFont);
        clientTitle.setSpacingBefore(15);
        clientTitle.setSpacingAfter(10);
        document.add(clientTitle);

        if (apiService.getClientConsumers() != null && !apiService.getClientConsumers().isEmpty()) {
            addListToDocument(document, apiService.getClientConsumers());
        } else {
            document.add(new Paragraph("No client consumers."));
        }

        // Consumes
        Paragraph consumesTitle = new Paragraph("Services Consumed", sectionFont);
        consumesTitle.setSpacingBefore(15);
        consumesTitle.setSpacingAfter(10);
        document.add(consumesTitle);

        if (apiService.getConsumes() != null && !apiService.getConsumes().isEmpty()) {
            addListToDocument(document, apiService.getConsumes());
        } else {
            document.add(new Paragraph("No services consumed."));
        }

        // Consumed By
        Paragraph consumedByTitle = new Paragraph("Consumed By", sectionFont);
        consumedByTitle.setSpacingBefore(15);
        consumedByTitle.setSpacingAfter(10);
        document.add(consumedByTitle);

        if (apiService.getConsumedBy() != null && !apiService.getConsumedBy().isEmpty()) {
            addListToDocument(document, apiService.getConsumedBy());
        } else {
            document.add(new Paragraph("Not consumed by any services."));
        }

        document.add(Chunk.NEWLINE);
    }
    private void addEndpointsInfo(Document document, APIServiceDTO apiService) throws DocumentException {
        Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.DARK_GRAY);
        Paragraph sectionTitle = new Paragraph("API Endpoints", sectionFont);
        sectionTitle.setSpacingBefore(15);
        sectionTitle.setSpacingAfter(10);
        document.add(sectionTitle);

        if (apiService.getEndpoints() != null && !apiService.getEndpoints().isEmpty()) {
            for (EndpointDTO endpoint : apiService.getEndpoints()) {
                addEndpointDetails(document, endpoint);
            }
        } else {
            document.add(new Paragraph("No endpoints available."));
        }
    }
    private void addEndpointDetails(Document document, EndpointDTO endpoint) throws DocumentException {
        Font methodFont = new Font(Font.FontFamily.COURIER, 11, Font.BOLD);
        if ("GET".equalsIgnoreCase(endpoint.getMethod())) {
            methodFont.setColor(BaseColor.GREEN);
        } else if ("POST".equalsIgnoreCase(endpoint.getMethod())) {
            methodFont.setColor(BaseColor.BLUE);
        } else if ("PUT".equalsIgnoreCase(endpoint.getMethod())) {
            methodFont.setColor(BaseColor.ORANGE);
        } else if ("DELETE".equalsIgnoreCase(endpoint.getMethod())) {
            methodFont.setColor(BaseColor.RED);
        }

        Font pathFont = new Font(Font.FontFamily.COURIER, 11, Font.NORMAL);
        Paragraph endpointHeader = new Paragraph();
        endpointHeader.add(new Chunk(endpoint.getMethod(), methodFont));
        endpointHeader.add(new Chunk(" " + endpoint.getPath(), pathFont));
        endpointHeader.setSpacingBefore(10);
        endpointHeader.setSpacingAfter(5);
        document.add(endpointHeader);

        // Description
        if (endpoint.getDescription() != null && !endpoint.getDescription().isEmpty()) {
            Font descFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC);
            Paragraph description = new Paragraph(endpoint.getDescription(), descFont);
            description.setSpacingAfter(5);
            document.add(description);
        }

        // cURL command in a box
        if (endpoint.getCurl() != null && !endpoint.getCurl().isEmpty()) {
            PdfPTable curlTable = new PdfPTable(1);
            curlTable.setWidthPercentage(100);

            Font curlFont = new Font(Font.FontFamily.COURIER, 9);
            PdfPCell curlCell = new PdfPCell(new Phrase(endpoint.getCurl(), curlFont));
            curlCell.setBackgroundColor(new BaseColor(240, 240, 240));
            curlCell.setPadding(5);
            curlTable.addCell(curlCell);
            document.add(curlTable);
        }

        // Expected response
        if (endpoint.getExpectedResponse() != null && !endpoint.getExpectedResponse().isEmpty()) {
            document.add(new Paragraph("Expected Response:", new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));

            PdfPTable responseTable = new PdfPTable(1);
            responseTable.setWidthPercentage(100);

            Font responseFont = new Font(Font.FontFamily.COURIER, 9);
            PdfPCell responseCell = new PdfPCell(new Phrase(endpoint.getExpectedResponse(), responseFont));
            responseCell.setBackgroundColor(new BaseColor(240, 240, 240));
            responseCell.setPadding(5);
            responseTable.addCell(responseCell);
            document.add(responseTable);
        }

        document.add(Chunk.NEWLINE);
    }
    private void addTableRow(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
        labelCell.setBorder(Rectangle.NO_BORDER);
        labelCell.setPadding(5);
        table.addCell(labelCell);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
        valueCell.setBorder(Rectangle.NO_BORDER);
        valueCell.setPadding(5);
        table.addCell(valueCell);
    }
    private void addListToDocument(Document document, List<String> items) throws DocumentException {
        com.itextpdf.text.List list = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
        list.setIndentationLeft(30);

        for (String item : items) {
            list.add(new ListItem(item));
        }

        document.add(list);
    }
}