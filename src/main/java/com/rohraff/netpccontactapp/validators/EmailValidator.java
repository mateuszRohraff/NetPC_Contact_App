package com.rohraff.netpccontactapp.validators;

import com.rohraff.netpccontactapp.mapper.ContactMapper;
import com.rohraff.netpccontactapp.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailValidator {

    ContactMapper contactMapper;

    public EmailValidator(ContactMapper contactMapper) {
        this.contactMapper = contactMapper;
    }

    public boolean validateEmail(Contact contact, boolean isFirstRecord) {
        //sprawdzamy przypadek, gdy aktualizujemy inne dane formularza, ale mail pozostaje bez zmian
        if(!isFirstRecord) {
            String previousEmail = contactMapper.getEmail(contact.getContactId());
            if (contact.getEmail().equals(previousEmail)) {
                return true;
            }
        }

        //sprawdzamy czy podany w formularzu mail nie występuje już w bazie
        List<String> emails = contactMapper.getAllEmailAddresses();
        return emails.stream()
                .noneMatch(x -> x.equals(contact.getEmail()));
    }
}
