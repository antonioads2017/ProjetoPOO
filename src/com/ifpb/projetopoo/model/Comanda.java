package com.ifpb.projetopoo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.ifpb.projetopoo.model.Pedido;

public class Comanda {

    private List<Pedido> comanda;
    private float valorTotal;
    private int numeroMesa;
    private static LocalDate data;
    private static int numeroComanda;

    public Comanda(int numeroMesa) {
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
        for(int i=0;i<comanda.size();i++){
            this.valorTotal+=comanda.get(i).getValorTotal();
        }
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

    public void addPedido(Pedido pedido){
        comanda.add(pedido);
        this.getValorTotal();
    }
    public boolean atender(int id) {
        for (Pedido pedido : comanda) {
            if (pedido.getId() == id) {
                pedido.setAtendido(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "comanda=" + comanda +
                ", valorTotal=" + valorTotal +
                ", numeroMesa=" + numeroMesa +
                '}';
    }
}
