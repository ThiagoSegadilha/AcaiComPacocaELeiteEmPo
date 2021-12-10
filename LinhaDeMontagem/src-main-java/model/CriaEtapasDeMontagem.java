package model;

import java.util.ArrayList;
import java.util.List;

public class CriaEtapasDeMontagem {

    private static List<EtapaDeMontagem> listaDeEtapaDeMontagem;

    public static void criaLinhaDeMontagem(ArrayList<String> listaDeEtapas) {
        listaDeEtapaDeMontagem = new ArrayList<EtapaDeMontagem>();
        int tempo = 0;
        int cont = 0;

        String manutencao = "maintenance";
        String min = "min";

        for (String etapa : listaDeEtapas) {
            int espacoEntreEtapaEHora = etapa.lastIndexOf(" ");

            String nome = etapa.substring(0, espacoEntreEtapaEHora);
            String tempoDeDuracao = etapa.substring(espacoEntreEtapaEHora + 1);

            if (tempoDeDuracao.endsWith(min)) {
                tempo = Integer.parseInt(tempoDeDuracao.substring(0, tempoDeDuracao.indexOf(min)));
            } else if (tempoDeDuracao.endsWith(manutencao)) {
                String tempoDeManutencao = tempoDeDuracao.substring(0, tempoDeDuracao.indexOf(manutencao));
                if ("".equals(tempoDeManutencao)) {
                    tempo = 5;
                }
            }

            listaDeEtapaDeMontagem.add(new EtapaDeMontagem(etapa, nome, tempo));

            System.out.println(listaDeEtapaDeMontagem.get(cont).getNome() + " " + listaDeEtapaDeMontagem.get(cont).getTempoDeDuracao());
            cont++;

        }

    }
}
