package main.empresa.aquisicoes.model;

public class Operador {
    private String nome;
    private String iniciais;

    public Operador(String nome, String iniciais) {
        this.nome = nome;
        this.iniciais = iniciais;
    }

    public String getNome() {
        return nome;
    }

    public String getIniciais() {
        return iniciais;
    }

    @Override
    public String toString() {
        return nome + " (" + iniciais + ")";
    }
}