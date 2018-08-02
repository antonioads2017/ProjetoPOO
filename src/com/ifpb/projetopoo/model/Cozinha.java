package com.ifpb.projetopoo.model;

import java.util.ArrayList;
import java.util.List;

public class Cozinha {

    private List<Pedido> pedidos;

    public Cozinha() {
        pedidos=new ArrayList<>();
    }

    public List<Pedido> getPedidos(int idPedido) {
        for (Pedido pedido: pedidos){
            if(pedido.getId()==idPedido){
                return pedidos;
            }
        }
        return null;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void addPedido (Pedido pedido){
        pedidos.add(pedido);
    }

    //FALTA TERMINAR DE IMPLEMENTAR
    public boolean atenderPedido(int idPedido){
        if(getPedidos(idPedido)==null){
            return false;
        }return true;

    }
}
