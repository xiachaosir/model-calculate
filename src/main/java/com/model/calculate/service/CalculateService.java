package com.model.calculate.service;

import com.model.calculate.domain.InputParam;
import com.model.calculate.domain.ResultDto;

public interface CalculateService {
    ResultDto calculate(InputParam inputParam);
}
