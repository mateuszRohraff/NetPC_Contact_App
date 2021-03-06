package com.rohraff.netpccontactapp.controller;

import com.rohraff.netpccontactapp.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/home")
public class HomeController {

    private ContactService contactService;
    private EncryptionService encryptionService;
    private UserService userService;

    public HomeController(ContactService contactService, EncryptionService encryptionService, UserService userService) {
        this.contactService = contactService;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    @GetMapping
    public String homeView(Model model){
        model.addAttribute("contacts", this.contactService.getContacts());
        model.addAttribute("encryption", encryptionService);
        model.addAttribute("isAuthenticated", this.userService.checkAuthentication());
        return "home";
    }
}
