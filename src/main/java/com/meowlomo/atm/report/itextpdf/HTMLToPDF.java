package com.meowlomo.atm.report.itextpdf;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class HTMLToPDF {
    private final Logger logger = LoggerFactory.getLogger(HTMLToPDF.class);

    public InputStream converteHTMLToPDF(InputStream htmlInputStream) {
        ByteArrayOutputStream out = null;
        ByteArrayInputStream inputStream = null;
        Document document = new Document();
        try {
            out = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new BufferedInputStream(htmlInputStream),
                    Charset.forName("utf-8"), new MyFontProvider());
            document.close();
            inputStream = new ByteArrayInputStream(out.toByteArray());
        }
        catch (DocumentException e) {
            logger.error(e.getMessage(), e);
        }
        catch (IOException e) {
            document.close();
            logger.error(e.getMessage(), e);
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                }
                catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                }
                catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return inputStream;
    }

}
