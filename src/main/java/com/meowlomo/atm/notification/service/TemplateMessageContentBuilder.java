package com.meowlomo.atm.notification.service;

import java.util.Map;

public interface TemplateMessageContentBuilder {
    String buildMessage(String templateName, Map<String, Object> templateDate);
}
