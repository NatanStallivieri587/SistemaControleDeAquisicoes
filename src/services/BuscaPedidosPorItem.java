package services;

import java.util.List;
import java.util.Scanner;

import model.ItemPedido;
import model.Pedido;

public class BuscaPedidosPorItem {

    public static void buscarPorPalavraChave(Scanner scanner, List<Pedido> todosPedidos) {
        System.out.println();
        System.out.println("=== BUSCA DE PEDIDOS POR ITEM ===");

        if (todosPedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
            return;
        }

        System.out.print("Digite a palavra-chave do produto/item: ");
        String termo = scanner.nextLine().trim();

        if (termo.isEmpty()) {
            System.out.println("Termo de busca nao pode estar vazio.");
            return;
        }

        String termoBusca = termo.toLowerCase();
        boolean encontrou = false;

        for (Pedido pedido : todosPedidos) {
            for (ItemPedido item : pedido.getItens()) {
                String descricao = item.getProduto().getDescricao().toLowerCase();
                if (descricao.contains(termoBusca)) {
                    if (!encontrou) {
                        System.out.println("Pedidos encontrados:");
                        encontrou = true;
                    }
                    System.out.println("- " + pedido);
                    break;
                }
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum pedido encontrado para a palavra-chave: " + termo);
        }
    }
}