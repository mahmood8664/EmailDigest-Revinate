package com.revinate.emaildigest.digest;

import com.revinate.emaildigest.contact.model.Contact;

/**
 * @author mahmood
 * @since 9/19/21
 */
public interface DigestService {

    void prepareAndSendEmail(Contact contact, TemplateKey templateKey);

}
