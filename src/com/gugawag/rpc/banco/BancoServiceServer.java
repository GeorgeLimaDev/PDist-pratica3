package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private Map<String, Double> saldoContas;
    private List<Conta> contas;

    public BancoServiceServer() throws RemoteException {
        saldoContas = new HashMap<>();
        contas = new ArrayList<>();

        // Adicionando contas iniciais
        saldoContas.put("1", 100.0);
        saldoContas.put("2", 156.0);
        saldoContas.put("3", 950.0);

        contas.add(new Conta("1", "100.0"));
        contas.add(new Conta("2", "156.0"));
        contas.add(new Conta("3", "950.0"));
    }

    @Override
    public double saldo(String conta) throws RemoteException {
        return saldoContas.getOrDefault(conta, 0.0);
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return saldoContas.size();
    }

    @Override
    public void adicionarConta(String numero, String saldo) throws RemoteException {
        if (saldoContas.containsKey(numero)) {
            System.out.println("Conta já existe: " + numero);
            return;
        }
        Conta novaConta = new Conta(numero, saldo);
        contas.add(novaConta);
        saldoContas.put(numero, Double.parseDouble(saldo));
        System.out.println("Conta criada com sucesso: " + numero);
    }

    @Override
    public Conta pesquisarConta(String numero) throws RemoteException {
        return contas.stream()
                .filter(conta -> conta.getNumero().equals(numero))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void excluirConta(String numero) throws RemoteException {
        Conta conta = pesquisarConta(numero);
        if (conta != null) {
            contas.remove(conta);
            saldoContas.remove(numero);
            System.out.println("Conta excluída com sucesso: " + numero);
        } else {
            System.out.println("Conta não encontrada: " + numero);
        }
    }
}
