package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

import java.util.ArrayList;
import java.util.List;

public class Avaliador {

    private double maiorDeTodos= Double.NEGATIVE_INFINITY;
    private double menorDeTodos= Double.POSITIVE_INFINITY;
    private double mediaDeTodos=0;
    private List<Lance> maiores= new ArrayList<>();

    public void avalia(Leilao leilao){
        double total=0;
        for (Lance lance: leilao.getLances()){
            if (lance.getValor()> maiorDeTodos) maiorDeTodos = lance.getValor();
            if (lance.getValor()< menorDeTodos) menorDeTodos = lance.getValor();
            total += lance.getValor();
        }
        if(total==0){
            mediaDeTodos=0;
            return;
        }
        mediaDeTodos = total/leilao.getLances().size();

        maiores = new ArrayList<>(leilao.getLances());
        maiores.sort((o1, o2) -> Double.compare(o2.getValor(), o1.getValor()));

        maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
//        System.out.println(maiores.size());

    }

    public List<Lance> getTresMaiores() { return maiores; }

    public double getMaiorLance() {
        return maiorDeTodos;
    }

    public double getMenorLance() {
        return menorDeTodos;
    }

    public double getMediaDeTodos() {
        return mediaDeTodos;
    }
}
