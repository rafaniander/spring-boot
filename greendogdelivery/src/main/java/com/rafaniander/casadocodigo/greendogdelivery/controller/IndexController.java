package com.rafaniander.casadocodigo.greendogdelivery.controller;

import java.util.Properties;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/ambiente")
    public String ambiente() {
        return "ambiente";
    }

    @GetMapping("/delivery")
    public String delivery() {
        return "delivery/index";
    }

    @GetMapping("/properties")
    @ResponseBody
    public Properties properties() {
        return System.getProperties();
    }

}
