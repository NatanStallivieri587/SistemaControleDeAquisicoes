import java.util.*;
import model.Fornecedor;
import model.Operador;
import model.Pedido;
import model.Produto;
import services.*;
import model.Departamento;


public class Main {
    
    private static final List<Departamento> departamentos = new ArrayList<>();
    private static final List<Fornecedor> fornecedores = new ArrayList<>();
    private static final List<Produto> produtos = new ArrayList<>();
    private static final List<Pedido> todosPedidos = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static Operador operadorAtual = new Operador("Nao identificado", "--", null, false);

    public static void main(String[] args) {
        boolean executando = true;

        carregarDadosExemplo();
        while (executando) {
            exibirCabecalho();
            exibirMenu();

            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    identificarOperador();
                    break;
                case "2":
                    CriacaoPedidos.registrarPedido(scanner, todosPedidos, operadorAtual, fornecedores, produtos);
                    pausar();
                    break;
                case "3":
                    ExclusaoPedidos.excluirPedido(scanner, todosPedidos, operadorAtual);
                    pausar();
                    break;
                case "4":
                    BuscaPedidosPorPeriodo.buscarPedidoPorData(scanner, todosPedidos);
                    pausar();
                    break;
                case "5":
                    registrarEntrega();
                    break;
                case "6":
                    if (operadorAtual.isAdministrador()) {
                        exibirPainelAdministrador();
                    } else {
                        System.out.println("Opcao invalida.");
                        pausar();
                    }
                    break;
                case "7":
                    GerenciadorDeEstatisticas.getEstatisticasUltimos30Dias(todosPedidos);
                    pausar();
                    break;
                case "8":
                    GerenciadorDeEstatisticas.getPedidosOperador(scanner, todosPedidos);
                    pausar();
                    break;
                case "9":
                    GerenciadorDeEstatisticas.getMaiorPedidoValorEmAberto(todosPedidos);
                    pausar();
                    break;
                case "0":
                    executando = false;
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    pausar();
            }
        }

        scanner.close();
    }

    private static void exibirPainelAdministrador() {
        System.out.println();
        System.out.println("=== PAINEL DO ADMINISTRADOR ===");
        
        int totalPedidos = todosPedidos.size();
        System.out.println("Numero total de pedidos registrados: " + totalPedidos);
        
        if (totalPedidos > 0) {
            int aprovados = 0;
            int reprovados = 0;
            
            for (Pedido p : todosPedidos) {
                if (p.getStatus() == model.StatusPedido.APROVADO || p.getStatus() == model.StatusPedido.RECEBIDO) {
                    aprovados++;
                } else if (p.getStatus() == model.StatusPedido.CANCELADO) {
                    reprovados++;
                }
            }
            
            double percAprovados = (double) aprovados / totalPedidos * 100;
            double percReprovados = (double) reprovados / totalPedidos * 100;
            
            System.out.printf("Divisao Percentual:%n");
            System.out.printf("  - Aprovados (e Recebidos): %.2f%% (%d pedidos)%n", percAprovados, aprovados);
            System.out.printf("  - Reprovados (Cancelados): %.2f%% (%d pedidos)%n", percReprovados, reprovados);
        } else {
            System.out.println("Ainda nao ha pedidos para exibir estatisticas.");
        }
        
        pausar();
    }

    private static void exibirCabecalho() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("   SISTEMA DE CONTROLE DE AQUISICOES");
        System.out.println("   Usuario atual: " + operadorAtual);
        System.out.println("========================================");
    }

    private static void exibirMenu() {
        System.out.println("1 - Identificar/Trocar usuario atual");
        System.out.println("2 - Registrar novo pedido");
        System.out.println("3 - Excluir pedido");
        System.out.println("4 - Buscar pedidos por periodo");
        System.out.println("5 - Registrar entrega de pedido");
        if (operadorAtual.isAdministrador()) {
            System.out.println("6 - Painel de Estatisticas (Admin)");
        }
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private static void registrarEntrega() {
        System.out.println();
        System.out.println("=== REGISTRAR ENTREGA DE PEDIDO ===");
        
        List<Pedido> pedidosAbertos = new ArrayList<>();
        for (Pedido p : todosPedidos) {
            if (p.getStatus() == model.StatusPedido.ABERTO || p.getStatus() == model.StatusPedido.APROVADO) {
                pedidosAbertos.add(p);
            }
        }
        
        if (pedidosAbertos.isEmpty()) {
            System.out.println("Nenhum pedido pendente de entrega.");
            pausar();
            return;
        }
        
        System.out.println("Pedidos pendentes:");
        for (Pedido p : pedidosAbertos) {
            System.out.printf("  %d - Pedido em %s | Status: %s | Solicitante: %s%n", 
                p.getId(), p.getData(), p.getStatus(), p.getOperador().getNome());
        }
        
        System.out.print("Digite o numero do pedido para registrar entrega (0 para cancelar): ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            if (id == 0) {
                System.out.println("Operacao cancelada.");
            } else {
                Pedido pedidoEncontrado = null;
                for (Pedido p : pedidosAbertos) {
                    if (p.getId() == id) {
                        pedidoEncontrado = p;
                        break;
                    }
                }
                
                if (pedidoEncontrado != null) {
                    pedidoEncontrado.registrarEntrega();
                    System.out.println("Entrega registrada! Data de conclusao definida para: " + pedidoEncontrado.getDataConclusao());
                } else {
                    System.out.println("Pedido nao encontrado ou nao esta pendente de entrega.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida.");
        }
        pausar();
    }

    private static void identificarOperador() {
        System.out.print("Nome do usuario: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Iniciais do usuario: ");
        String iniciais = scanner.nextLine().trim();

        if (nome.isEmpty() || iniciais.isEmpty()) {
            System.out.println("Nome e iniciais sao obrigatorios.");
            pausar();
            return;
        }

        System.out.println("Departamentos disponiveis:");
        for (int i = 0; i < departamentos.size(); i++) {
            System.out.println((i + 1) + " - " + departamentos.get(i).getNome());
        }
        System.out.print("Escolha o departamento (numero): ");
        int deptoIndex = -1;
        try {
            deptoIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
        } catch (NumberFormatException e) {
            // ignora
        }
        
        Departamento depto = null;
        if (deptoIndex >= 0 && deptoIndex < departamentos.size()) {
            depto = departamentos.get(deptoIndex);
        } else {
            System.out.println("Departamento invalido. Definindo sem departamento.");
        }

        System.out.print("O usuario e administrador? (S/N): ");
        boolean isAdmin = scanner.nextLine().trim().equalsIgnoreCase("S");

        operadorAtual = new Operador(nome, iniciais, depto, isAdmin);
        System.out.println("Usuario atual alterado para: " + operadorAtual);

        pausar();
    }

    private static void pausar() {
        System.out.println("Pressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void carregarDadosExemplo() {
    departamentos.add(new Departamento("TI", 15000.00));
    departamentos.add(new Departamento("RH", 5000.00));
    departamentos.add(new Departamento("Financeiro", 20000.00));
    departamentos.add(new Departamento("Marketing", 8000.00));

    fornecedores.add(new Fornecedor(1, "Papelaria Central", "12.345.678/0001-90", "(11) 3000-1111"));
    fornecedores.add(new Fornecedor(2, "Tech Suprimentos", "98.765.432/0001-10", "(11) 3000-2222"));

    produtos.add(new Produto(1, "Papel A4 (resma)", "UN", 25.90));
    produtos.add(new Produto(2, "Caneta azul", "CX", 18.50));
    produtos.add(new Produto(3, "Toner preto", "UN", 189.00));
}



}