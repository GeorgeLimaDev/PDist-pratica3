package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        
        if (args.length < 1) {
            System.out.println("Uso correto: java com.gugawag.rpc.banco.AppClienteBanco <IP_SERVIDOR>");
            return;
        }

        String ipServidor = args[0];

        
        Registry registry = LocateRegistry.getRegistry(ipServidor);
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        Scanner entrada = new Scanner(System.in);
        int opcao;

        do {
            menu();
            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 1: {
                    System.out.print("Digite o número da conta: ");
                    String conta = entrada.nextLine();
                    System.out.println("Saldo: " + banco.saldo(conta));
                    break;
                }
                case 2: {
                    System.out.println("Quantidade de contas: " + banco.quantidadeContas());
                    break;
                }
                case 3: {
                    System.out.print("Digite o número da nova conta: ");
                    String numero = entrada.nextLine();
                    System.out.print("Digite o saldo inicial: ");
                    String saldo = entrada.nextLine();
                    banco.adicionarConta(numero, saldo);
                    break;
                }
                case 4: {
                    System.out.print("Digite o número da conta para pesquisa: ");
                    String numero = entrada.nextLine();
                    Conta conta = banco.pesquisarConta(numero);
                    if (conta != null) {
                        System.out.println("Conta encontrada! Número: " + conta.getNumero() + ", Saldo: " + conta.getSaldo());
                    } else {
                        System.out.println("Conta não encontrada!");
                    }
                    break;
                }
                case 5: {
                    System.out.print("Digite o número da conta para exclusão: ");
                    String numero = entrada.nextLine();
                    banco.excluirConta(numero);
                    break;
                }
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 9);
    }

    public static void menu() {
        System.out.println("\n=== BANCO RMI ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Criar nova conta");
        System.out.println("4 - Pesquisar conta");
        System.out.println("5 - Excluir conta");
        System.out.println("9 - Sair");
    }
}
