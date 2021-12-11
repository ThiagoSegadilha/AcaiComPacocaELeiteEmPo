package main;

import controller.SistemaDeControle;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner dadosDoTeclado = new Scanner(System.in);
        String nomeArquivo;

        System.out.println("Confira se Arquivo de input esta na pasta Arquivos \n");

        System.out.println("Digite o nome do Arquivo .txt com os dados do teste: ");
        nomeArquivo = dadosDoTeclado.next();

        if (!nomeArquivo.contains(".txt")) {
            nomeArquivo += ".txt";
        }

        try {
            checaArquivo(nomeArquivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checaArquivo(String nomeArquivo) throws Exception {

        String caminhoArquivo = "LinhaDeMontagem/arquivos/" + nomeArquivo;

        if (!new File(caminhoArquivo).exists()) {
            throw new FileNotFoundException("O sistema não pode encontrar o arquivo especificado.");
        }
        executaArquivo(caminhoArquivo);
    }

    private static void executaArquivo(String caminhoArquivo) {
        SistemaDeControle sistemaDeControle = new SistemaDeControle();
        try {
            sistemaDeControle.tratarDadosDoArquivo(caminhoArquivo);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
