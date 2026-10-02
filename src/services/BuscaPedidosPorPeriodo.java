package services;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import model.Pedido;

//Isso eh apenas uma base pois nao esta compilando atualmente, isso deve ser ajustado logo em que as necessidades forem cumpridas

public class BuscaPedidosPorPeriodo {
    private static final DateTimeFormatter FORMATO_DATA =  DateTimeFormatter.ofPattern("dd/MM");

    public static void buscarPedidoPorData(Scanner scanner, List<Pedido> todosPedidos) {
        MonthDay dataInicial;
        MonthDay dataFinal;

        try {
            System.out.println("Data inicial:");
            System.out.print("Dia: ");
            int diaInicial = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Mes: ");
            int mesInicial = Integer.parseInt(scanner.nextLine().trim());
            dataInicial = MonthDay.of(mesInicial, diaInicial);

            System.out.println("Data final:");
            System.out.print("Dia: ");
            int diaFinal = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Mes: ");
            int mesFinal = Integer.parseInt(scanner.nextLine().trim());
            dataFinal = MonthDay.of(mesFinal, diaFinal);
        } 
        catch (NumberFormatException | DateTimeException e) {
            System.out.println("Data invalida. Informe um dia e um mes validos.");
            return;
        }

        System.out.println("\nPedidos entre " + dataInicial.format(FORMATO_DATA)
                + " e " + dataFinal.format(FORMATO_DATA) + ":");

        int quantidadeEncontrada = 0;
        for (Pedido pedido : todosPedidos) {
            if (pedido == null || pedido.getData() == null) {
                continue;
            }

            LocalDate dataPedido = pedido.getData();
            MonthDay diaMesPedido = MonthDay.from(dataPedido);
            boolean dentroDoPeriodo;

            if (dataInicial.compareTo(dataFinal) <= 0) {
                dentroDoPeriodo = diaMesPedido.compareTo(dataInicial) >= 0 && diaMesPedido.compareTo(dataFinal) <= 0;
            } else {
                dentroDoPeriodo = diaMesPedido.compareTo(dataInicial) >= 0 || diaMesPedido.compareTo(dataFinal) <= 0;
            }

            if (dentroDoPeriodo) {
                quantidadeEncontrada++;
                System.out.println(quantidadeEncontrada + " - " + dataPedido.format(FORMATO_DATA) + " | " + pedido);
            }
        }

        if (quantidadeEncontrada == 0) {
            System.out.println("Nenhum pedido encontrado nesse periodo.");
        }
    }
}