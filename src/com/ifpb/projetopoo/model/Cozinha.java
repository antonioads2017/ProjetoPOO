package com.ifpb.projetopoo.model;

import java.util.List;

public class Cozinha {

    private List<Pedido> pedidos;

    public Cozinha(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
