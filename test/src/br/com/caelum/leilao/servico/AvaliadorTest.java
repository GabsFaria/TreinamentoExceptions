package br.com.caelum.leilao.servico;


import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


public class AvaliadorTest {

    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;

    @Before
    public void criaAvaliador(){
        this.leiloeiro = new Avaliador();
        System.out.println("cria avaliador");
        this.joao = new Usuario("João");
        this.jose = new Usuario("José");
        this.maria = new Usuario("Maria");
    }

    @After
    public void finaliza(){
        System.out.println("fim");
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveAvaliarLeiloesSemNenhumLanceDado(){
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").constroi();
        leiloeiro.avalia(leilao);
    }


    @Test
    public void deveEntenderLancesEmOrdemCrescente() {

        //parte 1 : cenario
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 novo")
                .lance(joao, 250.0)
                .lance(jose, 300.0)
                .lance(maria, 400.0)
                .constroi();

        //parte 2 : acao
        leiloeiro.avalia(leilao);

        //parte 3 : validacao
        assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
        assertThat(leiloeiro.getMenorLance(), equalTo(250.0));
        assertEquals(316.6666666666667, leiloeiro.getMediaDeTodos(), 0.00001);

    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance(){

        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 novo")
                .lance(joao, 1000.0)
                .constroi();

        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMaiorLance(),equalTo(1000.0));
        assertThat(leiloeiro.getMenorLance(),equalTo(1000.0));
    }

    @Test
    public void deveEncontrarOs3MaioresLances(){

        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 100.0)
                .lance(maria, 200.0)
                .lance(joao, 300.0)
                .lance(maria, 400.0)
                .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertThat(maiores.size(),equalTo(3));

        assertThat(maiores, hasItems(
                new Lance(maria,400.0),
                new Lance(joao,300.0),
                new Lance(maria, 200.0)
        ));

    }

    @Test
    public void deveEncontrarEmOrdemRandomica(){

        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 200.0)
                .lance(maria, 450.0)
                .lance(joao, 120.0)
                .lance(maria, 700.0)
                .lance(joao, 630.0)
                .lance(maria, 230.0)
                .constroi();


        leiloeiro.avalia(leilao);

        assertThat(leiloeiro.getMaiorLance(),equalTo(700.0));
        assertThat(leiloeiro.getMenorLance(),equalTo(120.0));
    }

    @Test
    public void deveEncontrarOs3MaioresLancesComApenas2Lances(){

        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 300.0)
                .lance(maria, 400.0)
                .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertThat(maiores.size(),equalTo(2));

        assertThat(maiores, hasItems(
                new Lance(maria,400.0),
                new Lance(joao, 300.0)
        ));
    }

    @Test(expected = RuntimeException.class)
    public void deveEncontrarOs3MaioresLancesComNenhumLance(){

        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertThat(maiores.size(),equalTo(0));
    }
}
