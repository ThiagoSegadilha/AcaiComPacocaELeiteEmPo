package view;

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
            FileInputStream lerArquivo = new FileInputStream(caminhoArquivo);
            DataInputStream dadosDoAquivo = new DataInputStream(lerArquivo);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(dadosDoAquivo));

            checaConteudoDoArquivo(buffer);

            String frase = buffer.readLine();

            System.out.println(frase);
            System.out.println("Teste");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checaConteudoDoArquivo(BufferedReader buffer) throws Exception {
        int verificaConteudoDoArquivo = buffer.read();
        if (verificaConteudoDoArquivo == -1) {
            buffer.close();
            throw new Exception("O arquivo está vazio.");
        }
    }
}
