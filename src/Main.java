import java.util.Scanner;

import model.Operador;
import model.Fornecedor;
import model.Produto;
import services.RegistroPedido;

public class Main {
    
    private static final List<Fornecedor> fornecedores = new ArrayList<>();
    private static final List<Produto> produtos = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static Operador operadorAtual = new Operador("Nao identificado", "--");

    public static void main(String[] args) {
        boolean executando = true;

        while (executando) {
            exibirCabecalho();
            exibirMenu();

            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    identificarOperador();
                    break;
                case "2":
                    RegistroPedido.registrarPedido(scanner, todosPedidos, operadorAtual, fornecedores, produtos);
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

    private static void pausar() {
        System.out.println("Pressione ENTER para continuar...");
        scanner.nextLine();
    }
}