package com.revinate.emaildigest.scheduler;

import com.revinate.emaildigest.email.model.Email;
import com.revinate.emaildigest.email.service.EmailService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author mahmood
 * @since 9/19/21
 */
@SpringBootTest
public class DigestsSchedulerTest {

    @Autowired
    private DigestsScheduler digestsScheduler;
    @MockBean
    private EmailService emailService;

    @Test
    void testSchedulerDaily() {
        digestsScheduler.sendDailyDigests();
        Mockito.verify(emailService, Mockito.times(12345)).sendEmail(any(Email.class), any(String.class));
    }

    @Test
    void testSchedulerWeekly() {
        digestsScheduler.sendWeeklyDigests();
        Mockito.verify(emailService, Mockito.times(12345)).sendEmail(any(Email.class), any(String.class));
    }

}
