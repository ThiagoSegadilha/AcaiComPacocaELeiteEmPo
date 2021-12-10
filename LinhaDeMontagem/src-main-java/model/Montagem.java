package model;

public class Montagem {
    String titulo, nome, tempoPrevisto;
    int tempoDeDuracao;
    boolean previsao = false;

    public Montagem(String titulo, String nome, int tempo) {
        this.titulo = titulo;
        this.nome =nome;
        this.tempoDeDuracao = tempo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTempoPrevisto() {
        return tempoPrevisto;
    }

    public void setTempoPrevisto(String tempoPrevisto) {
        this.tempoPrevisto = tempoPrevisto;
    }

    public int getTempoDeDuracao() {
        return tempoDeDuracao;
    }

    public void setTempoDeDuracao(int tempoDeDuracao) {
        this.tempoDeDuracao = tempoDeDuracao;
    }

    public boolean isPrevisao() {
        return previsao;
    }

    public void setPrevisao(boolean previsao) {
        this.previsao = previsao;
    }
}
