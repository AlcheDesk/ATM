package com.meowlomo.atm.core.model.custom;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "XmlEnvolop")

public class XmlEnvolop implements Serializable {

    private static final long serialVersionUID = 1L;

    private String action;
    private String xml;
    private String xsd;
    private String dtd;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getXsd() {
        return xsd;
    }

    public void setXsd(String xsd) {
        this.xsd = xsd;
    }

    public String getDtd() {
        return dtd;
    }

    public void setDtd(String dtd) {
        this.dtd = dtd;
    }
}
