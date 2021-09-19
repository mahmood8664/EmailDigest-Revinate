package com.revinate.emaildigest.digest;

import com.revinate.emaildigest.contact.model.Contact;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author mahmood
 * @since 9/19/21
 */
@Component
public class DigestProducer {

    final KafkaTemplate<String, Contact> kafkaTemplate;

    public DigestProducer(KafkaTemplate<String, Contact> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceDailyDigest(Contact contact) {
        kafkaTemplate.send(DigestConsumer.DAILY_TOPIC, contact);
    }

    public void produceWeeklyDigest(Contact contact) {
        kafkaTemplate.send(DigestConsumer.WEEKLY_TOPIC, contact);
    }
}
