package com.rohraff.netpccontactapp.validators;

import com.rohraff.netpccontactapp.mapper.ContactMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailValidator {

    ContactMapper contactMapper;

    public EmailValidator(ContactMapper contactMapper) {
        this.contactMapper = contactMapper;
    }

    public boolean validateEmail(String email) {
        List<String> emails = contactMapper.getAllEmailAddresses();
        return emails.stream()
                .noneMatch(x -> x.equals(email));
    }
}
