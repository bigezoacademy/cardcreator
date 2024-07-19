package com.bigezo.bigezojfx;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CreateCards {

    public byte[] generateCard(String personName, String bgPicUrl,String bgPicUrlPage2, Font font) throws IOException, DocumentException {
        Document document = new Document();
        document.setPageSize(PageSize.A4);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        try {
            // Load the background image from local file path
            Image backgroundImage = Image.getInstance(bgPicUrl);
            backgroundImage.setAbsolutePosition(0f, 0f);
            backgroundImage.scaleToFit(595, 842);
            document.add(backgroundImage);
        } catch (Exception e) {
            System.out.println("----------No Image Found on Page 1-----------");
            e.printStackTrace();
        }

        // Add a new page for the content
        document.newPage();

        try {
            // Add the background image again for the second page
            Image backgroundImagePage2 = Image.getInstance(bgPicUrlPage2);
            backgroundImagePage2.setAbsolutePosition(0f, 0f);
            backgroundImagePage2.scaleToFit(595, 842);
            document.add(backgroundImagePage2);
        } catch (Exception e) {
            System.out.println("----------No Image Found on Page 2-----------");
            e.printStackTrace();
        }

        // Set font and add person name to the second page
        Paragraph name1 = new Paragraph(personName.toUpperCase(), font);
        name1.setAlignment(Paragraph.ALIGN_CENTER);
        name1.setSpacingBefore(245f); // Space before the name
        name1.setSpacingAfter(40f); // Space after the name
        try {
            document.add(name1);
        } catch (Exception e) {
            System.out.println("----------FAILED TO ADD NAME----------");
            e.printStackTrace();
        }

        document.close();
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] generateOneCard(String personName, String bgPicUrl, Font font) throws IOException, DocumentException {
        Document document = new Document();
        document.setPageSize(PageSize.A4);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        try {
            // Load the background image from local file path
            Image backgroundImage = Image.getInstance(bgPicUrl);
            backgroundImage.setAbsolutePosition(0f, 0f);
            backgroundImage.scaleToFit(595, 842);
            document.add(backgroundImage);
        } catch (Exception e) {
            System.out.println("----------No Image Found on Page 1-----------");
            e.printStackTrace();
        }

        // Add a new page for the content


        // Set font and add person name to the second page
        Paragraph name1 = new Paragraph(personName.toUpperCase(), font);
        name1.setAlignment(Paragraph.ALIGN_CENTER);
        name1.setSpacingBefore(245f); // Space before the name
        name1.setSpacingAfter(40f); // Space after the name
        try {
            document.add(name1);
        } catch (Exception e) {
            System.out.println("----------FAILED TO ADD NAME----------");
            e.printStackTrace();
        }

        document.close();
        return byteArrayOutputStream.toByteArray();
    }

}
