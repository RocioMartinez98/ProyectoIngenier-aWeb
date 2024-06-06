package com.notepad.project.controllers;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.notepad.project.models.Nota;
import com.notepad.project.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class PDFController {

    private final NotaService notaService;

    @Autowired
    public PDFController(NotaService notaService) {
        this.notaService = notaService;
    }
    @GetMapping("/anotepad/notaSeleccionada/{id}/descargarPDF")
    public void descargarPDF(@PathVariable Long id, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=nota.pdf");



        try {
            Document document = new Document();
            OutputStream out = response.getOutputStream();
            PdfWriter.getInstance(document, out);


            document.open();

            Nota nota = notaService.findById(id);

            if (nota != null) {
                // Fuente del titulo
                BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                Font fontTitulo = new Font(bf, 16, Font.BOLD, Color.MAGENTA);

                // Fuente del texto
                Font fontTexto = new Font(bf, 12);

                // Agregar titulo
                Paragraph title = new Paragraph("Titulo de la nota: " + nota.getTitulo(), fontTitulo);
                title.setAlignment(Paragraph.ALIGN_JUSTIFIED);
                document.add(title);

                //Espacio en blanco para que no quede tan junto
                document.add(new Paragraph(" "));

                // Agregar texto
                Paragraph paragraph = new Paragraph("Texto de la nota: " + nota.getTexto(), fontTexto);
                paragraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);
                document.add(paragraph);
            }

            document.close();
            out.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
