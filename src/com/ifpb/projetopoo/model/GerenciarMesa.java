package com.ifpb.projetopoo.model;

import java.util.ArrayList;
import java.util.List;

public class GerenciarMesa {

    private List<Comanda> mesas;

    public GerenciarMesa() {
        mesas = new ArrayList<Comanda>();
    }

    public List<Comanda> getMesas() {
        return mesas;
    }

    public void setMesas(List<Comanda> mesas) {
        this.mesas = mesas;
    }

    public Comanda pegaComanda (int numeroMesa){
        for (Comanda comanda: mesas){
            if(comanda.getNumeroMesa()==numeroMesa){
                return comanda;
            }
        }return null;
    }

    public boolean abrirComanda(int numeroMesa){
        boolean temComanda = false;
        for(Comanda comanda: mesas){
            if(comanda.getNumeroMesa() == numeroMesa){
                temComanda=true;
                break;

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

     public boolean fazPedido(int numeroMesa, Pedido pedido, Cozinha cozinha){
        for(Comanda comanda:mesas){
            if(comanda.getNumeroMesa()==numeroMesa){
                comanda.addPedido(pedido);
                cozinha.addPedido(pedido);
                return true;
            }
        }return false;
     }

     public boolean fecharComanda(int numeroMesa){
        int index=-1;
        for (Comanda comanda: mesas){
            if(comanda.getNumeroMesa()==numeroMesa) {
                if (comanda.Atendidos()) {
                    Gerencia.addComanda(comanda);
                    index = mesas.indexOf(comanda);
                    System.out.println("Valor Total: " + comanda.getValorTotal());
                    break;
                }
                else break;
            }
        }
         if(index!=-1){
            mesas.remove(index);
            return true;
         }else return false;
     }

     public boolean modificarPedido (int idPedido, int numeroMesa, Pedido pedidoNovo){
        for(Comanda comanda:mesas){
            if(comanda.getNumeroMesa()==numeroMesa){
                List<Pedido> mesa = comanda.getComanda();
                for (Pedido pedido:mesa){
                    int index = mesa.indexOf(pedido);
                    mesa.add(index,pedidoNovo);
                    return true;
                }
            }
        }return false;
     }



}
