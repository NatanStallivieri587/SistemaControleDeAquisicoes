import java.util.*;

import services.*;
import model.Operador;
import model.Fornecedor;
import model.Pedido;
import model.Produto;


public class Main {
    
    private static final List<Fornecedor> fornecedores = new ArrayList<>();
    private static final List<Produto> produtos = new ArrayList<>();
    private static final List<Pedido> todosPedidos = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static Operador operadorAtual = new Operador("Nao identificado", "--");

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
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private static void identificarOperador() {
        System.out.print("Nome do usuario: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Iniciais do usuario: ");
        String iniciais = scanner.nextLine().trim();

        if (nome.isEmpty() || iniciais.isEmpty()) {
            System.out.println("Nome e iniciais sao obrigatorios.");
        } else {
            operadorAtual = new Operador(nome, iniciais);
            System.out.println("Usuario atual alterado para: " + operadorAtual);
        }

        pausar();
    }
1

    private static void pausar() {
        System.out.println("Pressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void carregarDadosExemplo() {
    fornecedores.add(new Fornecedor(1, "Papelaria Central", "12.345.678/0001-90", "(11) 3000-1111"));
    fornecedores.add(new Fornecedor(2, "Tech Suprimentos", "98.765.432/0001-10", "(11) 3000-2222"));

    produtos.add(new Produto(1, "Papel A4 (resma)", "UN", 25.90));
    produtos.add(new Produto(2, "Caneta azul", "CX", 18.50));
    produtos.add(new Produto(3, "Toner preto", "UN", 189.00));
}



}