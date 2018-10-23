package br.com.caelum.leilao.dominio;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import org.junit.Test;

import static br.com.caelum.leilao.dominio.LeilaoMatcher.temUmLance;
import static org.hamcrest.MatcherAssert.assertThat;

public class LanceTest {



    @Test(expected = IllegalArgumentException.class)
    public void oCasoOndeOValorEhZero(){
        new Lance(new Usuario("João"), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void oCasoOndeOValorDoLanceEhNegativo(){
        new Lance(new Usuario("João"), -15);
    }

    @Test
    public void haUmLanceAqui(){
        Leilao leilao = new CriadorDeLeilao().para("TV LCD 27 Polegadas").constroi();

        Lance lance = new Lance(new Usuario("João"),2000.0);
        leilao.propoe(lance);
        assertThat(leilao, temUmLance(lance));
    }
}
