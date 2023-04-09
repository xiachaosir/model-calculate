package com.model.calculate.controller;

import com.model.calculate.domain.InputParam;
import com.model.calculate.domain.Result;
import com.model.calculate.domain.ResultDto;
import com.model.calculate.service.CalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ModelCalculateController {

    private Logger LOGGER = LoggerFactory.getLogger(ModelCalculateController.class);

    @Autowired
    private CalculateService calculateService;

    /**
     * 输入参数：田面水养分浓度、降水时间、降水强度、雨滴速度、土壤渗透系数、田埂高度
     *
     * @param inputParam inputParam
     * @param model      model
     * @return html
     */
    @PostMapping("/calculate")
    @ResponseBody
    public Result<ResultDto> calculate(@ModelAttribute InputParam inputParam, Model model) {
        Result<ResultDto> result = new Result<>();
        try {
            result.setStatus(0);
            result.setData(calculateService.calculate(inputParam));
        } catch (Exception e) {
            LOGGER.error("计算失败，", e);
            result.setStatus(1);
            result.setMessage("计算失败");
        }
        return result;
    }

    @GetMapping("/calculate")
    public String calculate(Model model) {
        model.addAttribute("inputParam", new InputParam());
        return "calculation.html";
    }

}
