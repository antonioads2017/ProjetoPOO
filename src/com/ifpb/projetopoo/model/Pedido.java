package com.ifpb.projetopoo.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Pedido {

    private int quantidade;
    private LocalDate data;
    private LocalTime hora;
    private float valorTotal;
    private boolean atendido;
    private Produto produto;
    private static int id;
    private int numeroPedido;

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

    public void modificarAtendido(){
        if(!isAtendido()){
            setAtendido(true);
        }
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "quantidade=" + quantidade +
                ", data=" + data +
                ", hora=" + hora +
                ", atendido=" + atendido +
                ", produto=" + produto +
                ", numeroPedido=" + numeroPedido +
                '}';
    }
}
