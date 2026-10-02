package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
    private static int proximoId = 1;

    private final int id;
    private final Operador operador;
    private final Fornecedor fornecedor;
    private final LocalDate data;
    private StatusPedido status;
    private final List<ItemPedido> itens = new ArrayList<>();

    public Pedido(Operador operador, Fornecedor fornecedor) {
        this.id = proximoId++;
        this.operador = operador;
        this.fornecedor = fornecedor;
        this.data = LocalDate.now();
        this.status = StatusPedido.ABERTO;
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    public double getTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    public int getId() { return id; }
    public Operador getOperador() { return operador; }
    public Fornecedor getFornecedor() { return fornecedor; }
    public LocalDate getData() { return data; }
    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }
    public List<ItemPedido> getItens() { return Collections.unmodifiableList(itens); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Pedido #%d | Data: %s | Status: %s%n", id, data, status));
        sb.append("Funcionario: ").append(operador).append(System.lineSeparator());
        sb.append("Fornecedor: ").append(fornecedor).append(System.lineSeparator());
        sb.append("Itens:").append(System.lineSeparator());
        for (ItemPedido item : itens) {
            sb.append("  - ").append(item).append(System.lineSeparator());
        }
        sb.append(String.format("TOTAL: R$ %.2f", getTotal()));
        return sb.toString();
    }
}