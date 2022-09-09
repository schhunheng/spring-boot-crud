package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {

    @RequestMapping("")
    public String index() {
        return "index";
    }

    @RequestMapping("/user")
    public String home(HttpServletRequest req, HttpServletResponse res) {
        System.out.println(req.getParameter("name"));
        HttpSession session = req.getSession();
        String name = req.getParameter("name");
        session.setAttribute("username", name);
        return "user";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
}
