package model;

import exceptions.ArquivoVazioException;

import java.io.*;
import java.util.ArrayList;

public class CriaListaComOsDadosDoArquivo {
    private ArrayList<String> listaDeEtapas;

    public ArrayList<String> criaListaDeEtapas(String caminhoArquivo) {

        listaDeEtapas = new ArrayList<String>();

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaDeEtapas;
    }

    public void checaConteudoDoArquivo(BufferedReader buffer) throws IOException {
        int verificaConteudoDoArquivo = buffer.read();
        if (verificaConteudoDoArquivo == -1) {
            buffer.close();
            throw new ArquivoVazioException("O arquivo está vazio.");
        }
    }

}
