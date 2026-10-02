package services;

import java.util.List;
import java.util.Scanner;

import model.Operador;
import model.Pedido;
import model.StatusPedido;

public class ExclusaoPedidos {

    /**
     * Valida se um pedido pode ser excluido pelo operador informado.
     * 
     * @param pedido O pedido a ser avaliado.
     * @param operador O operador que esta solicitando a exclusao.
     * @return Mensagem de erro caso a exclusao nao seja permitida, ou null se for permitida.
     */
    public static String validarExclusao(Pedido pedido, Operador operador) {
        if (pedido == null) {
            return "Pedido nao encontrado ou invalido.";
        }

        if (operador == null || operador.getIniciais() == null || operador.getIniciais().equals("--")) {
            return "Identifique o usuario atual antes de tentar excluir um pedido.";
        }

        if (!operador.equals(pedido.getOperador())) {
            return String.format(
                    "Operacao negada: o pedido #%d pertence ao funcionario '%s'. Somente o criador pode exclui-lo.",
                    pedido.getId(),
                    pedido.getOperador()
            );
        }

        if (pedido.getStatus() != StatusPedido.ABERTO) {
            return String.format(
                    "Operacao negada: o pedido #%d possui status '%s'. Somente pedidos com status 'ABERTO' podem ser excluidos.",
                    pedido.getId(),
                    pedido.getStatus()
            );
        }

        return null;
    }

    /**
     * Verifica se o pedido pode ser excluido pelo operador.
     * 
     * @param pedido Pedido a verificar.
     * @param operador Operador solicitante.
     * @return true se permitido, false caso contrario.
     */
    public static boolean podeExcluir(Pedido pedido, Operador operador) {
        return validarExclusao(pedido, operador) == null;
    }

    /**
     * Fluxo interativo no console para exclusao de pedidos.
     * 
     * @param scanner Scanner para leitura da entrada do usuario.
     * @param todosPedidos Lista com todos os pedidos do sistema.
     * @param operadorAtual Operador que esta atualmente logado/ativo no sistema.
     */
    public static void excluirPedido(Scanner scanner, List<Pedido> todosPedidos, Operador operadorAtual) {
        System.out.println();
        System.out.println("=== EXCLUSAO DE PEDIDO ===");

        if (operadorAtual == null || operadorAtual.getIniciais() == null || operadorAtual.getIniciais().equals("--")) {
            System.out.println("Identifique o usuario atual (opcao 1) antes de excluir um pedido.");
            return;
        }

        if (todosPedidos == null || todosPedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado no sistema.");
            return;
        }

        System.out.println("Pedidos cadastrados no sistema:");
        for (Pedido p : todosPedidos) {
            System.out.printf("  [#%d] Solicitante: %s | Status: %s | Itens: %d | Total: R$ %.2f%n",
                    p.getId(), p.getOperador(), p.getStatus(), p.getItens().size(), p.getTotal());
        }

        int idPedido = lerInteiro(scanner, "Informe o ID do pedido que deseja excluir (0 para cancelar): ");
        if (idPedido == 0) {
            System.out.println("Operacao cancelada.");
            return;
        }

        Pedido pedidoEncontrado = null;
        for (Pedido p : todosPedidos) {
            if (p.getId() == idPedido) {
                pedidoEncontrado = p;
                break;
            }
        }

        if (pedidoEncontrado == null) {
            System.out.println("Pedido com ID " + idPedido + " nao encontrado.");
            return;
        }

        String erroValidacao = validarExclusao(pedidoEncontrado, operadorAtual);
        if (erroValidacao != null) {
            System.out.println(erroValidacao);
            return;
        }

        System.out.println();
        System.out.println("Detalhes do pedido selecionado:");
        System.out.println(pedidoEncontrado);

        System.out.print("\nTem certeza de que deseja excluir o pedido #" + pedidoEncontrado.getId() + "? (S/N): ");
        String confirmacao = scanner.nextLine().trim();

        if (confirmacao.equalsIgnoreCase("S")) {
            todosPedidos.remove(pedidoEncontrado);
            System.out.println("Pedido #" + pedidoEncontrado.getId() + " excluido com sucesso!");
        } else {
            System.out.println("Exclusao cancelada pelo usuario.");
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
}
