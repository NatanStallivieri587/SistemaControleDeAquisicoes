package services;

import java.util.List;
import java.util.Scanner;

import model.*;

public class CriacaoPedidos {

    public static void registrarPedido(Scanner scanner, List<Pedido> todosPedidos, Operador operador,
                                       List<Fornecedor> fornecedores, List<Produto> produtos) {
        System.out.println();
        System.out.println("=== NOVO PEDIDO ===");

        if (operador.getIniciais().equals("--")) {
            System.out.println("Identifique o usuario atual (opcao 1) antes de registrar um pedido.");
            return;
        }

        Fornecedor fornecedor = escolherFornecedor(scanner, fornecedores);
        if (fornecedor == null) {
            System.out.println("Registro cancelado.");
            return;
        }

        Pedido pedido = new Pedido(operador, fornecedor);
        System.out.println("Funcionario: " + pedido.getOperador());
        System.out.println("Data: " + pedido.getData());
        System.out.println("Status inicial: " + pedido.getStatus());

        boolean adicionando = true;
        while (adicionando) {
            System.out.println();
            System.out.println("--- Item " + (pedido.getItens().size() + 1) + " ---");

            Produto produto = escolherProduto(scanner, produtos);
            if (produto == null) {
                System.out.println("Item cancelado.");
            } else {
                int quantidade = lerInteiroPositivo(scanner, "Quantidade: ");
                double preco = lerPreco(scanner, produto);
                pedido.adicionarItem(new ItemPedido(produto, quantidade, preco));
                System.out.println("Item adicionado.");
            }

            System.out.print("Adicionar outro item? (S/N): ");
            adicionando = scanner.nextLine().trim().equalsIgnoreCase("S");
        }

        if (pedido.getItens().isEmpty()) {
            System.out.println("Pedido sem itens nao pode ser registrado. Operacao cancelada.");
            return;
        }

        todosPedidos.add(pedido);
        System.out.println();
        System.out.println("Pedido registrado com sucesso!");
        System.out.println(pedido);
    }

    private static Fornecedor escolherFornecedor(Scanner scanner, List<Fornecedor> fornecedores) {
        System.out.println("Fornecedores:");
        fornecedores.forEach(f -> System.out.println("  " + f));
        while (true) {
            int id = lerInteiro(scanner, "ID do fornecedor (0 para cancelar): ");
            if (id == 0) return null;
            for (Fornecedor f : fornecedores) {
                if (f.getId() == id) return f;
            }
            System.out.println("Fornecedor nao encontrado.");
        }
    }

    private static Produto escolherProduto(Scanner scanner, List<Produto> produtos) {
        System.out.println("Produtos:");
        produtos.forEach(p -> System.out.println("  " + p));
        while (true) {
            int id = lerInteiro(scanner, "ID do produto (0 para cancelar): ");
            if (id == 0) return null;
            for (Produto p : produtos) {
                if (p.getId() == id) return p;
            }
            System.out.println("Produto nao encontrado.");
        }
    }

    private static double lerPreco(Scanner scanner, Produto produto) {
        while (true) {
            System.out.printf("Preco unitario [ENTER = referencia R$ %.2f]: ", produto.getPrecoReferencia());
            String entrada = scanner.nextLine().trim().replace(',', '.');
            if (entrada.isEmpty()) return produto.getPrecoReferencia();
            try {
                double preco = Double.parseDouble(entrada);
                if (preco > 0) return preco;
            } catch (NumberFormatException e) {
                // cai na mensagem abaixo
            }
            System.out.println("Preco invalido.");
        }
    }

    private static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro valido.");
            }
        }
    }

    private static int lerInteiroPositivo(Scanner scanner, String mensagem) {
        while (true) {
            int valor = lerInteiro(scanner, mensagem);
            if (valor > 0) return valor;
            System.out.println("O valor deve ser maior que zero.");
        }
    }
}