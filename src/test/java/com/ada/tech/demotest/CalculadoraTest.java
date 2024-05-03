package com.ada.tech.demotest;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CalculadoraTest {
    Calculadora calculadora = new Calculadora();

    @Test
    public void somarDoisMaisDoisDeveSerIgualAQuartro() {
        BigDecimal resultado = calculadora.somar(BigDecimal.valueOf(2), BigDecimal.valueOf(2));
        if (resultado.longValue() != 4) {
            throw new RuntimeException("calculadora em falha.");
        }
    }

    @Test
    public void somarDoisPontoDoisMainUmPontoCincoDeveSerIgualATresPontoSete(){
        BigDecimal resultado = calculadora.somar(BigDecimal.valueOf(2.2), BigDecimal.valueOf(1.5));
        if (resultado.compareTo(BigDecimal.valueOf(3.7)) != 0) {
            throw new RuntimeException("calculadora em falha.");
        }
    }

    @Test
    public void subtracaoDoisMenosUmDeveSerIgualAUm() {
        BigDecimal resultado = calculadora.subtracao(BigDecimal.valueOf(2), BigDecimal.valueOf(1));
        if (resultado.compareTo(BigDecimal.valueOf(1)) != 0) {
            throw new RuntimeException("calculadora em falha.");
        }
    }

    @Test
    public void multiplicar() {
        BigDecimal resultado = calculadora.multiply(BigDecimal.valueOf(2), BigDecimal.valueOf(2));
        if (resultado.compareTo(BigDecimal.valueOf(4)) != 0) {
            throw new RuntimeException("calculadora em falha.");
        }
    }

}
