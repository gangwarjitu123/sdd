package com.sdd.controller;
import org.springframework.web.bind.annotation.*;


@RestController
public class IndexController {

    private static String applicationName = "SDD";

    @GetMapping("/")
    public String login() {
        return applicationName;
    }



}
