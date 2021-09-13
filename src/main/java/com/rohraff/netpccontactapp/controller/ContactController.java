package com.rohraff.netpccontactapp.controller;

import com.rohraff.netpccontactapp.model.Contact;
import com.rohraff.netpccontactapp.services.ContactService;
import com.rohraff.netpccontactapp.validators.EmailValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private ContactService contactService;
    private HomeController homeController;
    private EmailValidator emailValidator;

    public ContactController(ContactService contactService, HomeController homeController, EmailValidator emailValidator) {
        this.contactService = contactService;
        this.homeController = homeController;
        this.emailValidator = emailValidator;
    }

    @PostMapping()
    public String addCredentials(Contact contact) {
        if(emailValidator.validateEmail(contact.getEmail())) {
            contactService.addContact(contact);
            homeController.setMessage("Contact was created!");
        } else {
            homeController.setMessage("This email is already in use! \n" +
                    "Try to use a unique email address");
        }
        return "redirect:/home";
    }

    @DeleteMapping()
    public String deleteCredential(@ModelAttribute Contact contact) {
        homeController.setMessage("Contact was deleted!");
        contactService.deleteContact(contact);
        return "redirect:/home";
    }

    @PutMapping()
    public String updateCredentials(@ModelAttribute Contact contact) {
        if(emailValidator.validateEmail(contact.getEmail())) {
            contactService.updateContact(contact);
            homeController.setMessage("Contact was updated!");
        } else {
            homeController.setMessage("Error, this email was already used!");
        }
        return "redirect:/home";
    }
}
