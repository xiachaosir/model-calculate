package com.model.calculate.service.impl;

import com.model.calculate.domain.InputParam;
import com.model.calculate.domain.ResultDto;
import com.model.calculate.service.CalculateService;
import com.model.calculate.util.BigDecimalUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Formatter;

@Service
public class CalculateServiceImpl implements CalculateService {

    private final BigDecimal e = new BigDecimal("2.71828");
    private final BigDecimal g = new BigDecimal("9.8");
    private final BigDecimal mv = new BigDecimal("0.00018");
    private final BigDecimal TWO = new BigDecimal("2");
    private final BigDecimal PI = new BigDecimal("3.1415");

    @Override
    public ResultDto calculate(InputParam inputParam) {
        inputParam.setD0Dec(new BigDecimal(inputParam.getD0()));
        inputParam.setkDec(new BigDecimal(inputParam.getK()));
        inputParam.setHtDec(new BigDecimal(inputParam.getHt()));
        inputParam.setrDec(new BigDecimal(inputParam.getR()));
        inputParam.setsDec(new BigDecimal(inputParam.getS()));
        inputParam.setCtDec(new BigDecimal(inputParam.getCt()));
        inputParam.setC0Dec(new BigDecimal(inputParam.getC0()));
        inputParam.settDec(new BigDecimal(inputParam.getT()));
        //降水养分浓度
        inputParam.setCrDec(new BigDecimal(inputParam.getCr()));
        inputParam.setVdropDec(new BigDecimal(inputParam.getVdrop()));
        inputParam.setType("1");
        BigDecimal k2 = new BigDecimal("0.7").multiply(BigDecimalUtil.divide(inputParam.getrDec(), inputParam.gettDec()));
        BigDecimal f2 = BigDecimalUtil.divide(inputParam.getrDec().multiply(inputParam.gettDec()), new BigDecimal("60"));
        BigDecimal h0Right = f2.multiply(BigDecimalUtil.divide(BigDecimal.ONE, new BigDecimal("2").multiply(g)))
                .multiply(BigDecimalUtil.pow(inputParam.getVdropDec(), TWO));
        BigDecimal f1 = inputParam.getD0Dec().multiply(inputParam.getsDec());
        BigDecimal h0 = BigDecimalUtil.divide(h0Right, f1);
        //连续方程系数
        BigDecimal a = BigDecimalUtil.divide(inputParam.getkDec(), new BigDecimal("1000").multiply(g).multiply(mv)).multiply(new BigDecimal("1000000"));
        BigDecimal T = BigDecimalUtil.divide(BigDecimal.ONE, BigDecimalUtil.pow(new BigDecimal("6.25")
                .multiply(BigDecimalUtil.pow(inputParam.getrDec(), new BigDecimal("0.115"))), TWO));
        BigDecimal wt = BigDecimalUtil.divide(inputParam.getkDec(), k2).multiply(h0).multiply(BigDecimalUtil.sqrt(BigDecimalUtil.divide(PI, a.multiply(T)), 2, 8));
        wt = BigDecimalUtil.divide(wt, inputParam.gettDec());
        //hs为测量的水稻根系深度，取值为60cm
        BigDecimal hs = new BigDecimal("60");
        BigDecimal u = new BigDecimal("0.8");
        BigDecimal ce = u.multiply(inputParam.getCtDec());
        BigDecimal et = TWO.multiply(ce).multiply(wt).multiply(new BigDecimal("600"));

        //k3为系数(无量纲)，对于氮，取1.9，对于磷，取4
        BigDecimal k3 = BigDecimal.ONE;
        if ("1".equals(inputParam.getType())) {
            k3 = new BigDecimal("1.9");
        } else if ("2".equals(inputParam.getType())) {
            k3 = new BigDecimal("4");
        }
        BigDecimal v = inputParam.getD0Dec()
                .add(BigDecimalUtil.divide(inputParam.gettDec().multiply(inputParam.getrDec()), new BigDecimal("600")))
                .subtract(inputParam.getHtDec());

        BigDecimal enRight = (new BigDecimal("0.8").multiply(BigDecimalUtil.pow(e, new BigDecimal("0.2").multiply(ce).multiply(new BigDecimal("-1"))))
                .multiply(BigDecimalUtil.pow(e, new BigDecimal("0.2").multiply(v).multiply(new BigDecimal("-1")))));

        BigDecimal en = k3.multiply(et).multiply((BigDecimal.ONE.subtract(enRight)));

        BigDecimal k4 = BigDecimal.ONE;
        BigDecimal p = BigDecimal.ONE;
        BigDecimal k5 = BigDecimal.ONE;

        if ("1".equals(inputParam.getType())) {
            k4 = new BigDecimal("4.3");
            p = new BigDecimal("2");
            k5 = new BigDecimal("90").multiply(BigDecimalUtil.divide(BigDecimal.ONE, BigDecimalUtil.pow(inputParam.getrDec(), new BigDecimal("0.75"))));
        } else if ("2".equals(inputParam.getType())) {
            k4 = new BigDecimal("3");
            p = new BigDecimal("3");
            k5 = new BigDecimal("120").multiply(BigDecimalUtil.divide(BigDecimal.ONE, BigDecimalUtil.pow(inputParam.getrDec(), new BigDecimal("0.8"))));
        }

        BigDecimal tmp1 = BigDecimalUtil.divide(p.multiply(v), inputParam.getHtDec().multiply(inputParam.gettDec()));
        BigDecimal etr = k4.multiply(en).multiply((BigDecimal.ONE.subtract(BigDecimalUtil.divide(BigDecimal.ONE, BigDecimalUtil.pow(e, tmp1)))));

        BigDecimal y = new BigDecimal("0.2");
        BigDecimal tmp3 = BigDecimalUtil.divide(y.multiply(v), inputParam.getHtDec());

        BigDecimal t1 = inputParam.getC0Dec().add(inputParam.getCrDec());
        BigDecimal t2 = BigDecimal.ONE.subtract(BigDecimalUtil.divide(BigDecimal.ONE, BigDecimalUtil.pow(e, tmp3)));

        BigDecimal er = BigDecimalUtil.divide(k5.multiply(t1).multiply(t2).multiply(v).multiply(new BigDecimal("0.1")), inputParam.gettDec()).add(etr);
        ResultDto resultDto = new ResultDto();
        resultDto.setEtr(etr.setScale(9, BigDecimal.ROUND_HALF_UP).toString());
        resultDto.setEr(er.setScale(9, BigDecimal.ROUND_HALF_UP).toString());
        return resultDto;
    }
}
