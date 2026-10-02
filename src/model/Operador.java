package model;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operador operador = (Operador) o;
        return (nome != null && operador.nome != null ? nome.equalsIgnoreCase(operador.nome) : java.util.Objects.equals(nome, operador.nome))
                && (iniciais != null && operador.iniciais != null ? iniciais.equalsIgnoreCase(operador.iniciais) : java.util.Objects.equals(iniciais, operador.iniciais));
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                nome != null ? nome.toLowerCase() : null,
                iniciais != null ? iniciais.toLowerCase() : null
        );
    }

    @Override
    public String toString() {
        return nome + " (" + iniciais + ")";
    }
}