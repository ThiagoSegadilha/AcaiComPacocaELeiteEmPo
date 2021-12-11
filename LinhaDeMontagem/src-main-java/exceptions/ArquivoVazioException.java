package exceptions;

import java.io.IOException;

public class ArquivoVazioException extends IOException {
    public  ArquivoVazioException(String mensagem) {
        super(mensagem);
    }
}
