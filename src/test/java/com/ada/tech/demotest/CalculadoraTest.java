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
    public void somarDoisPontoDoisMainUmPontoCincoDeveSerIgualATresPontoSete() {
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

    @Test
    public void multiplicar_DoisPorDois_DeveSerIgualAQuatro() {
        // Prover os dados para execução
        BigDecimal one = BigDecimal.valueOf(2);
        BigDecimal two = BigDecimal.valueOf(2);

        // Chamar a execução
        BigDecimal resultado = calculadora.multiply(one, two);

        // Conferir resultado
        if (resultado.compareTo(BigDecimal.valueOf(4)) != 0) {
            throw new RuntimeException("Calculadora em falha");
        }
    }

    @Test
    public void dividir_oitoPorDois_deveSerIgualAQuatro() {
        // Prover os dados para execução
        BigDecimal one = BigDecimal.valueOf(8);
        BigDecimal two = BigDecimal.valueOf(2);

        // Chamar a execução
        BigDecimal resultado = calculadora.divisao(one, two);

        // Conferir resultado
        BigDecimal valorEsperado = BigDecimal.valueOf(4);
        if (resultado.compareTo(valorEsperado) != 0) {
            throw new RuntimeException("Calculadora em falha");
        }
    }

    // Dividir 8.2 por 2, deve gerar resultado igual a 4.1
    // Valida que a divisão esteja trabalhando com número decimais
    @Test
    public void dividir_oitoPontoDoisPorDois_deveSerIgualAQuatroPontoUm() {
        // Prover os dados para execução
        BigDecimal one = BigDecimal.valueOf(8.2);
        BigDecimal two = BigDecimal.valueOf(2);

        // Chamar a execução
        BigDecimal resultado = calculadora.divisao(one, two);

        // Conferir resultado
        BigDecimal valorEsperado = BigDecimal.valueOf(4.1);
        if (resultado.compareTo(valorEsperado) != 0) {
            throw new RuntimeException("Calculadora em falha");
        }
    }

    // Dividir 8 por 2.5, deve gerar resultado igual a 3.2
    @Test
    public void dividir_oitoPorDoisPontoCinco_deveSerIgualATresPontoDois() {
        // Prover os dados para execução
        BigDecimal one = BigDecimal.valueOf(8);
        BigDecimal two = BigDecimal.valueOf(2.5);

        // Chamar a execução
        BigDecimal resultado = calculadora.divisao(one, two);

        // Conferir resultado
        BigDecimal valorEsperado = BigDecimal.valueOf(3.2);
        if (resultado.compareTo(valorEsperado) != 0) {
            throw new RuntimeException("Calculadora em falha");
        }
    }

    // Divisao não pode aceitar zero
    @Test
    public void dividir_doisPorZero_deveLancarExcecao() {
        // Prover os dados para execução
        BigDecimal one = BigDecimal.valueOf(2);
        BigDecimal two = BigDecimal.ZERO;

        // Chamar a execução
        Boolean ocorreuExcecao = false;
        try {
            calculadora.divisao(one, two);
        } catch (IllegalArgumentException exception) {
            ocorreuExcecao = true;
        }

        //Conferir resultado
        if (!ocorreuExcecao) {
            throw new RuntimeException("Calculadora em falha");
        }
    }

    // Calcular 10 porcento de 150, deve ser igual a 15
    @Test
    public void percentual_dezDeCentoECinquenta_deveSerIgualAQuinze() {
        // Prover os dados para execução
        BigDecimal valorTotal = BigDecimal.valueOf(150);
        BigDecimal percentual = BigDecimal.valueOf(10);

        // Chamar a execução
        BigDecimal resultado = calculadora.percentual(valorTotal, percentual);

        // Conferir resultado
        BigDecimal valorEsperado = BigDecimal.valueOf(15);
        if (resultado.compareTo(valorEsperado) != 0) {
            throw new RuntimeException("Calculadora em falha");
        }
    }

}
