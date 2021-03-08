package com.meowlomo.atm.notification.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.internet.InternetAddress;

public class QueuedEmail implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1L;
    private InternetAddress[] recipients;
    private String subject;
    private String templateName;
    private String messages;
    private Map<String, Object> templateDate;
    private Boolean html;
    private Map<String, Entry<String, String>> inlines;
    private Map<String, Entry<String, String>> attachments;

    public InternetAddress[] getRecipients() {
        return recipients;
    }

    public void setRecipients(InternetAddress[] recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public Map<String, Object> getTemplateDate() {
        return templateDate;
    }

    public void setTemplateDate(Map<String, Object> templateDate) {
        this.templateDate = templateDate;
    }

    public Boolean getHtml() {
        return html;
    }

    public void setHtml(Boolean html) {
        this.html = html;
    }

    public Map<String, Entry<String, String>> getInlines() {
        return inlines;
    }

    public void setInlines(Map<String, Entry<String, String>> inlines) {
        this.inlines = inlines;
    }

    public Map<String, Entry<String, String>> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, Entry<String, String>> attachments) {
        this.attachments = attachments;
    }
}
