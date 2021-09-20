package com.revinate.emaildigest.digest;

import com.revinate.emaildigest.contact.model.Contact;
import com.revinate.emaildigest.deal.model.Deal;
import com.revinate.emaildigest.deal.service.DealService;
import com.revinate.emaildigest.email.model.Email;
import com.revinate.emaildigest.email.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mahmood
 * @since 9/19/21
 */
@Service
@AllArgsConstructor
class DigestServiceImpl implements DigestService {
    private final DealService dealService;
    private final EmailService emailService;
    private final TemplateManger templateManger;

    @Override
    public void prepareAndSendEmail(Contact contact, TemplateKey templateKey) {
        List<Deal> deals;
        switch (templateKey) {
            case DAILY_EMAIL:
                deals = dealService.getDailyDealsByEmail(contact.getEmail());
                break;
            case WEEKLY_EMAIL:
                deals = dealService.getWeeklyDealsByEmail(contact.getEmail());
                break;
            default:
                throw new RuntimeException("Invalid TemplateKey");
        }

        if (deals.size() != 0) {
            Email email = templateManger.prepareEmail(templateKey, new EmailContext()
                    .setContactFirstName(contact.getFirstName())
                    .setDeals(deals)
            );
            emailService.sendEmail(email, contact.getEmail());
        }
    }

}
