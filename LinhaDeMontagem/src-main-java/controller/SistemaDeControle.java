package controller;

import model.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class SistemaDeControle {

    private ListaDeEtapas listaDeEtapas;
    private CriaCombinacoesDeEtapasDeMontagem criaCombinacoesDeEtapasDeMontagem;


    public SistemaDeControle() {
        listaDeEtapas = new ListaDeEtapas();
        criaCombinacoesDeEtapasDeMontagem = new CriaCombinacoesDeEtapasDeMontagem();
//        criaCronogramaDasEtapasDeMontagem = new CriaCronogramaDasEtapasDeMontagem();
    }

    public void tratarDadosDoArquivo(String caminhoArquivo) throws Exception {

        List<String> listaDeEtapasDeMontagem = listaDeEtapas.criaListaDeEtapas(caminhoArquivo);
        List<EtapaDeMontagem> etapasDeMontagemLista = CriaListaDeEtapasDeMontagem.criaLinhaDeMontagem(listaDeEtapasDeMontagem);

        criaCombinacoesDeEtapasDeMontagem.cronogramaDeMontagem(etapasDeMontagemLista);
    }
}
