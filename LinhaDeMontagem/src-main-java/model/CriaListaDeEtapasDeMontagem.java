package model;

import exceptions.EtapaSemNomeException;
import exceptions.EtapaSemTempoException;

import java.util.ArrayList;
import java.util.List;

public class CriaListaDeEtapasDeMontagem {

    private static List<EtapaDeMontagem> listaDeEtapaDeMontagem;

    public static List<EtapaDeMontagem> criaLinhaDeMontagem(List<String> listaDeEtapas) throws Throwable {
        listaDeEtapaDeMontagem = new ArrayList<EtapaDeMontagem>();
        int tempo = 0;

        String manutencao = "maintenance";
        String min = "min";

        for (String etapa : listaDeEtapas) {
            int espacoEntreEtapaEHora = etapa.lastIndexOf(" ");

            String nome = etapa.substring(0, espacoEntreEtapaEHora);
            String tempoDeDuracao = etapa.substring(espacoEntreEtapaEHora + 1);

            checaTempoENome(tempoDeDuracao, nome, min, manutencao);

            if (tempoDeDuracao.endsWith(min)) {
                tempo = Integer.parseInt(tempoDeDuracao.substring(0, tempoDeDuracao.indexOf(min)));
            } else if (tempoDeDuracao.endsWith(manutencao)) {
                String tempoDeManutencao = tempoDeDuracao.substring(0, tempoDeDuracao.indexOf(manutencao));
                if ("".equals(tempoDeManutencao)) {
                    tempo = 5;
                }
            }

            listaDeEtapaDeMontagem.add(new EtapaDeMontagem(etapa, nome, tempo));
        }

        return listaDeEtapaDeMontagem;
    }

    private static void checaTempoENome(String tempo, String nome, String min, String manutencao) throws Exception {
        if (!tempo.endsWith(min) && !tempo.endsWith(manutencao)) {
            throw new EtapaSemTempoException("A etapa atual não possui tempo");
        }
        if (nome.isEmpty()) {
            throw new EtapaSemNomeException("Não existe nenhuma etapa na posição atual da lista");
        }
    }
}
