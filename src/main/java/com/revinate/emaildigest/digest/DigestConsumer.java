package com.revinate.emaildigest.digest;

import com.revinate.emaildigest.contact.model.Contact;
import com.revinate.emaildigest.deal.model.Deal;
import com.revinate.emaildigest.deal.service.DealService;
import com.revinate.emaildigest.email.model.Email;
import com.revinate.emaildigest.email.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mahmood
 * @since 9/19/21
 */
@Component
@AllArgsConstructor
public class DigestConsumer {

    public static final String DAILY_TOPIC = "daily-digest";
    public static final String WEEKLY_TOPIC = "weekly-digest";

    private final DealService dealService;
    private final EmailService emailService;
    private final TemplateManger templateManger;


    // todo: This is pub/sub approach which is not good for this case, we need point to point approach.
    // todo: I know how to implement it in queues like RabbitMQ but not in Kafka!


    @KafkaListener(topics = DAILY_TOPIC, groupId = "123")
    public void dailyConsumer(Contact contact) {

        List<Deal> dailyDealsByEmail = dealService.getDailyDealsByEmail(contact.getEmail());
        if (dailyDealsByEmail.size() != 0) {
            Email email = templateManger.prepareEmail(TemplateKey.DAILY_EMAIL, new EmailContext()
                    .setContactFirstName(contact.getFirstName())
                    .setDeals(dailyDealsByEmail)
            );
            emailService.sendEmail(email, contact.getEmail());
        }
    }

    @KafkaListener(topics = WEEKLY_TOPIC, groupId = "123")
    public void weeklyConsumer(Contact contact) {
        List<Deal> dailyDealsByEmail = dealService.getDailyDealsByEmail(contact.getEmail());
        if (dailyDealsByEmail.size() != 0) {
            Email email = templateManger.prepareEmail(TemplateKey.WEEKLY_EMAIL, new EmailContext()
                    .setContactFirstName(contact.getFirstName())
                    .setDeals(dailyDealsByEmail)
            );
            emailService.sendEmail(email, contact.getEmail());
        }
    }

}
