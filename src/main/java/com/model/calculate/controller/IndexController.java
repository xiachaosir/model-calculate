package com.model.calculate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("page")
public class IndexController {

    @GetMapping("/welcome")
    public String welcome() {
        return "page/welcome-1.html";
    }

    @GetMapping("/setting")
    public String menu(){
        return "page/setting.html";
    }
}
