package com.revinate.emaildigest.digest;

import com.revinate.emaildigest.deal.model.Deal;
import com.revinate.emaildigest.email.model.Email;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mahmood
 * @since 9/19/21
 */
@ExtendWith(MockitoExtension.class)
public class TemplateManagerTest {

    @Spy
    private TemplateManger templateManger;

    @Test
    void prepareDailyEmailTestShouldReturnValidEmail() {

        Email email = templateManger.prepareEmail(TemplateKey.DAILY_EMAIL, new EmailContext()
                .setContactFirstName("name")
                .setDeals(List.of(
                        new Deal().setName("1").setOriginalPrice(11).setDescription("111").setPercentDiscount(99),
                        new Deal().setName("2").setOriginalPrice(22).setDescription("222").setPercentDiscount(90)
                ))
        );

        assertEquals(new Email()
                .setSubject("Your deals of the day - Don't miss out!")
                .setBody("Hi name,\n" +
                        "\n" +
                        "Today again! Your amazing deals:\n" +
                        "\n" +
                        "- 1 - 99 off on 11\n" +
                        "  111\n" +
                        "- 2 - 90 off on 22\n" +
                        "  222\n"), email);

    }

    @Test
    void prepareWeeklyEmailTestShouldReturnValidEmail() {

        Email email = templateManger.prepareEmail(TemplateKey.WEEKLY_EMAIL, new EmailContext()
                .setContactFirstName("name")
                .setDeals(List.of(
                        new Deal().setName("1").setOriginalPrice(11).setDescription("111").setPercentDiscount(99),
                        new Deal().setName("2").setOriginalPrice(22).setDescription("222").setPercentDiscount(90)
                ))
        );

        assertEquals(new Email()
                .setSubject("Your deals of the week - Get Ready!")
                .setBody("Hi name,\n" +
                        "\n" +
                        "Today again! Your amazing deals:\n" +
                        "\n" +
                        "- 1 - 99 off on 11\n" +
                        "  111\n" +
                        "- 2 - 90 off on 22\n" +
                        "  222\n"), email);

    }

}
