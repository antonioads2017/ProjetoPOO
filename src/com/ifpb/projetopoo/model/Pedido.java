package com.ifpb.projetopoo.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Pedido {

    private String nome;
    private int quantidade;
    private LocalDate data;
    private LocalTime hora;
    private float valorTotal;
    private boolean atendido;
    private Produto produto;
    private int id;

    public Pedido(Produto produto, String nome, float valorUnitario, int quantidade, LocalDate data, LocalTime hora,
                  float valorTotal, boolean atendido, int id) {
        this.produto=produto;
        this.nome = nome;
        this.quantidade = quantidade;
        this.data = LocalDate.now();
        this.hora = LocalTime.now();
        this.valorTotal = quantidade*produto.getPrecoUnico();
        this.atendido = false;
        this.id=id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", data=" + data +
                ", hora=" + hora +
                ", valorTotal=" + valorTotal +
                ", atendido=" + atendido +
                ", produto=" + produto +
                '}';
    }
}
