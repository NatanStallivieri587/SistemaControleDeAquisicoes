package model;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
public class GerenciadorDeEstatisticas {

    public static List<Pedido> getPedidosOperador(Scanner scanner, List<Pedido> todosPedidos) {
        System.out.println();
        System.out.println("=== CONSULTAR PEDIDOS DE UM OPERADOR ===");
        String nomeOerador = lerNome(scanner, "Informe o ID do pedido que deseja excluir (0 para cancelar): ");
        if (operador == null || operador.getIniciais() == null || operador.getIniciais().equals("--")) {
            System.out.println("Identifique o usuario atual (opcao 1) antes de excluir um pedido.");
            return;
        }
        for(Pedido p : todosPedidos)
        {
            if(p.getOperador() != null && p.getOperador().getNome().equals(nomeOperador)) {
                resultados.add(p);
            }
        }
        return resultados;
    }
    // retorna um array com os dados de quantidade pedidos, posição 0, e media dos pedidos, posição 1.
    public static double[] getEstatisticasUltimos30Dias(List<Pedido> todosPedidos)
    {
        double[] resultado = new double[2];
        LocalDate dataAtual = LocalDate.now();
        double totalValores30Dias = 0;
        double totalPedidos30Dias= 0;
        for(Pedido p : todosPedidos)
        {
            if(p.getData() != null && p.getData().isAfter(dataAtual.minusDays(31))) {
                totalPedidos30Dias ++;
                totalValores30Dias= totalValores30Dias + p.getTotal();
            }
        }
        Double mediaValor30Dias= totalValores30Dias/totalPedidos30Dias;
        resultado[0] = totalPedidos30Dias;
        resultado[1] = mediaValor30Dias;
        return resultado;
    }
    public static String getMaiorPedidoValorEmAberto(List<Pedido> todosPedidos)
    {
       double valorMaior= 0;
       Pedido comValorMaior=null;
       for(Pedido p : todosPedidos)
        {
            if(p.getTotal() != 0 && p.getStatus()== StatusPedido.ABERTO && p.getTotal()>valorMaior) {
                valorMaior= p.getTotal();
                comValorMaior = p;
            }
        }
     return comValorMaior.toString();
    }
    private static String lerNome(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            
        }
    }
}