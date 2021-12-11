package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CriaCombinacoesDeEtapasDeMontagem {

    private List<List<EtapaDeMontagem>> combinacaoDeEtapasTurnoManha;

    public void cronogramaDeMontagem(List<EtapaDeMontagem> etapasDeMontagemLista) {
        int TEMPO_TOTAL_DO_DIA = 360;
        int tempoTotalDasEtapas = getTempoTotalDasEtapas(etapasDeMontagemLista);
        int tempoTotalPorDia = tempoTotalDasEtapas / TEMPO_TOTAL_DO_DIA;

        // Necessario implementar o Comparable<Object> na classe EtapaDeMontagem para realizar a ordenação
        Collections.sort(etapasDeMontagemLista);

        for (EtapaDeMontagem etapa : etapasDeMontagemLista) {
            System.out.println(etapa.getNome() + " " + etapa.getTempoDeDuracao());
        }

        combinacaoDeEtapasTurnoManha = combinacoesTurnoManha(etapasDeMontagemLista, tempoTotalPorDia);

        removeEtapasDaListaDeMontagem(combinacaoDeEtapasTurnoManha, etapasDeMontagemLista);

        for (EtapaDeMontagem etapaDeMontagemAtual : etapasDeMontagemLista) {
            System.out.println("\nTeste 2");
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

    public List<List<EtapaDeMontagem>> combinacoesTurnoManha(List<EtapaDeMontagem> etapasDeMontagemLista, int tempoTotalPorDia) {
        int TEMPO_TURNO_MANHA = 180;
        int numeroDeEtapas = etapasDeMontagemLista.size();
        List<List<EtapaDeMontagem>> combinacaoDeEtapas = new ArrayList<List<EtapaDeMontagem>>();
        int numeroDePossiveisCombinacoes = 0;

        for (int i = 0; i < numeroDeEtapas; i++) {
            int auxiliar = i;
            int tempoTotal = 0;
            List<EtapaDeMontagem> listaDeCombinacoes = new ArrayList<EtapaDeMontagem>();
            boolean combinacaoValida = false;

            while (auxiliar != numeroDeEtapas) {
                int contAuxiliar = auxiliar;
                auxiliar++;

                EtapaDeMontagem estapaDeMontagemAutal = etapasDeMontagemLista.get(contAuxiliar);

                if (estapaDeMontagemAutal.isEtapaCombinada()) {
                    continue;
                }

                int tempoDaEtapaAtual = estapaDeMontagemAutal.getTempoDeDuracao();
                if (tempoDaEtapaAtual + tempoTotal > TEMPO_TURNO_MANHA) {
                    continue;
                }

                listaDeCombinacoes.add(estapaDeMontagemAutal);
                tempoTotal += tempoDaEtapaAtual;

                if (tempoTotal >= TEMPO_TURNO_MANHA) {
                    break;
                }
            }

            combinacaoValida = tempoTotal == TEMPO_TURNO_MANHA;
            if (combinacaoValida) {
                combinacaoDeEtapas.add(listaDeCombinacoes);
                for (EtapaDeMontagem etapaDeMontagemAtual : listaDeCombinacoes) {
                    etapaDeMontagemAtual.setEtapaCombinada(true);
                }
                numeroDePossiveisCombinacoes++;
                if (numeroDePossiveisCombinacoes == tempoTotalPorDia) {
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

    private void removeEtapasDaListaDeMontagem(List<List<EtapaDeMontagem>> combinacaoDeEtapasTurnoManha, List<EtapaDeMontagem> etapasDeMontagemLista) {
        for (List<EtapaDeMontagem> etapaDeMontagem : combinacaoDeEtapasTurnoManha) {
            etapasDeMontagemLista.removeAll(etapaDeMontagem);
        }
    }
}
