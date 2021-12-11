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
        int TEMPO_TOTAL_DO_DIA = 360;
        int tempoTotalDasEtapas = getTempoTotalDasEtapas(etapasDeMontagemLista);
        int tempoTotalPorDia = tempoTotalDasEtapas / TEMPO_TOTAL_DO_DIA;

        // Necessario implementar o Comparable<Object> na classe EtapaDeMontagem para realizar a ordenação
        Collections.sort(etapasDeMontagemLista);

        for (EtapaDeMontagem etapa : etapasDeMontagemLista) {
            System.out.println(etapa.getNome() + " " + etapa.getTempoDeDuracao());
        }

        combinacoesTurnoManha(etapasDeMontagemLista, tempoTotalPorDia);
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

    public void combinacoesTurnoManha(List<EtapaDeMontagem> etapasDeMontagemLista, int tempoTotalPorDia) {
        int TEMPO_TURNO_MANHA = 180;
        int numeroDeEtapas = etapasDeMontagemLista.size();
        List<List<EtapaDeMontagem>> combinacaoDeEtapas = new ArrayList<List<EtapaDeMontagem>>();
        int numeroDePossiveisCombinacoes = 0;

        for (int i = 0; i < numeroDeEtapas; i++) {
            int auxiliar = i;
            int tempoTotal = 0;
            List<EtapaDeMontagem> listaDeCombinacoes = new ArrayList<EtapaDeMontagem>();

            while (auxiliar != numeroDeEtapas) {
                int contAuxiliar = auxiliar;
                auxiliar++;

                EtapaDeMontagem estapaDeMontagemAutal = etapasDeMontagemLista.get(contAuxiliar);
                int tempoDaEtapaAtual = estapaDeMontagemAutal.getTempoDeDuracao();

                if (tempoDaEtapaAtual + tempoTotal > TEMPO_TURNO_MANHA) {
                    continue;
                }

                listaDeCombinacoes.add(estapaDeMontagemAutal);
                tempoTotal += tempoDaEtapaAtual;

                if (tempoTotal == TEMPO_TURNO_MANHA) {
                    break;
                } else if (tempoTotal >= TEMPO_TURNO_MANHA) {
                    break;
                }

            }

            for (int j = 0; j < listaDeCombinacoes.size(); j++) {
                System.out.println("\nTeste " + j);
                System.out.println(listaDeCombinacoes.get(j).getNome() + listaDeCombinacoes.get(j).getTempoDeDuracao());
            }

        }

    }
}
