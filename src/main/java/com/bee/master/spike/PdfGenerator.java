package com.bee.master.spike;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PdfGenerator {
    public static byte[] generate() throws Exception {

        Document document = new Document(PageSize.A4.rotate(), 25, 25, 30, 30);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);

        document.open();
        setText(writer, "Winfield Feng", 420, 370);
        setText(writer, "Bee Master Course", 420, 240);
        setText(writer, "2020/4/22", 270, 120);

        document.add(background());
        document.close();

        return baos.toByteArray();
    }

    private static Image background() throws URISyntaxException, BadElementException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource("certs/cert.jpeg").toURI());
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        img.scalePercent(50);
        return img;
    }

    private static void setText(PdfWriter writer, String text, int x, int y) throws DocumentException, IOException {
        PdfContentByte cb = writer.getDirectContent();
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        cb.beginText();
        cb.setFontAndSize(bf, 36);
        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, text, x, y, 0);
        cb.endText();
    }
}

