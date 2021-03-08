package com.meowlomo.atm.typehandler;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.postgresql.util.PGobject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@MappedTypes({ Document.class })
public class XMLTypeHandler extends BaseTypeHandler<Document> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Document parameter, JdbcType jdbcType)
            throws SQLException {
        String xml = this.convertDocumentToString(parameter);
        PGobject jsonObject = new PGobject();
        jsonObject.setType("xml");
        jsonObject.setValue(xml);
        ps.setObject(i, jsonObject);

    }

    private Document read(String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xml)));
            return doc;
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private String convertDocumentToString(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            // below code to remove XML declaration
            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        }
        catch (TransformerException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Document getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String xml = rs.getString(columnName);
        if (xml == null) {
            return null;
        }
        else {
            return read(xml);
        }
    }

    @Override
    public Document getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String xml = rs.getString(columnIndex);
        if (xml == null) {
            return null;
        }
        else {
            return read(xml);
        }
    }

    @Override
    public Document getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String xml = cs.getString(columnIndex);
        if (xml == null) {
            return null;
        }
        else {
            return read(xml);
        }
    }
}
