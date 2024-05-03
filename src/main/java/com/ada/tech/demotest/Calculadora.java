package com.ada.tech.demotest;

import java.math.BigDecimal;

public class Calculadora {

    public BigDecimal somar(BigDecimal one, BigDecimal two) {
        return one.add(two);
    }

    public BigDecimal subtracao(BigDecimal one, BigDecimal two) {
        return one.subtract(two);
    }

    public BigDecimal multiply(BigDecimal one, BigDecimal two) {
        return one.multiply(two);
    }

}
