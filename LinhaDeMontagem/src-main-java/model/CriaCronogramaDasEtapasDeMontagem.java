package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CriaCronogramaDasEtapasDeMontagem {

    private int numeroDaLinhaDeMontagem;
    private String horaFormatada;

    public void criaCronogramaDasEtapasDeMontagem(List<List<EtapaDeMontagem>> combinacaoDeEtapasPorTurnoManha, List<List<EtapaDeMontagem>> combinacaoDeEtapasPorTurnoTarde) {
        int totalDeCombinacoes = combinacaoDeEtapasPorTurnoManha.size();
        int TEMPO_DE_ALMOCO = 60;

        for (int i = 0; i < totalDeCombinacoes; i++) {
            List<EtapaDeMontagem> listaDeEtapas = new ArrayList<EtapaDeMontagem>();


            Calendar date = Calendar.getInstance();
            date.set(Calendar.HOUR_OF_DAY, 9);
            date.set(Calendar.MINUTE, 0);
            date.set(Calendar.SECOND, 0);

            Date data = date.getTime();

            numeroDaLinhaDeMontagem = i + 1;
            horaFormatada = formatarHora(data);

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
        return getFormatoDeHoraSimples().format(data);
    }

    private SimpleDateFormat getFormatoDeHoraSimples() {
        return new SimpleDateFormat("HH:mm ");
    }

    private String formatarHora(Date date) {
        return getFormatoDeHoraSimples().format(date);
    }
}
