package br.com.caelum.leilao.dominio;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeilaoTest {

    private Usuario steveJobs;
    private Usuario steveWozniak;
    private Usuario billGates;

    @Before
    public void criaLeilao(){
        this.steveJobs = new Usuario("Steve Jobs");
        this.steveWozniak = new Usuario("Steve Wozniak");
        this.billGates = new Usuario("Bill Gates");
    }

    @Test
    public  void deveReceberUmLance(){
        Leilao leilao = new Leilao("Macbook pro 15");
        assertEquals(0, leilao.getLances().size());

        leilao.propoe(new Lance(steveJobs, 2000.0));

        assertEquals(1,leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @BeforeClass
    public static void testandoBeforeClass(){
        System.out.println("before class");
    }

    @AfterClass
    public static void testandoAfterClass(){
        System.out.println("after class");
    }

    @Test
    public void deveReceberVariosLances(){

        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15")
                .lance(steveJobs, 2000.0)
                .lance(steveWozniak, 3000.0)
                .constroi();

        assertEquals(2, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
        assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
    }

    @Test
    public  void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario(){

        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15")
                .lance(steveJobs, 2000.0)
                .lance(steveJobs, 3000.0)
                .constroi();

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
    }

    @Test
    public void naoDeveAceitarMaisDoQue5LancesDoMesmoUsuario(){

        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15")
                .lance(steveJobs,2000.0)
                .lance(billGates,3000.0)
                .lance(steveJobs,4000.0)
                .lance(billGates,5000.0)
                .lance(steveJobs,6000.0)
                .lance(billGates,7000.0)
                .lance(steveJobs,8000.0)
                .lance(billGates,9000.0)
                .lance(steveJobs,10000.0)
                .lance(billGates,11000.0)
                //dever ser ignorado
                .lance(steveJobs,12000.0)
                .constroi();



        assertEquals(10, leilao.getLances().size());
        assertEquals(11000.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
    }

    @Test
    public void deveDobrarOLance(){

        Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15")
                .lance(steveJobs, 2000.0)
                .lance(billGates, 3000.0)
                .constroi();

        leilao.dobraLance(steveJobs);

        assertEquals(4000.0, leilao.getLances().get(2).getValor(),0.00001);
    }

    @Test
    public void naoDeveDobrarCasoNaoHajaLanceAnterior(){
        Leilao leilao = new Leilao("Macbook Pro 15");

        leilao.dobraLance(steveJobs);

        assertEquals(0, leilao.getLances().size());
    }
}
