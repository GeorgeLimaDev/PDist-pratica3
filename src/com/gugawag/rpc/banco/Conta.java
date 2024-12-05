package com.gugawag.rpc.banco;

public class Conta {
    private String numero;
    private String saldo;

    public Conta(String numero, String saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSaldo() {
        return this.saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
}
