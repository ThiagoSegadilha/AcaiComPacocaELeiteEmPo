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

        String manutencao = "maintenance";

        for (String etapa : listaDeEtapas) {
            int espacoEntreEtapaEHora = etapa.lastIndexOf(" ");

            String nome = etapa.substring(0, espacoEntreEtapaEHora);
            String tempo = etapa.substring(espacoEntreEtapaEHora +1);

            System.out.println("Teste " + nome + " " + tempo);
        }

    }

}
