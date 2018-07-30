package com.ifpb.projetopoo.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Pedido {

    private String nome;
    private float valorUnitario;
    private int quantidade;
    private LocalDate data;
    private LocalTime hora;
    private float valorTotal;
    private boolean atendido;

    public Pedido(String nome, float valorUnitario, int quantidade, LocalDate data, LocalTime hora, float valorTotal, boolean atendido) {
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.data = LocalDate.now();
        this.hora = LocalTime.now();
        this.valorTotal = valorUnitario*quantidade;
        this.atendido = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
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
}
