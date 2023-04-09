package com.model.calculate.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDecimalUtil {

    /**
     * 牛顿迭代法计算平方根
     *
     * @param value value
     * @param scale 精度
     * @return big
     */
    public static BigDecimal sqrt(BigDecimal value, int n, int scale) {
        BigDecimal num2 = BigDecimal.valueOf(n);
        int precision = 100;
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal deviation = value;
        int cnt = 0;
        while (cnt < precision) {
            deviation = (deviation.add(value.divide(deviation, mc))).divide(num2, mc);
            cnt++;
        }
        deviation = deviation.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return deviation;
    }

    /**
     * 计算n次方 n可为小数 负数
     *
     * @param value value
     * @param n     n
     * @return BigDecimal
     */
    public static BigDecimal pow(BigDecimal value, BigDecimal n) {
        Double result = Math.pow(value.doubleValue(), n.doubleValue());
        return new BigDecimal(result.toString(), MathContext.DECIMAL64);

    }

    /**
     * 开n次方根
     *
     * @param number 数值
     * @param n      几次方
     * @return BigDecimal
     */
    public static BigDecimal bigRoot(BigDecimal number, int n) {
        int scale = 2;
        int roundingMode = BigDecimal.ROUND_HALF_UP;
        boolean negate = false;
        if (n < 0)
            throw new ArithmeticException();
        if (number.compareTo(BigDecimal.ZERO) < 0) {
            if (n % 2 == 0)
                throw new ArithmeticException();
            else {
                number = number.negate();
                negate = true;
            }
        }
        BigDecimal root;
        if (n == 0)
            root = BigDecimal.ONE;
        else if (n == 1)
            root = number;
        else {
            final BigInteger N = BigInteger.valueOf(n);
            final BigInteger N2 = BigInteger.TEN.pow(n);
            final BigInteger N3 = BigInteger.TEN.pow(n - 1);
            final BigInteger NINE = BigInteger.valueOf(9);

            BigInteger[] C = new BigInteger[n + 1];
            for (int i = 0; i <= n; i++) {
                C[i] = combination(n, i);
            }
            BigInteger integer = number.toBigInteger();
            String strInt = integer.toString();
            int lenInt = strInt.length();
            for (int i = lenInt % n; i < n && i > 0; i++)
                strInt = "0" + strInt;
            lenInt = (lenInt + n - 1) / n * n;
            BigDecimal fraction = number.subtract(number.setScale(0, BigDecimal.ROUND_DOWN));
            int lenFrac = (fraction.scale() + n - 1) / n * n;
            fraction = fraction.movePointRight(lenFrac);
            String strFrac = fraction.toPlainString();
            for (int i = strFrac.length(); i < lenFrac; i++)
                strFrac = "0" + strFrac;

            BigInteger res = BigInteger.ZERO;
            BigInteger rem = BigInteger.ZERO;
            for (int i = 0; i < lenInt / n; i++) {
                rem = rem.multiply(N2);

                BigInteger temp = new BigInteger(strInt.substring(i * n, i * n + n));
                rem = rem.add(temp);

                BigInteger j;
                if (res.compareTo(BigInteger.ZERO) != 0)
                    j = rem.divide(res.pow(n - 1).multiply(N).multiply(N3));
                else
                    j = NINE;
                BigInteger test = BigInteger.ZERO;
                temp = res.multiply(BigInteger.TEN);
                while (j.compareTo(BigInteger.ZERO) >= 0) {
                    test = BigInteger.ZERO;
                    if (j.compareTo(BigInteger.ZERO) > 0)
                        for (int k = 1; k <= n; k++)
                            test = test.add(j.pow(k).multiply(C[k]).multiply(temp.pow(n - k)));
                    if (test.compareTo(rem) <= 0)
                        break;
                    j = j.subtract(BigInteger.ONE);
                }

                rem = rem.subtract(test);
                res = res.multiply(BigInteger.TEN);
                res = res.add(j);
            }
            for (int i = 0; i <= scale; i++) {
                rem = rem.multiply(N2);

                if (i < lenFrac / n) {
                    BigInteger temp = new BigInteger(strFrac.substring(i * n, i * n + n));
                    rem = rem.add(temp);
                }

                BigInteger j;
                if (res.compareTo(BigInteger.ZERO) != 0) {
                    j = rem.divide(res.pow(n - 1).multiply(N).multiply(N3));
                } else
                    j = NINE;
                BigInteger test = BigInteger.ZERO;
                BigInteger temp = res.multiply(BigInteger.TEN);
                while (j.compareTo(BigInteger.ZERO) >= 0) {
                    test = BigInteger.ZERO;
                    if (j.compareTo(BigInteger.ZERO) > 0)
                        for (int k = 1; k <= n; k++)
                            test = test.add(j.pow(k).multiply(C[k]).multiply(temp.pow(n - k)));
                    if (test.compareTo(rem) <= 0)
                        break;
                    j = j.subtract(BigInteger.ONE);
                }

                rem = rem.subtract(test);
                res = res.multiply(BigInteger.TEN);
                res = res.add(j);
            }
            root = new BigDecimal(res).movePointLeft(scale + 1);
            if (negate)
                root = root.negate();
        }
        return root.setScale(scale, roundingMode);
    }

    public static BigInteger combination(int n, int k) {
        if (k > n || n < 0 || k < 0)
            return BigInteger.ZERO;
        if (k > n / 2)
            return combination(n, n - k);
        BigInteger N1 = BigInteger.ONE;
        BigInteger N2 = BigInteger.ONE;
        BigInteger N = BigInteger.valueOf(n);
        BigInteger K = BigInteger.valueOf(k);
        for (int i = 0; i < k; i++) {
            N1 = N1.multiply(N);
            N2 = N2.multiply(K);
            N = N.subtract(BigInteger.ONE);
            K = K.subtract(BigInteger.ONE);
        }
        return N1.divide(N2);
    }

    /**
     * value / value2
     *
     * @param value1 value1
     * @param value2 value2
     * @return BigDecimal
     */
    public static BigDecimal divide(BigDecimal value1, BigDecimal value2) {
        return value1.divide(value2, 8, BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String[] args) {

        System.out.println(new BigDecimal("0.0023424224"));
        System.out.println(new BigDecimal("2.3E10").toPlainString());
        System.out.println(new BigDecimal("0.0023424224").toString());
        System.out.println(new BigDecimal("0.0023424224").stripTrailingZeros().toString());
        System.out.println(new BigDecimal("0.0023424224").toPlainString());

        System.out.println(pow(new BigDecimal("2"), new BigDecimal("-1.1")));

        System.out.println(BigDecimal.ONE.subtract(new BigDecimal("2").multiply(new BigDecimal("-1.1"))));


        //Double d = 0.224242;

        //2的0.15次方
        //System.out.println(bigRoot(new BigDecimal("8"), 3));

        //new BigDecimal("2").pow(15);
        //System.out.println(bigRoot(new BigDecimal("2.71").pow(15), 100));
        // System.out.println(new BigDecimal("1").divide(new BigDecimal("2").pow(2)));
    }
}
