package com.gugawag.rpc.banco;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BancoServiceIF extends Remote {

    double saldo(String conta) throws RemoteException;

    int quantidadeContas() throws RemoteException;

    void adicionarConta(String numero, String saldo) throws RemoteException;

    Conta pesquisarConta(String numero) throws RemoteException;

    void excluirConta(String numero) throws RemoteException;
}
