package controller;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaDeControle {

    private static ArrayList<String> listaDeEtapas;

    public static void tratarDadosDoArquivo(String caminhoArquivo) throws Exception {
        listaDeEtapas = new ArrayList<String>();

        FileInputStream lerArquivo = new FileInputStream(caminhoArquivo);
        DataInputStream dadosDoAquivo = new DataInputStream(lerArquivo);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(dadosDoAquivo));

        checaConteudoDoArquivo(buffer);

        String frase = buffer.readLine();

        System.out.println("Teste");
        System.out.println(frase);

        while (frase != null) {
            listaDeEtapas.add(frase);
            frase = buffer.readLine();
            System.out.println(frase);
        }
        System.out.println(listaDeEtapas);

        dadosDoAquivo.close();

    }

    private static void checaConteudoDoArquivo(BufferedReader buffer) throws Exception {
        int verificaConteudoDoArquivo = buffer.read();
        if (verificaConteudoDoArquivo == -1) {
            buffer.close();
            throw new Exception("O arquivo está vazio.");
        }
    }


}
