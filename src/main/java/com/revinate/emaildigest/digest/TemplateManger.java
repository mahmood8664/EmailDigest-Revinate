package com.revinate.emaildigest.digest;

import com.revinate.emaildigest.email.model.Email;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author mahmood
 * @since 9/18/21
 */
@Component
class TemplateManger {

    private static final Map<TemplateKey, Email> TEMPLATE_TEXTS = Map.of(
            TemplateKey.DAILY_EMAIL, new Email()
                    .setSubject("Your deals of the day - Don't miss out!")
                    .setBody("Hi {{contactFirstName}},\n" +
                            "\n" +
                            "Today again! Your amazing deals:\n" +
                            "\n" +
                            "{{#deals}}" +
                            "- {{name}} - {{percentDiscount}} off on {{originalPrice}}\n" +
                            "  {{description}}\n" +
                            "{{/deals}}"),
            TemplateKey.WEEKLY_EMAIL, new Email()
                    .setSubject("Your deals of the week - Get Ready!")
                    .setBody("Hi {{contactFirstName}},\n" +
                            "\n" +
                            "Check what's coming!\n" +
                            "\n" +
                            "{{#deals}}\n" +
                            "- {{name}} - {{percentDiscount}} off on {{originalPrice}}\n" +
                            "  {{description}}\n" +
                            "{{/deals}}")
    );


    /**
     * This is template store, for large number of template using lazy compilation is recommended!
     */
    private static final Map<TemplateKey, Template> TEMPLATES = Map.of(
            TemplateKey.DAILY_EMAIL, Mustache.compiler().compile(TEMPLATE_TEXTS.get(TemplateKey.DAILY_EMAIL).getBody()),
            TemplateKey.WEEKLY_EMAIL, Mustache.compiler().compile(TEMPLATE_TEXTS.get(TemplateKey.DAILY_EMAIL).getBody()));

    public Email prepareEmail(TemplateKey templateKey, EmailContext emailContext) {
        return new Email()
                .setSubject(TEMPLATE_TEXTS.get(templateKey).getSubject())
                .setBody(TEMPLATES.get(templateKey).execute(emailContext));
    }

}
