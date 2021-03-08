package com.meowlomo.atm.notification.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import com.meowlomo.atm.notification.model.QueuedEmail;

public interface EmailClientService {
    void sendMail(InternetAddress[] recipients, String subject, String templateName, Map<String, Object> templateDate, String messages, boolean html, Map<String, Entry<String, String>> inlines, Map<String, Entry<String, String>> attachments) throws MessagingException ;
    void sendMail(InternetAddress[] recipients, String subject,String messages, boolean html, Map<String, Entry<String, String>> inlines, Map<String, Entry<String, String>> attachments) throws MessagingException ;
    void sendMail(InternetAddress[] recipients, String subject,String messages, boolean html, Map<String, Entry<String, String>> attachments) throws MessagingException ;
    void sendMail(InternetAddress[] recipients, String subject,String messages, boolean html)  throws MessagingException ;
    void sendMail(InternetAddress[] recipients, String subject,String messages) throws MessagingException ;
    
    List<QueuedEmail> sendQueuedEmail();
}
