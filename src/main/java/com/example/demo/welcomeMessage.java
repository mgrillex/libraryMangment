package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
@Component
public class welcomeMessage {
    public String getWelcomeMessage(){
        return "hello this is your springboot application";
    };

}
