package model;

public class Produto {
    private int id;
    private String descricao;
    private String unidade;
    private double precoReferencia;

    public Produto(int id, String descricao, String unidade, double precoReferencia) {
        this.id = id;
        this.descricao = descricao;
        this.unidade = unidade;
        this.precoReferencia = precoReferencia;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public double getPrecoReferencia() {
        return precoReferencia;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s) - R$ %.2f", id, descricao, unidade, precoReferencia);
    }
}
