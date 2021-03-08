package com.meowlomo.atm.util;

import java.io.IOException;

import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

@Component
public class XmlUtil {

    private final Logger logger = LoggerFactory.getLogger(XmlUtil.class);

    public boolean validateWithXSD(InputStream xsdInputStream, InputStream xmlInputStream) throws Exception {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsdInputStream));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlInputStream));
        }
        catch (SAXException | IOException ex) {
            logger.error("Xml is not valided ", ex);
            throw ex;
        }
        return true;
    }

    public boolean validateWithDTDUsingDOM(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);

            DocumentBuilder builder = factory.newDocumentBuilder();

            builder.setErrorHandler(new ErrorHandler() {
                public void warning(SAXParseException e) throws SAXException {
                    logger.info("WARNING : " + e.getMessage()); // do nothing
                }

                public void error(SAXParseException e) throws SAXException {
                    logger.info("ERROR : " + e.getMessage());
                    throw e;
                }

                public void fatalError(SAXParseException e) throws SAXException {
                    logger.info("FATAL : " + e.getMessage());
                    throw e;
                }
            });
            builder.parse(new InputSource(xml));
            return true;
        }
        catch (SAXException ex) {
            return false;
        }
        catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
            return false;
        }
        catch (ParserConfigurationException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
            return false;
        }
    }
}
