package com.revinate.emaildigest.scheduler;

import com.revinate.emaildigest.contact.model.Contact;
import com.revinate.emaildigest.contact.service.ContactService;
import com.revinate.emaildigest.digest.DigestService;
import com.revinate.emaildigest.lock.LockProvider;
import com.revinate.emaildigest.digest.TemplateKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class DigestsSchedulerByKafka {

    private final ContactService contactService;
    private final DigestService digestServiceKafkaImpl;

    @Scheduled(cron = "0 0 7 * * *")
    public void sendDailyDigests() {
        LockProvider.doWithLock("daily-emails", () -> {
            Iterator<List<Contact>> paginatedContacts = contactService.getPaginatedContacts(100);
            paginatedContacts.forEachRemaining(contacts -> contacts.forEach(contact ->
                    digestServiceKafkaImpl.prepareAndSendEmail(contact, TemplateKey.DAILY_EMAIL)));
        });
    }

    @Scheduled(cron = "0 0 7 * * 1")
    public void sendWeeklyDigests() {
        LockProvider.doWithLock("weekly-emails", () -> {
            Iterator<List<Contact>> paginatedContacts = contactService.getPaginatedContacts(100);
            paginatedContacts.forEachRemaining(contacts -> contacts.forEach(contact ->
                    digestServiceKafkaImpl.prepareAndSendEmail(contact, TemplateKey.WEEKLY_EMAIL)));
        });
    }

}
