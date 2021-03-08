package com.meowlomo.atm.notification.service.impl;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.config.RuntimeVariables;
import com.meowlomo.atm.notification.model.QueuedEmail;
import com.meowlomo.atm.notification.service.EmailClientService;
import com.meowlomo.atm.notification.service.TemplateMessageContentBuilder;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class EmailClientServiceImpl implements EmailClientService {

    private final Logger logger = LoggerFactory.getLogger(EmailClientServiceImpl.class);

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private TemplateMessageContentBuilder templateMessageContentBuilder;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.default-encoding}")
    private String charsets;

    @Value("${meowlomo.atm.email.send-queued-mail-size:5}")
    private int SEND_QUEUED_EMAIL_SIZE;

    @Override
    public void sendMail(InternetAddress[] recipients, String subject,
            String templateName, Map<String, Object> templateDate,
            String messages, boolean html,
            Map<String, Entry<String, String>> inlines,
            Map<String, Entry<String, String>> attachments) {
        logger.debug("sending emails to {} with subject {} ", Arrays.toString(recipients), subject);
        if (templateName == null || templateName.isEmpty()
                || templateDate == null) {

            sendMail(recipients, subject, messages, html, attachments);

        } else {
            String templateContent = templateMessageContentBuilder
                    .buildMessage(templateName, templateDate);
            sendMail(recipients, subject, templateContent, html, attachments);
        }

    }

    @Override
    public void sendMail(InternetAddress[] recipients, String subject,
            String messages, boolean html,
            Map<String, Entry<String, String>> inlines,
            Map<String, Entry<String, String>> attachments) {
        logger.debug("sending emails to {} with subject {} ", Arrays.toString(recipients), subject);
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true,
                    charsets);
            helper.setFrom(from);
            helper.setTo(recipients);
            helper.setSubject(subject);
            helper.setText(messages, html);

            // add inline
            if (inlines != null && !inlines.isEmpty()) {
                Set<String> nameSet = inlines.keySet();
                for (String name : nameSet) {
                    Entry<String, String> inline = inlines.get(name);
                    String contentType = inline.getKey();
                    String content = inline.getValue();
                    ByteArrayInputStream contentInputStream = new ByteArrayInputStream(
                            content.getBytes(Charset.forName(charsets)));
                    helper.addInline(name, new InputStreamResource(contentInputStream), contentType);
                }
            }
            sendMail(recipients, subject, messages, html, attachments);
        } catch (Exception e) {
            logger.error("Error on seding email.", e);
        }
    }

    // using
    @Override
    public void sendMail(InternetAddress[] recipients, String subject,
            String messages, boolean html,
            Map<String, Entry<String, String>> attachments) {
        try {
            logger.debug("sending emails to {} with subject {} ", Arrays.toString(recipients), subject);
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true,
                    charsets);
            helper.setFrom(from);
            helper.setTo(recipients);
            helper.setSubject(subject);
            helper.setText(messages, html);

            // check the attachment
            Set<String> nameSet = attachments.keySet();
            for (String name : nameSet) {
                Entry<String, String> attachment = attachments.get(name);
                String contentType = attachment.getKey();
                String content = attachment.getValue();
                byte[] contentInputStream = content
                        .getBytes(Charset.forName(charsets));
                helper.addAttachment(name, new ByteArrayResource(contentInputStream), contentType);
            }

            emailSender.send(message);
        } catch (Exception e) {
            logger.error("Error on seding email.", e);
        }
    }

    @Override
    public void sendMail(InternetAddress[] recipients, String subject,
            String messages, boolean html) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true,
                    charsets);
            helper.setFrom(from);
            helper.setTo(recipients);
            helper.setSubject(subject);
            helper.setText(messages, html);

            if (html) {
                emailSender.send(message);
            } else {
                sendMail(recipients, subject, messages);
            }
        } catch (Exception e) {

            logger.error("Error on seding email.", e);
        }
    }

    @Override
    public void sendMail(InternetAddress[] recipients, String subject,
            String messages) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true,
                    charsets);
            helper.setFrom(from);
            helper.setTo(recipients);
            helper.setSubject(subject);
            helper.setText(messages);
            emailSender.send(message);
        } catch (Exception e) {
            logger.error("Error on seding email.", e);
        }
    }

    @Override
    public List<QueuedEmail> sendQueuedEmail() {
        List<QueuedEmail> emailsForRrocessing = new ArrayList<QueuedEmail>();
        for (int i = 0; i < SEND_QUEUED_EMAIL_SIZE; i++) {
            QueuedEmail email = RuntimeVariables.EMAIL_QUEUE.poll();
            if (email != null) {
                emailsForRrocessing.add(email);
            }
        }

        // check if we need to send the jobs
        if (emailsForRrocessing.isEmpty()) {
            return new ArrayList<QueuedEmail>();
        } else {
            List<QueuedEmail> returnList = new ArrayList<QueuedEmail>();
            // start processing the email
            // check the fields
            for (QueuedEmail email : emailsForRrocessing) {
                if (email.getRecipients() != null && email.getSubject() != null
                        && email.getTemplateName() != null
                        && email.getTemplateDate() != null
                        && email.getMessages() != null
                        && email.getHtml() != null && email.getInlines() != null
                        && email.getAttachments() != null) {
                    try {
                        sendMail(email.getRecipients(), email.getSubject(),
                                email.getTemplateName(),
                                email.getTemplateDate(), email.getMessages(),
                                email.getHtml(), email.getInlines(),
                                email.getAttachments());
                        returnList.add(email);
                    } catch (Exception e) {
                        logger.error("Error on sending email.", e);
                    }

                } else if (email.getRecipients() != null
                        && email.getSubject() != null
                        && email.getMessages() != null
                        && email.getHtml() != null && email.getInlines() != null
                        && email.getAttachments() != null) {
                    try {
                        sendMail(email.getRecipients(), email.getSubject(),
                                email.getMessages(), email.getHtml(),
                                email.getInlines(), email.getAttachments());
                        returnList.add(email);
                    } catch (Exception e) {
                        logger.error("Error on sending email.", e);
                    }
                } else if (email.getRecipients() != null
                        && email.getSubject() != null
                        && email.getMessages() != null
                        && email.getHtml() != null
                        && email.getAttachments() != null) {
                    try {
                        sendMail(email.getRecipients(), email.getSubject(),
                                email.getMessages(), email.getHtml(),
                                email.getAttachments());
                        returnList.add(email);
                    } catch (Exception e) {
                        logger.error("Error on sending email.", e);
                    }
                } else if (email.getRecipients() != null
                        && email.getSubject() != null
                        && email.getMessages() != null
                        && email.getHtml() != null) {
                    try {
                        sendMail(email.getRecipients(), email.getSubject(),
                                email.getMessages(), email.getHtml());
                        returnList.add(email);
                    } catch (Exception e) {
                        logger.error("Error on sending email.", e);
                    }
                } else if (email.getRecipients() != null
                        && email.getSubject() != null
                        && email.getMessages() != null) {
                    try {
                        sendMail(email.getRecipients(), email.getSubject(),
                                email.getMessages());
                        returnList.add(email);
                    } catch (Exception e) {
                        logger.error("Error on sending email.", e);
                    }
                } else {
                    logger.error(
                            "QueuedEmail object mssing required field to be sent "
                                    + email.toString());
                }

            }
            return returnList;
        }
    }

}
