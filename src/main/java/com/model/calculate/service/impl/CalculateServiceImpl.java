package com.model.calculate.service.impl;

import com.model.calculate.domain.InputParam;
import com.model.calculate.service.CalculateService;
import com.model.calculate.util.BigDecimalUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Service
public class CalculateServiceImpl implements CalculateService {

    private final BigDecimal constant1 = new BigDecimal("2");
    //系数
    private final BigDecimal a = new BigDecimal("0.123545");
    //系数
    private final BigDecimal k1 = new BigDecimal("0.12");
    //降雨强度成正比的系数
    private final BigDecimal k2 = new BigDecimal("0.98");

    private final BigDecimal k3_1 = new BigDecimal("1.9");
    private final BigDecimal k3_2 = new BigDecimal("4");

    //对于氮，k4、𝛽分别取4.3， 2，对于磷，k4、𝛽分别取3， 3
    private final BigDecimal k4_1 = new BigDecimal("4.3");
    private final BigDecimal k4_1_1 = new BigDecimal("2");
    private final BigDecimal k4_2 = new BigDecimal("3");
    private final BigDecimal k4_2_1 = new BigDecimal("3");
    //重力加速度
    private final BigDecimal g = new BigDecimal("9.8");

    private final BigDecimal e = new BigDecimal("2.71828");

    @Override
    public String calculate(InputParam inputParam) {
        if (StringUtils.isEmpty(inputParam.getC0())) {
            return BigDecimal.ZERO.toPlainString();
        }
        inputParam.setD0Dec(new BigDecimal(inputParam.getD0()));
        inputParam.setkDec(new BigDecimal(inputParam.getK()));
        inputParam.setHtDec(new BigDecimal(inputParam.getHt()));
        inputParam.setrDec(new BigDecimal(inputParam.getR()));
        inputParam.setsDec(new BigDecimal(inputParam.getS()));
        inputParam.setCtDec(new BigDecimal(inputParam.getCt()));
        inputParam.setC0Dec(new BigDecimal(inputParam.getC0()));

        BigDecimal wt = new BigDecimal("1");

        //hs为测量的水稻根系深度，取值为60cm
        BigDecimal hs = new BigDecimal("60");
        BigDecimal u = BigDecimalUtil.divide(
                inputParam.getrDec().subtract((wt.multiply(BigDecimalUtil.divide(inputParam.getD0Dec(), hs)))),
                wt);

        BigDecimal t = BigDecimal.ONE;

        BigDecimal ce = u.multiply(inputParam.getCtDec())
                .subtract(inputParam.getC0Dec().multiply(e).multiply(inputParam.getrDec().multiply(BigDecimalUtil.divide(t, inputParam.getD0Dec()))));

        BigDecimal w = BigDecimal.ONE;
        BigDecimal et = new BigDecimal("2").multiply(ce).multiply(w);

        //k3为系数(无量纲)，对于氮，取1.9，对于磷，取4
        BigDecimal k3 = BigDecimal.ONE;
        if ("1".equals(inputParam.getType())) {
            k3 = new BigDecimal("1.9");
        } else if ("2".equals(inputParam.getType())) {
            k3 = new BigDecimal("4");
        }

        BigDecimal v = BigDecimalUtil.divide(inputParam.getD0Dec().add(inputParam.getrDec()).subtract(inputParam.getHtDec()), t);
        BigDecimal en = k3.multiply(et).multiply((BigDecimal.ONE.subtract(
                (new BigDecimal("0.8").multiply(BigDecimalUtil.pow(e, new BigDecimal("0.2").multiply(ce).multiply(new BigDecimal("-1"))))
                        .multiply(BigDecimalUtil.pow(e, new BigDecimal("0.2").multiply(v).multiply(new BigDecimal("-1"))))))));


        BigDecimal k4 = BigDecimal.ONE;
        BigDecimal p = BigDecimal.ONE;
        if ("1".equals(inputParam.getType())) {
            k4 = new BigDecimal("4.3");
            p = new BigDecimal("2");
        } else if ("2".equals(inputParam.getType())) {
            k4 = new BigDecimal("3");
            p = new BigDecimal("3");
        }
        BigDecimal tmp1 = BigDecimalUtil.divide(p.multiply(v), inputParam.getHtDec()).multiply(new BigDecimal("-1"));
        BigDecimal etr = k4.multiply(en).multiply((BigDecimal.ONE.subtract(BigDecimalUtil.pow(e, tmp1))));

        BigDecimal k5 = BigDecimal.ONE;
        BigDecimal cr = BigDecimal.ONE;
        BigDecimal y = BigDecimal.ONE;
        BigDecimal tmp3 = BigDecimalUtil.divide(y.multiply(v), inputParam.getHtDec()).multiply(new BigDecimal("-1"));
        BigDecimal tmp5 = (inputParam.getCtDec().add(cr)).multiply(BigDecimal.ONE.subtract(BigDecimalUtil.pow(e, tmp3))).multiply(v);
        BigDecimal er = k5.multiply(tmp5).multiply(BigDecimalUtil.divide(new BigDecimal("0.1"), t)).add(etr);

        return er.stripTrailingZeros().toPlainString();
    }
}
