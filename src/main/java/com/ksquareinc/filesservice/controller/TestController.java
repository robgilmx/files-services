package com.ksquareinc.filesservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "/ex", method = RequestMethod.GET)
    public String example(){
        return "Hello";
    }


}
