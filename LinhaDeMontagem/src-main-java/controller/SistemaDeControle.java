package controller;

import java.io.*;
import java.util.List;

public class SistemaDeControle {

    public static void tratarDadosDoArquivo(String caminhoArquivo) throws Exception {
        FileInputStream lerArquivo = new FileInputStream(caminhoArquivo);
        DataInputStream dadosDoAquivo = new DataInputStream(lerArquivo);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(dadosDoAquivo));

        checaConteudoDoArquivo(buffer);

        String frase = buffer.readLine();

        System.out.println(frase);
        System.out.println("Teste");
    }

    private static void checaConteudoDoArquivo(BufferedReader buffer) throws Exception {
        int verificaConteudoDoArquivo = buffer.read();
        if (verificaConteudoDoArquivo == -1) {
            buffer.close();
            throw new Exception("O arquivo está vazio.");
        }
    }
}
