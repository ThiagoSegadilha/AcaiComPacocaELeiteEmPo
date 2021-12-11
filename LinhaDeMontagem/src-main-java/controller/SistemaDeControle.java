package controller;

import model.*;

import java.util.*;

public class SistemaDeControle {

    private CriaListaComOsDadosDoArquivo criaListaComOsDadosDoArquivo;
    private CriaCombinacoesDeEtapasDeMontagem criaCombinacoesDeEtapasDeMontagem;


    public SistemaDeControle() {
        criaListaComOsDadosDoArquivo = new CriaListaComOsDadosDoArquivo();
        criaCombinacoesDeEtapasDeMontagem = new CriaCombinacoesDeEtapasDeMontagem();
//        criaCronogramaDasEtapasDeMontagem = new CriaCronogramaDasEtapasDeMontagem();
    }

    public void tratarDadosDoArquivo(String caminhoArquivo) throws Exception {

        List<String> listaDeEtapasDeMontagem = criaListaComOsDadosDoArquivo.criaListaDeEtapas(caminhoArquivo);
        List<EtapaDeMontagem> etapasDeMontagemLista = CriaListaDeEtapasDeMontagem.criaLinhaDeMontagem(listaDeEtapasDeMontagem);

        criaCombinacoesDeEtapasDeMontagem.cronogramaDeMontagem(etapasDeMontagemLista);
    }
}
