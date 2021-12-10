package model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListaDeEtapas {
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

    private void checaConteudoDoArquivo(BufferedReader buffer) throws Exception {
        int verificaConteudoDoArquivo = buffer.read();
        if (verificaConteudoDoArquivo == -1) {
            buffer.close();
            throw new Exception("O arquivo está vazio.");
        }
    }

}
