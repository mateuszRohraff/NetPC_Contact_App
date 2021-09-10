package com.rohraff.netpccontactapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/result")
@Controller
public class ResultController {

    @GetMapping()
    public String resultView(Model model){
        return "result";
    }
}
