package com.ifpb.projetopoo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/** A classe Pedido contêm a modelagem de pedidos dos clientes
 * @author Antonio Miguel
 * @author Laires Pereira
 * @version 1.0
 * @since 29-07-2018
 */

public class Pedido implements Serializable {



    private int quantidade;
    private LocalDate data;
    private LocalTime hora;
    private float valorTotal;
    private boolean atendido;
    private Produto produto;
    private static int id;
    private int numeroPedido;

    /**
     * Construtor da classe
     * */

    public Pedido(Produto produto, int quantidade) {
        this.produto=produto;
        this.quantidade = quantidade;
        this.data = LocalDate.now();
        this.hora = LocalTime.now();
        this.atendido = false;
        numeroPedido= ++id;

    }


    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    /**
     * getValorTotal soma o valor de todos os pedidos
     * @return valorTotal a soma do preço de todos os pedidos
     * */

    public float getValorTotal() {
        float valorTotal=0;
        valorTotal=quantidade*produto.getPrecoUnico();
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public boolean isAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    /**
     * modificarAtendido altera a informação se este pedido ja foi ou não atendido
     * @return true se conseguir definir como ja atendido
     * */

    public void modificarAtendido(){
        if(!isAtendido()){
            setAtendido(true);
        }
    }

    @Override
    public String toString() {
        String atendimento = "";
        if(atendido==true){
            atendimento="Sim";
        }else atendimento="Não";
        return "Pedido "+numeroPedido+" = |Produto: "+produto+
                "| Data e Hora:"+data+hora+"| Atendido: "+
                atendimento+"| Valor Total: "+getValorTotal();

                }
}
