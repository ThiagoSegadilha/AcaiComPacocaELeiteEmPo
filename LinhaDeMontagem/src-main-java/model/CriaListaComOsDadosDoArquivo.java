package model;

import exceptions.ArquivoVazioException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CriaListaComOsDadosDoArquivo {
    private ArrayList<String> listaDeEtapas;

    public ArrayList<String> criaListaDeEtapas(String caminhoArquivo) {

        listaDeEtapas = new ArrayList<String>();

        try {
            File arquivo = new File(caminhoArquivo);
            Scanner lerArquivo = new Scanner(arquivo);

            while (lerArquivo.hasNextLine()) {
                String frase = lerArquivo.nextLine();
                listaDeEtapas.add(frase);
            }

            lerArquivo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaDeEtapas;
    }
}
