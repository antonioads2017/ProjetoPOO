package com.ifpb.projetopoo.model;

import java.util.ArrayList;
import java.util.List;

public class Cozinha {

    private List<Pedido> pedidos;

    public Cozinha() {
        pedidos=new ArrayList<>();
    }

    public Pedido getPedido(int idPedido) {
        for (Pedido pedido: pedidos){
            if(pedido.getId()==idPedido){
                return pedido;
            }
        }
        return null;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void addPedido (Pedido pedido){
        pedidos.add(pedido);
    }

    public boolean atenderPedido(int idPedido){
        if(getPedido(idPedido)==null){
            return false;
        }if(!getPedido(idPedido).isAtendido()){
            for(Pedido pedido: pedidos){
                if(pedido.getNumeroPedido()==idPedido){
                    pedido.modificarAtendido();
                    pedidos.remove(pedido);
                    return true;
                }
            }
        }return false;
    }
}
