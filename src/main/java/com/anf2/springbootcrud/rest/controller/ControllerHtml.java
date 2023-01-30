package com.anf2.springbootcrud.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerHtml {

    @RequestMapping("/")
    public String defaultPage1 () {
        return "<!DOCTYPE html><html><head>TEXT 137<br></head><body>______________________</body></html>";
    }

    @RequestMapping("/2")
    public String defaultPage2 () {
        return "<!DOCTYPE html><html><head>TEXT1!!!!!!!!!!!!<br><br><br><br>" +
                "</head><body>TEXT2%%%%%%%%%%%%%%<br><br><br><br>" +
                "TEXT3&&&&&&&&&&&</body></html>";
    }
}
