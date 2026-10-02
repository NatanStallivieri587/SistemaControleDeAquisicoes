package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import model.Pedido;
import model.StatusPedido;

public class GerenciadorDeEstatisticas {

    public static void getPedidosOperador(Scanner scanner, List<Pedido> todosPedidos) {
        System.out.println();
        System.out.println("=== CONSULTAR PEDIDOS DE UM OPERADOR ===");
        System.out.print("Informe o nome do solicitante para buscar (vazio para cancelar): ");
        String nomeOperador = scanner.nextLine().trim();
        
        if (nomeOperador.isEmpty()) {
            System.out.println("Busca cancelada.");
            return;
        }

        List<Pedido> resultados = new ArrayList<>();
        for(Pedido p : todosPedidos) {
            if(p.getOperador() != null && p.getOperador().getNome().equalsIgnoreCase(nomeOperador)) {
                resultados.add(p);
            }
        }

        if (resultados.isEmpty()) {
            System.out.println("Nenhum pedido encontrado para o operador: " + nomeOperador);
        } else {
            System.out.println("Pedidos encontrados:");
            for (Pedido p : resultados) {
                System.out.println(p);
                System.out.println("-------------------------");
            }
        }
    }
    
    // retorna um array com os dados de quantidade pedidos, posição 0, e media dos pedidos, posição 1.
    public static void getEstatisticasUltimos30Dias(List<Pedido> todosPedidos)
    {
        System.out.println();
        System.out.println("=== ESTATISTICAS DOS ULTIMOS 30 DIAS ===");
        LocalDate dataAtual = LocalDate.now();
        double totalValores30Dias = 0;
        int totalPedidos30Dias= 0;
        
        for(Pedido p : todosPedidos) {
            if(p.getData() != null && !p.getData().isBefore(dataAtual.minusDays(30))) {
                totalPedidos30Dias++;
                totalValores30Dias += p.getTotal();
            }
        }

        System.out.println("Total de pedidos (ultimos 30 dias): " + totalPedidos30Dias);
        if (totalPedidos30Dias > 0) {
            double media = totalValores30Dias / totalPedidos30Dias;
            System.out.printf("Valor medio dos pedidos: R$ %.2f%n", media);
        }
    }

    public static void getMaiorPedidoValorEmAberto(List<Pedido> todosPedidos)
    {
        System.out.println();
        System.out.println("=== MAIOR PEDIDO ABERTO ===");
        double valorMaior= 0;
        Pedido comValorMaior=null;
        
        for(Pedido p : todosPedidos) {
            if(p.getStatus() == StatusPedido.ABERTO && p.getTotal() > valorMaior) {
                valorMaior = p.getTotal();
                comValorMaior = p;
            }
        }

        if (comValorMaior != null) {
            System.out.println(comValorMaior);
        } else {
            System.out.println("Nenhum pedido ABERTO foi encontrado.");
        }
    }
}