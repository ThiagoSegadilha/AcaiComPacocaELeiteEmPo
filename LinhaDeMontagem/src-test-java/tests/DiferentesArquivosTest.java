package tests;

import exceptions.ArquivoVazioException;
import main.Main;
import model.CriaListaComOsDadosDoArquivo;
import org.junit.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class DiferentesArquivosTest {

    @Test(expected = FileNotFoundException.class)
    public void arquivoInexistente() throws Throwable {
        Main main = new Main();
        Main.checaArquivo("LinhaDeMontagem/arquivos/input123.txt");
    }

    @Test(expected = ArquivoVazioException.class)
    public void arquivoVazio() throws Throwable {
        CriaListaComOsDadosDoArquivo criaListaComOsDadosDoArquivo = new CriaListaComOsDadosDoArquivo();
        FileInputStream lerArquivo = new FileInputStream("LinhaDeMontagem/arquivos/inputVazio.txt");
        DataInputStream dadosDoAquivo = new DataInputStream(lerArquivo);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(dadosDoAquivo));

        criaListaComOsDadosDoArquivo.checaConteudoDoArquivo(buffer);
    }
}
