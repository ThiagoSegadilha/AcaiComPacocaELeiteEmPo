package controller;

import model.CriaCombinacoesDeEtapasDeMontagem;
import model.CriaListaDeEtapasDeMontagem;
import model.EtapaDeMontagem;
import model.ListaDeEtapas;

import java.text.SimpleDateFormat;
import java.util.*;

public class SistemaDeControle {

    private ListaDeEtapas listaDeEtapas;
    private CriaCombinacoesDeEtapasDeMontagem criaCombinacoesDeEtapasDeMontagem;
    private int numeroDaLinhaDeMontagem;
    private String horaFormatada;

    public SistemaDeControle() {
        listaDeEtapas = new ListaDeEtapas();
        criaCombinacoesDeEtapasDeMontagem = new CriaCombinacoesDeEtapasDeMontagem();
    }

    public void tratarDadosDoArquivo(String caminhoArquivo) throws Exception {

        List<String> listaDeEtapasDeMontagem = listaDeEtapas.criaListaDeEtapas(caminhoArquivo);
        List<EtapaDeMontagem> etapasDeMontagemLista = CriaListaDeEtapasDeMontagem.criaLinhaDeMontagem(listaDeEtapasDeMontagem);

//        List<List<EtapaDeMontagem>> listaDeCombinacoesDasEtapasDeMontagem = criaCombinacoesDeEtapasDeMontagem.cronogramaDeMontagem(etapasDeMontagemLista);

        criaCombinacoesDeEtapasDeMontagem.cronogramaDeMontagem(etapasDeMontagemLista);
//        criaCronogramaDasEtapasDeMontagem(listaDeCombinacoesDasEtapasDeMontagem);
    }

    private void criaCronogramaDasEtapasDeMontagem(List<List<EtapaDeMontagem>> listaDeCombinacoesDasEtapasDeMontagem) {
        int totalDeCombinacoes = listaDeCombinacoesDasEtapasDeMontagem.size();
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

            List<EtapaDeMontagem> mornSessionConversationList = listaDeCombinacoesDasEtapasDeMontagem.get(i);
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
            List<EtapaDeMontagem> eveSessionConversationList = listaDeCombinacoesDasEtapasDeMontagem.get(i);
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
