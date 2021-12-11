package controller;

import model.CriaCombinacoesDeEtapasDeMontagem;
import model.CriaListaDeEtapasDeMontagem;
import model.EtapaDeMontagem;
import model.ListaDeEtapas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SistemaDeControle {

    private ListaDeEtapas listaDeEtapas;
    private CriaCombinacoesDeEtapasDeMontagem criaCombinacoesDeEtapasDeMontagem;

    public SistemaDeControle() {
        listaDeEtapas = new ListaDeEtapas();
        criaCombinacoesDeEtapasDeMontagem = new CriaCombinacoesDeEtapasDeMontagem();
    }

    public void tratarDadosDoArquivo(String caminhoArquivo) throws Exception {

        List<String> listaDeEtapasDeMontagem = listaDeEtapas.criaListaDeEtapas(caminhoArquivo);
        List<EtapaDeMontagem> etapasDeMontagemLista = CriaListaDeEtapasDeMontagem.criaLinhaDeMontagem(listaDeEtapasDeMontagem);

        criaCombinacoesDeEtapasDeMontagem.cronogramaDeMontagem(etapasDeMontagemLista);

    }
}
