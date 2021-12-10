package controller;

import model.Montagem;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaDeControle {

    private static ArrayList<String> listaDeEtapas;
    private static List<Montagem> listaDeMontagem;

    public static void tratarDadosDoArquivo(String caminhoArquivo) throws Exception {
        listaDeEtapas = new ArrayList<String>();

        FileInputStream lerArquivo = new FileInputStream(caminhoArquivo);
        DataInputStream dadosDoAquivo = new DataInputStream(lerArquivo);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(dadosDoAquivo));

        checaConteudoDoArquivo(buffer);

        String frase = buffer.readLine();

        while (frase != null) {
            listaDeEtapas.add(frase);
            frase = buffer.readLine();
        }

        dadosDoAquivo.close();

        criaLinhaDeMontagem(listaDeEtapas);

    }

    private static void checaConteudoDoArquivo(BufferedReader buffer) throws Exception {
        int verificaConteudoDoArquivo = buffer.read();
        if (verificaConteudoDoArquivo == -1) {
            buffer.close();
            throw new Exception("O arquivo está vazio.");
        }
    }

    private static void criaLinhaDeMontagem(ArrayList<String> listaDeEtapas) {
        listaDeMontagem = new ArrayList<Montagem>();
        int tempo = 0;
        int cont = 0;

        String manutencao = "maintenance";
        String min = "min";

        for (String etapa : listaDeEtapas) {
            int espacoEntreEtapaEHora = etapa.lastIndexOf(" ");

            String nome = etapa.substring(0, espacoEntreEtapaEHora);
            String tempoDeDuracao = etapa.substring(espacoEntreEtapaEHora + 1);

            System.out.println("Teste " + nome + " " + tempoDeDuracao);

            if (tempoDeDuracao.endsWith(min)) {
                tempo = Integer.parseInt(tempoDeDuracao.substring(0, tempoDeDuracao.indexOf(min)));
                System.out.println(tempo);
            } else if (tempoDeDuracao.endsWith(manutencao)) {
                String tempoDeManutencao = tempoDeDuracao.substring(0, tempoDeDuracao.indexOf(manutencao));
                if ("".equals(tempoDeManutencao)) {
                    tempo = 5;
                }
            }
            System.out.println(tempo);

            listaDeMontagem.add(new Montagem(etapa, nome, tempo));

            System.out.println(listaDeMontagem.get(cont).getNome() + " " + listaDeMontagem.get(cont).getTempoPrevisto());
            cont++;

        }

    }

}
