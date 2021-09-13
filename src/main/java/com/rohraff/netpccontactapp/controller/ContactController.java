package com.rohraff.netpccontactapp.controller;

import com.rohraff.netpccontactapp.model.Contact;
import com.rohraff.netpccontactapp.services.ContactService;
import com.rohraff.netpccontactapp.validators.EmailValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private ContactService contactService;
    private EmailValidator emailValidator;

    public ContactController(ContactService contactService, EmailValidator emailValidator) {
        this.contactService = contactService;
        this.emailValidator = emailValidator;
    }
    //Użycie RedirectAttributes powoduje stworzenie obiektu, który isntiał będzie tylko podczas pierwszego przeładowania strony
    @PostMapping()
    public String addCredentials(Contact contact, RedirectAttributes redirectAttributes) {
        if(emailValidator.validateEmail(contact, true)) {
            contactService.addContact(contact);
            redirectAttributes.addFlashAttribute("message", "Contact was created!");
        } else {
            redirectAttributes.addFlashAttribute("message","This email is already in use! \n" +
                    "Try to use a unique email address");
        }
        return "redirect:/home";
    }

    @DeleteMapping()
    public String deleteCredential(@ModelAttribute Contact contact, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message","Contact was deleted!");
        contactService.deleteContact(contact);
        return "redirect:/home";
    }

    @PutMapping()
    public String updateCredentials(@ModelAttribute Contact contact, RedirectAttributes redirectAttributes) {
        if(emailValidator.validateEmail(contact, false)) {
            contactService.updateContact(contact);
            redirectAttributes.addFlashAttribute("message", "Contact was updated!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Error, this email was already used!");
        }
        return "redirect:/home";
    }
}
