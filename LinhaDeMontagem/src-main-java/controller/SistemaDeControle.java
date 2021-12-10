package controller;

import model.CriaEtapasDeMontagem;
import model.EtapaDeMontagem;
import model.ListaDeEtapas;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SistemaDeControle {

    private ListaDeEtapas listaDeEtapas;

    public SistemaDeControle() {
        listaDeEtapas = new ListaDeEtapas();
    }

    public void tratarDadosDoArquivo(String caminhoArquivo) throws Exception {

        List<String> listaDeEtapasDeMontagem = listaDeEtapas.criaListaDeEtapas(caminhoArquivo);
        List<EtapaDeMontagem> etapasDeMontagemLista = CriaEtapasDeMontagem.criaLinhaDeMontagem(listaDeEtapasDeMontagem);

        cronogramaDeMontagem(etapasDeMontagemLista);

    }
    
    public void cronogramaDeMontagem(List<EtapaDeMontagem> etapasDeMontagemLista) {
        int tempoTotalDoDia = 360;
        int tempoTotalDasEtapas = getTempoTotalDasEtapas(etapasDeMontagemLista);
        int tempoTotalPorDia = tempoTotalDasEtapas/tempoTotalDoDia;

        // Necessario implementar o Comparable<Object> na classe EtapaDeMontagem para realizar a ordenação
        Collections.sort(etapasDeMontagemLista);

        for (EtapaDeMontagem etapa : etapasDeMontagemLista) {
            System.out.println(etapa.getNome() + " " + etapa.getTempoDeDuracao());
        }
    }

    public static int getTempoTotalDasEtapas(List<EtapaDeMontagem> etapasDeMontagemLista) {
        if (etapasDeMontagemLista == null || etapasDeMontagemLista.isEmpty()) {
            return 0;
        }

        int tempoTotal = 0;
        for (EtapaDeMontagem etapa : etapasDeMontagemLista) {
            tempoTotal += etapa.getTempoDeDuracao();
        }
        return tempoTotal;
    }
}
