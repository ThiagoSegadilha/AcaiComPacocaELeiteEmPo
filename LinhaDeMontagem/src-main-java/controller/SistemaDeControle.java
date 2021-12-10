package controller;

import model.CriaEtapasDeMontagem;
import model.EtapaDeMontagem;
import model.ListaDeEtapas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaDeControle {

    private ListaDeEtapas listaDeEtapas;

    public SistemaDeControle() {
        listaDeEtapas = new ListaDeEtapas();
    }

    public void tratarDadosDoArquivo(String caminhoArquivo) throws Exception {

        ArrayList<String> listaDeEtapasDeMontagem = listaDeEtapas.criaListaDeEtapas(caminhoArquivo);
        CriaEtapasDeMontagem.criaLinhaDeMontagem(listaDeEtapasDeMontagem);

    }
}
