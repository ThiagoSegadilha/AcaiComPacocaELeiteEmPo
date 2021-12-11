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
        int TEMPO_DE_GINASTICA = 60;

        for (int i = 0; i < totalDeCombinacoes; i++) {
            List<EtapaDeMontagem> listaDeEtapas = new ArrayList<EtapaDeMontagem>();

            Date data = dataEHoraHelper.getDataInicial(9, 0, 0);

            numeroDaLinhaDeMontagem = i + 1;
            horaFormatada = dataEHoraHelper.formatarHora(data);

            System.out.println("Linha de montagem " + numeroDaLinhaDeMontagem + ":");

            exibeEtapasDoTurnoDaManha(combinacaoDeEtapasPorTurnoManha, i, listaDeEtapas, data);
            exibeEtapaDoAlmoco(TEMPO_DE_ALMOCO, listaDeEtapas, data);
            exibeEtapasDoTurnoDaTarde(combinacaoDeEtapasPorTurnoTarde, i, listaDeEtapas, data);
            exibeEtapaDaGinastica(TEMPO_DE_GINASTICA, listaDeEtapas);
        }
    }

    private void exibeEtapaDaGinastica(int TEMPO_DE_GINASTICA, List<EtapaDeMontagem> listaDeEtapas) {
        EtapaDeMontagem momentoGinastica = new EtapaDeMontagem("Ginástica Laboral", "Ginástica Laboral", TEMPO_DE_GINASTICA);
        momentoGinastica.setTempoPrevisto(horaFormatada);
        listaDeEtapas.add(momentoGinastica);
        System.out.println(horaFormatada + "Ginástica Laboral \n");
    }

    private void exibeEtapasDoTurnoDaTarde(List<List<EtapaDeMontagem>> combinacaoDeEtapasPorTurnoTarde, int i, List<EtapaDeMontagem> listaDeEtapas, Date data) {
        List<EtapaDeMontagem> etapaTurnoTarde = combinacaoDeEtapasPorTurnoTarde.get(i);
        for (EtapaDeMontagem etapaDeMontagem : etapaTurnoTarde) {
            etapaDeMontagem.setTempoPrevisto(horaFormatada);
            listaDeEtapas.add(etapaDeMontagem);
            System.out.println(horaFormatada + etapaDeMontagem.getTitulo());

            horaFormatada = getHoradaProximaEtapaDeMontagem(data, etapaDeMontagem.getTempoDeDuracao());
        }
    }

    private void exibeEtapaDoAlmoco(int TEMPO_DE_ALMOCO, List<EtapaDeMontagem> listaDeEtapas, Date data) {
        EtapaDeMontagem momentoDoAlmoco = new EtapaDeMontagem("Almoço", "Almoço", TEMPO_DE_ALMOCO);
        momentoDoAlmoco.setTempoPrevisto(horaFormatada);
        listaDeEtapas.add(momentoDoAlmoco);
        System.out.println(horaFormatada + "Almoço");

        horaFormatada = getHoradaProximaEtapaDeMontagem(data, TEMPO_DE_ALMOCO);
    }

    private void exibeEtapasDoTurnoDaManha(List<List<EtapaDeMontagem>> combinacaoDeEtapasPorTurnoManha, int i, List<EtapaDeMontagem> listaDeEtapas, Date data) {
        List<EtapaDeMontagem> etapaTurnoManha = combinacaoDeEtapasPorTurnoManha.get(i);
        for (EtapaDeMontagem etapaDeMontagem : etapaTurnoManha) {
            etapaDeMontagem.setTempoPrevisto(horaFormatada);
            System.out.println(horaFormatada + etapaDeMontagem.getTitulo());

            horaFormatada = getHoradaProximaEtapaDeMontagem(data, etapaDeMontagem.getTempoDeDuracao());
            listaDeEtapas.add(etapaDeMontagem);
        }
    }

    public String getHoradaProximaEtapaDeMontagem(Date data, int tempoDeDuracao) {
        long tempoDaEtapa = data.getTime();

        long tempoDuracao = tempoDeDuracao * 60 * 1000;
        long tempoIcrementado = tempoDaEtapa + tempoDuracao;

        data.setTime(tempoIcrementado);
        return dataEHoraHelper.getFormatoDeHoraSimples().format(data);
    }
}
