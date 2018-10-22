package br.com.caelum.leilao.teste;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnoBissextoTest {

    @Test
    public void verificarSeEhBissexto(){
        AnoBissexto ab = new AnoBissexto();

        assertTrue(ab.ehBissexto(400));
        assertTrue(ab.ehBissexto(404));
    }

    @Test
    public void verificarSeNaoEhBissexto(){
        AnoBissexto ab = new AnoBissexto();

        assertFalse(ab.ehBissexto(398));
        assertFalse(ab.ehBissexto(300));
    }
}
