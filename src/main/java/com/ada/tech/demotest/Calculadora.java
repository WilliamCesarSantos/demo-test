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

    public BigDecimal divisao(BigDecimal one, BigDecimal two) {
        if (two.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Não é possivel dividir por zero");
        }
        return one.divide(two);
    }

    public BigDecimal percentual(BigDecimal valorTotal, BigDecimal valorPercentual) {
        BigDecimal dividido = divisao(valorTotal, BigDecimal.valueOf(100));
        return multiply(dividido, valorPercentual);
    }

}
