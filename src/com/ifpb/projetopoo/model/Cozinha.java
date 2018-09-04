package com.ifpb.projetopoo.model;

import java.util.ArrayList;
import java.util.List;
/**
 * A classe Cozinha representa a entidade cozinha da aplicação
 * Contém um ArrayList com todos os pedidos feitos pelos clientes
 * @author Laires
 * @author Antonio
 * @since 29-07-2018
 * @version 1.0
 * */

public class Cozinha {




    private List<Pedido> pedidos;

    public Cozinha() {
        pedidos=new ArrayList<>();
    }

    /**
     * Retorna um pedido pela id recebida como propriedade
     * @param idPedido : número de id que se deseja encontrar, contido no pedido
     * @return o pedido se for encontrado
     * @return null se não encontrar
     * */

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

    /**
     * O método atenderPedido marca um pedido específico como atendido
     * Para que não seja possível cancelar o mesmo futuramente
     * @param idPedido : recebe também a id do pedido que ja foi atendido
     * @return false se não encontrar o pedido através da funcção getPedido
     * @return true se encontrar o pedido e marca-lo como atendido
     * @return false se o pedido ja estiver marcado como atendido
     * */

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
