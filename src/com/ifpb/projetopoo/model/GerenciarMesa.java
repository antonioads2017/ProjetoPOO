package com.ifpb.projetopoo.model;

import java.util.ArrayList;
import java.util.List;

public class GerenciarMesa {
    private List<Comanda> mesas;

    public GerenciarMesa() {
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

    public boolean editarPedido(int numeroMesa, int idPedido, Pedido novoPedido) {
        for(Comanda comanda: mesas) {
            if(comanda.getNumeroMesa() == numeroMesa) {
                List<Pedido> mesa = comanda.getComanda();
                // Editar
                for(Pedido pedido: mesa) {
                    if(pedido.getId() == idPedido) {
                        int index = mesa.indexOf(pedido);
                        mesa.add(index,pedido);
                        return true;
                    }
                }

            }
        } return false;
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

     public boolean fecharComanda(int numeroMesa){
        int index=-1;
        for (Comanda comanda: mesas){
            if(comanda.getNumeroMesa()==numeroMesa) {
                Gerencia.addComanda(comanda);
                index = mesas.indexOf(comanda);
                System.out.println("Valor Total: " + comanda.getValorTotal());
                break;
            }
        }
         if(index!=-1){
            mesas.remove(index);
            return true;
         }return false;
     }



}
