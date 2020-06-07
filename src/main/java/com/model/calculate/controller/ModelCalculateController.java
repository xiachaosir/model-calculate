package com.model.calculate.controller;

import com.model.calculate.domain.InputParam;
import com.model.calculate.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ModelCalculateController {

    @Autowired
    private CalculateService calculateService;

    /**
     * 输入参数：田面水养分浓度、降水时间、降水强度、雨滴速度、土壤渗透系数、田埂高度
     *
     * @param inputParam inputParam
     * @param model      model
     * @return html
     */
    @PostMapping("calculate")
    public String calculate(@ModelAttribute InputParam inputParam, Model model) {
        String result = "0.12";
        //calculateService.calculate(inputParam);
        model.addAttribute("inputParam", inputParam);
        model.addAttribute("result", result);
        return "result.html";

    }

    @GetMapping("calculate")
    public String calculate(Model model) {
        model.addAttribute("inputParam", new InputParam());
        return "calculation.html";
    }

}
