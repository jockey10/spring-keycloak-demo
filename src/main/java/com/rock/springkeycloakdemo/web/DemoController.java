package com.rock.springkeycloakdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class DemoController {
    @RequestMapping(value="/")
    public String home() {
        return "home";
    }

    @RequestMapping(value="/secure")
    public String secure() {
        return "secure";
    }

    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "home";
    }
}