package com.model.calculate;

import com.model.calculate.domain.InputParam;
import com.model.calculate.service.CalculateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ModelCalculateApplicationTests {

    @Autowired
    private CalculateService calculateService;

    @Test
    void contextLoads() {
    }

    @Test
    public void calculate() {
        InputParam inputParam = new InputParam();
        inputParam.setCt("1.3242");
        inputParam.setD0("0.5412");
        inputParam.setK("1.522");
        inputParam.setR("0.433");
        inputParam.setHt("1.54242");
        inputParam.setC0("1.443");
        inputParam.setS("1.64242");
        inputParam.setType("0.24242");
        System.out.println(calculateService.calculate(inputParam));

    }

}
