package com.model.calculate.controller;

import com.model.calculate.domain.Result;
import com.model.calculate.domain.User;
import com.model.calculate.service.LoginService;
import com.model.calculate.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Resource
    private SysMenuService sysMenuService;

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @PostMapping("doLogin")
    @ResponseBody
    public Result doLogin(User user) {
        return loginService.login(user);
    }

    @GetMapping("/index")
    public String index() {
        return "index.html";
    }

    @GetMapping("/login/menu")
    @ResponseBody
    public Map<String, Object> menu() {
        return sysMenuService.menu();
    }


}
