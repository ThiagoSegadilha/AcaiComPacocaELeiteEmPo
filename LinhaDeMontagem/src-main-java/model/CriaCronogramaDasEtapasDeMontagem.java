package model;

import common.DataEHoraHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CriaCronogramaDasEtapasDeMontagem {

    private int numeroDaLinhaDeMontagem;
    private String horaFormatada;
    private DataEHoraHelper dataEHoraHelper;

    public CriaCronogramaDasEtapasDeMontagem() {
        dataEHoraHelper = new DataEHoraHelper();
    }

    public void criaCronogramaDasEtapasDeMontagem(List<List<EtapaDeMontagem>> combinacaoDeEtapasPorTurnoManha, List<List<EtapaDeMontagem>> combinacaoDeEtapasPorTurnoTarde) {
        int totalDeCombinacoes = combinacaoDeEtapasPorTurnoManha.size();
        int TEMPO_DE_ALMOCO = 60;

        for (int i = 0; i < totalDeCombinacoes; i++) {
            List<EtapaDeMontagem> listaDeEtapas = new ArrayList<EtapaDeMontagem>();


            Date data = dataEHoraHelper.getDataInicial(9, 0, 0);

            numeroDaLinhaDeMontagem = i + 1;
            horaFormatada = dataEHoraHelper.formatarHora(data);

            System.out.println("Linha de montagem " + numeroDaLinhaDeMontagem + ":");

            List<EtapaDeMontagem> mornSessionConversationList = combinacaoDeEtapasPorTurnoManha.get(i);
            for (EtapaDeMontagem etapaDeMontagem : mornSessionConversationList) {
                etapaDeMontagem.setTempoPrevisto(horaFormatada);
                System.out.println(horaFormatada + etapaDeMontagem.getTitulo());

                horaFormatada = getHoradaProximaEtapaDeMontagem(data, etapaDeMontagem.getTempoDeDuracao());
                listaDeEtapas.add(etapaDeMontagem);
            }

            EtapaDeMontagem momentoDoAlmoco = new EtapaDeMontagem("Almoço", "Almoço", TEMPO_DE_ALMOCO);
            momentoDoAlmoco.setTempoPrevisto(horaFormatada);
            listaDeEtapas.add(momentoDoAlmoco);
            System.out.println(horaFormatada + "Almoço");

            horaFormatada = getHoradaProximaEtapaDeMontagem(data, TEMPO_DE_ALMOCO);
            List<EtapaDeMontagem> eveSessionConversationList = combinacaoDeEtapasPorTurnoTarde.get(i);
            for (EtapaDeMontagem etapaDeMontagem : eveSessionConversationList) {
                etapaDeMontagem.setTempoPrevisto(horaFormatada);
                listaDeEtapas.add(etapaDeMontagem);
                System.out.println(horaFormatada + etapaDeMontagem.getTitulo());

                horaFormatada = getHoradaProximaEtapaDeMontagem(data, etapaDeMontagem.getTempoDeDuracao());
            }

            EtapaDeMontagem momentoGinastica = new EtapaDeMontagem("Ginástica Laboral", "Ginástica Laboral", 60);
            momentoGinastica.setTempoPrevisto(horaFormatada);
            listaDeEtapas.add(momentoGinastica);
            System.out.println(horaFormatada + "Ginástica Laboral \n");
        }
    }

    public String getHoradaProximaEtapaDeMontagem(Date data, int tempoDeDuracao) {
        long tempoDaEtapa = data.getTime();

        long timeDurationInLong = tempoDeDuracao * 60 * 1000;
        long newTimeInLong = tempoDaEtapa + timeDurationInLong;

        data.setTime(newTimeInLong);
        return dataEHoraHelper.getFormatoDeHoraSimples().format(data);
    }
}
