package model;

public class EtapaDeMontagem implements Comparable<Object>{
    String titulo, nome, tempoPrevisto;
    int tempoDeDuracao;
    boolean combinacaoDeEtapas = false;

    public EtapaDeMontagem(String titulo, String nome, int tempo) {
        this.titulo = titulo;
        this.nome =nome;
        this.tempoDeDuracao = tempo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getNome() {
        return nome;
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

    public boolean isCombinacaoDeEtapas() { return combinacaoDeEtapas; }

    public void setCombinacaoDeEtapas(boolean combinacaoDeEtapas) {
        this.combinacaoDeEtapas = combinacaoDeEtapas;
    }

    @Override // Ordenação por tempo de duração de cada etapa
    public int compareTo(Object obj) {
        EtapaDeMontagem etapaDeMontagem = (EtapaDeMontagem) obj;
        if (this.tempoDeDuracao > etapaDeMontagem.tempoDeDuracao)
            return -1;
        else if (this.tempoDeDuracao < etapaDeMontagem.tempoDeDuracao)
            return 1;
        else
            return 0;
    }
}
