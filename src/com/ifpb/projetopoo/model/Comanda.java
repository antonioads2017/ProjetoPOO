package com.ifpb.projetopoo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Comanda {

    private List<Pedido> comanda;
    private float valorTotal;
    private int numeroMesa;
    private static LocalDate data;
    private static int numeroComanda;

    public Comanda(List<Pedido> comanda, float valorTotal, int numeroMesa) {
        numeroComanda+=1;
        comanda=new ArrayList<>();
        this.numeroMesa = numeroMesa;
        this.valorTotal=0;
        data=LocalDate.now();
    }

    public List<Pedido> getComanda() {
        return comanda;
    }

    public void setComanda(List<Pedido> comanda) {
        this.comanda = comanda;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public static LocalDate getData() {
        return data;
    }

    public static void setData(LocalDate data) {
        Comanda.data = data;
    }

    public static int getNumeroComanda() {
        return numeroComanda;
    }

    public static void setNumeroComanda(int numeroComanda) {
        Comanda.numeroComanda = numeroComanda;
    }
    public void addPedido(Pedido pedido, Pedido novo){
        comanda.add(pedido);
    }
}
