package model;

public class Fornecedor {
    private int id;
    private String nome;
    private String cnpj;
    private String contato;

    public Fornecedor(int id, String nome, String cnpj, String contato) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.contato = contato;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getContato() {
        return contato;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - CNPJ: %s - Contato: %s", id, nome, cnpj, contato);
    }
}
