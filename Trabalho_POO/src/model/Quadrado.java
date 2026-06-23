package model;

public class Quadrado {

    // atributos
    private Posicao posicao;
    private String icone;

    public Quadrado() {}

    public Quadrado(Posicao posicao, String icone) {
        this.posicao = posicao;
        this.icone = icone;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public String getIcone() {
        return icone;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

}
