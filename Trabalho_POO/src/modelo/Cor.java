package modelo;

public class Cor {

    // atributos
    public static final Cor BRANCA = new Cor("BRANCA");
    public static final Cor PRETA = new Cor("PRETA");
    private String nome;

    // construtor
    public Cor(String nome) {
        this.nome = nome;
    }

    // getters & setters
    public String getNome() {
        return nome;
    }

    // métodos auxiliares
    public Cor oposta() {

        if (this == BRANCA) {
            return PRETA;
        } else {
            return BRANCA;
        }
    }
}
