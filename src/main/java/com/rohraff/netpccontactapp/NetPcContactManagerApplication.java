package com.rohraff.netpccontactapp;

import com.rohraff.netpccontactapp.model.Contact;
import com.rohraff.netpccontactapp.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class NetPcContactManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetPcContactManagerApplication.class, args);
    }
}
