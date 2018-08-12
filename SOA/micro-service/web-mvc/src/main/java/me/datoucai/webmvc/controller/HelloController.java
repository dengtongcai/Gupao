package me.datoucai.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {

    @RequestMapping("/hello")
    public String first(Model model) {
        model.addAttribute("msg", "msggggggggggggggggg");
        return "/hello";
    }
}
