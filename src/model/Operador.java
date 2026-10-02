package model;

public class Operador {
    private String nome;
    private String iniciais;
    private Departamento departamento;
    private boolean isAdministrador;

    public Operador(String nome, String iniciais, Departamento departamento, boolean isAdministrador) {
        this.nome = nome;
        this.iniciais = iniciais;
        this.departamento = departamento;
        this.isAdministrador = isAdministrador;
    }

    public String getNome() {
        return nome;
    }

    public String getIniciais() {
        return iniciais;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public boolean isAdministrador() {
        return isAdministrador;
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
        String adminStr = isAdministrador ? " [Admin]" : "";
        String deptoStr = (departamento != null) ? " - Depto: " + departamento.getNome() : "";
        return nome + " (" + iniciais + ")" + adminStr + deptoStr;
    }
}