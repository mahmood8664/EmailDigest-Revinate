package com.revinate.emaildigest.digest;

import com.revinate.emaildigest.contact.model.Contact;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author mahmood
 * @since 9/19/21
 */
@Service
@AllArgsConstructor
class DigestServiceKafkaImpl implements DigestService {

    private final DigestProducer producer;

    @Override
    public void prepareAndSendEmail(Contact contact, TemplateKey templateKey) {
        switch (templateKey) {
            case DAILY_EMAIL:
                producer.produceDailyDigest(contact);
                break;
            case WEEKLY_EMAIL:
                producer.produceWeeklyDigest(contact);
                break;
        }

    }

}
