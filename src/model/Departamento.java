package model;

public class Departamento {
    private String nome;
    private double limiteMaximoPedido;

    public Departamento(String nome, double limiteMaximoPedido) {
        this.nome = nome;
        this.limiteMaximoPedido = limiteMaximoPedido;
    }

    public String getNome() {
        return nome;
    }

    public double getLimiteMaximoPedido() {
        return limiteMaximoPedido;
    }

    @Override
    public String toString() {
        return nome + " (Limite: R$ " + String.format("%.2f", limiteMaximoPedido) + ")";
    }
}
