package com.rohraff.netpccontactapp.services;

import com.rohraff.netpccontactapp.mapper.ContactMapper;
import com.rohraff.netpccontactapp.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private ContactMapper contactMapper;
    private UserService userService;
    private EncryptionService encryptionService;
    private ContactDaoService contactDaoService;

    public ContactService(ContactMapper contactMapper, UserService userService, EncryptionService encryptionService, ContactDaoService contactDaoService) {
        this.contactMapper = contactMapper;
        this.userService = userService;
        this.encryptionService = encryptionService;
        this.contactDaoService = contactDaoService;
    }

    public void addContact(Contact contact) {
        String key = encryptionService.generateKey();
        String encryptedPassword = encryptionService.encryptValue(contact.getPassword(), key);
        contact.setPassword(encryptedPassword);
        contact.setKey(key);
        contactMapper.insertContacts(contactDaoService.getContactDao(contact));
    }

    public List<Contact> getContacts() {
        List<Contact> contacts = contactMapper.getContacts();
        contacts.forEach(System.out::println);
        return contacts;
    }

    public void deleteContact(Contact contact) {
        contactMapper.deleteContact(contact.getContactId());
    }

    public void updateContact(Contact contact) {
        String encryptedPassword = encryptionService.encryptValue(contact.getPassword(), contact.getKey());
        contact.setPassword(encryptedPassword);
        contactMapper.updateContacts(contactDaoService.getContactDao(contact));
    }

}
