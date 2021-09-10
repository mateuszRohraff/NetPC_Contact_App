package com.rohraff.netpccontactapp.services;

import com.rohraff.netpccontactapp.model.Contact;
import com.rohraff.netpccontactapp.model.ContactDao;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ContactDaoService {

    public ContactDao getContactDao(Contact contact) {
        ContactDao contactDao = new ContactDao();
        contactDao.setContactId(contact.getContactId());
        contactDao.setFirstname(contact.getFirstname());
        contactDao.setLastname(contact.getLastname());
        contactDao.setEmail(contact.getEmail());
        contactDao.setPhoneNumber(contact.getPhoneNumber());
        contactDao.setKey(contact.getKey());
        contactDao.setPassword(contact.getPassword());
        contactDao.setDateOfBirth(LocalDate.parse(contact.getDateOfBirth()));
        return contactDao;
    }
}
