package com.meowlomo.atm.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.notification.model.QueuedEmail;
import com.meowlomo.atm.notification.service.EmailClientService;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class NotificationSender {
    static final Logger logger = LoggerFactory.getLogger(NotificationSender.class);

    @Autowired
    private EmailClientService emailClientService;

    @Scheduled(initialDelay = 120 * 1000, fixedDelay = 30000)
    public void sendOutEmils() {
        try {
            logger.debug("===> ********** ===> start sending email");
            List<QueuedEmail> sendJobResult = emailClientService.sendQueuedEmail();
            if (!sendJobResult.isEmpty()) {
                logger.debug(String.format("Sent [%d] jobs to EMS", sendJobResult.size()));
                for (QueuedEmail sentEmail : sendJobResult) {
                    logger.debug(String.format("Job [%s] has been sent to EMS", sentEmail.getSubject()));
                }
            }
            logger.debug("===> ********** ===> end sending email");
        } catch (Exception e) {
            logger.error("scheculer sendOutEmils error", e);
        }
    }

}
