package br.com.caelum.leilao.teste;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PalindromoTest {

    @Test
    public void DeveVerificarSeEPalindromo() {
        Palindromo palindromo = new Palindromo();
        boolean resultado = palindromo.ehPalindromo("Arara");

        Assertions.assertTrue(resultado);
    }

    @Test
    public void DeveVerificarSeCorrigeSImbolos(){
        Palindromo palindromo = new Palindromo();
        boolean resultado = palindromo.ehPalindromo("Socorram-me subi no onibus em Marrocos");
        Assertions.assertTrue(resultado);
    }

    @Test
    public void DeveVerificarSeNaoEPalindromo(){
        Palindromo palindromo = new Palindromo();
        boolean resultado = palindromo.ehPalindromo("Eu como arroz");
        Assertions.assertFalse(resultado);
    }
}
