package view;

import controller.SistemaDeControle;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner dadosDoTeclado = new Scanner(System.in);
        String nome;

        System.out.println("Confira se Arquivo de input esta na pasta Arquivos \n");

        System.out.println("Digite o nome do Arquivo .txt com os dados do teste: ");
        nome = dadosDoTeclado.next();

        if (!nome.contains(".txt")) {
            nome += ".txt";
        }

        try {
            checaArquivo(nome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checaArquivo(String nomeArquivo) throws Exception {

        String caminhoArquivo = "LinhaDeMontagem/Arquivos/" + nomeArquivo;

        if (!new File(caminhoArquivo).exists()) {
            throw new Exception("O sistema não pode encontrar o arquivo especificado.");
        }

        executaArquivo(caminhoArquivo);
    }

    private static void executaArquivo(String caminhoArquivo) {

        try {
            SistemaDeControle.tratarDadosDoArquivo(caminhoArquivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
