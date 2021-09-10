package com.rohraff.netpccontactapp.controller;

import com.rohraff.netpccontactapp.model.Contact;
import com.rohraff.netpccontactapp.services.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping()
    public String addCredentials(Contact contact) {
        contactService.addContact(contact);
        return "redirect:/home";
    }

    @DeleteMapping()
    public String deleteCredential(@ModelAttribute Contact contact) {
        contactService.deleteContact(contact);
        return "redirect:/home";
    }

    @PutMapping()
    public String updateCredentials(@ModelAttribute Contact contact) {
        contactService.updateContact(contact);
        return "redirect:/home";
    }
}
