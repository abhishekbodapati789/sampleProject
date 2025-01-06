package com.DailyTasks.core.Config;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
        service = EmailService.class,
        property = {
                Constants.SERVICE_DESCRIPTION + "=Email Service"
        }
)
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Reference
    private MessageGatewayService messageGatewayService;

    @Override
    public void sendEmail(final String toEmail, final String ccEmail, final String fromEmail, final String subject, final String content) {
        try {
            final Email email = new SimpleEmail();
            email.setSubject(subject);
            email.setMsg(content);
            email.addTo(toEmail);
            email.addCc(ccEmail);
            email.setFrom(fromEmail);
            final MessageGateway<Email> messageGateway = messageGatewayService.getGateway(Email.class);
            if (messageGateway != null) {
                messageGateway.send(email);
            } else {
                LOGGER.error("MessageGateway is null, unable to send email");
            }
        } catch (final EmailException e) {
            LOGGER.error("Failed to send email: {}", e.getMessage(), e);
        }
    }
}
