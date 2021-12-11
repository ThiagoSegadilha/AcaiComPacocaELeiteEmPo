package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CriaCombinacoesDeEtapasDeMontagem {

    private List<List<EtapaDeMontagem>> combinacaoDeEtapasPorTurno;

    public void cronogramaDeMontagem(List<EtapaDeMontagem> etapasDeMontagemLista) {
        int TEMPO_TOTAL_DO_DIA = 360;
        int TEMPO_TURNO_MANHA = 180;
        int TEMPO_TURNO_TARDE = 240;
        int tempoTotalDasEtapas = getTempoTotalDasEtapas(etapasDeMontagemLista);
        int tempoTotalPorDia = tempoTotalDasEtapas / TEMPO_TOTAL_DO_DIA;

        // Necessario implementar o Comparable<Object> na classe EtapaDeMontagem para realizar a ordenação
        Collections.sort(etapasDeMontagemLista);

        for (EtapaDeMontagem etapa : etapasDeMontagemLista) {
            System.out.println(etapa.getNome() + " " + etapa.getTempoDeDuracao());
        }

        combinacaoDeEtapasPorTurno = combinacoesPorTurno(etapasDeMontagemLista, tempoTotalPorDia, TEMPO_TURNO_MANHA);

        removeEtapasDaListaDeMontagem(combinacaoDeEtapasPorTurno, etapasDeMontagemLista);

        for (EtapaDeMontagem etapaDeMontagemAtual : etapasDeMontagemLista) {
            System.out.println("\nTeste 2");
            System.out.println(etapaDeMontagemAtual.getNome() + " " + etapaDeMontagemAtual.getTempoDeDuracao());
        }
        combinacaoDeEtapasPorTurno = combinacoesPorTurno(etapasDeMontagemLista, tempoTotalPorDia, TEMPO_TURNO_TARDE);

        removeEtapasDaListaDeMontagem(combinacaoDeEtapasPorTurno, etapasDeMontagemLista);

        for (EtapaDeMontagem etapaDeMontagemAtual : etapasDeMontagemLista) {
            System.out.println("\nTeste 3");
            System.out.println(etapaDeMontagemAtual.getNome() + " " + etapaDeMontagemAtual.getTempoDeDuracao());
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

    public List<List<EtapaDeMontagem>> combinacoesPorTurno(List<EtapaDeMontagem> etapasDeMontagemLista, int TEMPO_TOTAL_DO_DIA, int TEMPO_DO_TURNO) {

        int numeroDeEtapas = etapasDeMontagemLista.size();
        List<List<EtapaDeMontagem>> combinacaoDeEtapas = new ArrayList<List<EtapaDeMontagem>>();
        int numeroDePossiveisCombinacoes = 0;

        for (int i = 0; i < numeroDeEtapas; i++) {
            int auxiliar = i;
            int tempoTotal = 0;
            List<EtapaDeMontagem> listaDeCombinacoes = new ArrayList<EtapaDeMontagem>();
            boolean combinacaoValida = false;

            tempoTotal = getTempoTotalDaCominacaoPorTurno(etapasDeMontagemLista, TEMPO_DO_TURNO, numeroDeEtapas, auxiliar, tempoTotal, listaDeCombinacoes);

            combinacaoValida = tempoTotal <= TEMPO_DO_TURNO;
            if (combinacaoValida) {
                combinacaoDeEtapas.add(listaDeCombinacoes);
                for (EtapaDeMontagem etapaDeMontagemAtual : listaDeCombinacoes) {
                    etapaDeMontagemAtual.setEtapaCombinada(true);
                }
                numeroDePossiveisCombinacoes++;
                if (numeroDePossiveisCombinacoes == TEMPO_TOTAL_DO_DIA) {
                    break;
                }
            }
        }
        for (List<EtapaDeMontagem> etapaDeMontagemAtual : combinacaoDeEtapas) {
            for (EtapaDeMontagem etapaAtual : etapaDeMontagemAtual) {
                System.out.println("\nTeste ");
                System.out.println(etapaAtual.getNome() + " " + etapaAtual.getTempoDeDuracao());
            }
        }
        return combinacaoDeEtapas;
    }

    private int getTempoTotalDaCominacaoPorTurno(List<EtapaDeMontagem> etapasDeMontagemLista, int TEMPO_POR_TURNO, int numeroDeEtapas, int auxiliar, int tempoTotal, List<EtapaDeMontagem> listaDeCombinacoes) {
        while (auxiliar != numeroDeEtapas) {
            int contAuxiliar = auxiliar;
            auxiliar++;

            EtapaDeMontagem estapaDeMontagemAutal = etapasDeMontagemLista.get(contAuxiliar);

            if (estapaDeMontagemAutal.isEtapaCombinada()) {
                continue;
            }

            int tempoDaEtapaAtual = estapaDeMontagemAutal.getTempoDeDuracao();
            if (tempoDaEtapaAtual + tempoTotal > TEMPO_POR_TURNO) {
                continue;
            }

            listaDeCombinacoes.add(estapaDeMontagemAutal);
            tempoTotal += tempoDaEtapaAtual;

            if (tempoTotal >= TEMPO_POR_TURNO) {
                break;
            }
        }
        return tempoTotal;
    }

    private void removeEtapasDaListaDeMontagem(List<List<EtapaDeMontagem>> combinacaoDeEtapasPorTurno, List<EtapaDeMontagem> etapasDeMontagemLista) {
        for (List<EtapaDeMontagem> etapaDeMontagem : combinacaoDeEtapasPorTurno) {
            etapasDeMontagemLista.removeAll(etapaDeMontagem);
        }
    }
}
