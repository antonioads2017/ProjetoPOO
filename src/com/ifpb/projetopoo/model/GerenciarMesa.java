package com.ifpb.projetopoo.model;

import java.util.ArrayList;
import java.util.List;

public class GerenciarMesa {
    private List<Comanda> mesas;

    public GerenciarMesa(List<Comanda> mesas) {
        mesas = new ArrayList<>();
    }

    public boolean abrirComanda(int numeroMesa){
        boolean temComanda = false;
        for(Comanda comanda: mesas){
            if(comanda.getNumeroMesa() == numeroMesa){
                temComanda=true;

            }
        }
        if(!temComanda){
            Comanda comanda= new Comanda(numeroMesa);
            mesas.add(comanda);
            return true;
        }
        else return false;
    }

    public void pedir(int numeroMesa, Pedido pedido){
        for (Comanda comanda: mesas){
            comanda.addPedido(pedido);
        }
    }
     public void viewPedido(int numeroMesa) {
         for (Comanda comanda : mesas) {
             if (comanda.getNumeroMesa() == numeroMesa) {
                 System.out.println(comanda.toString());
             }
         }
     }

}
