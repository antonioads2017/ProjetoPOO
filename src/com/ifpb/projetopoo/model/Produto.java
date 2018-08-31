package com.ifpb.projetopoo.model;

import java.util.ArrayList;
import java.util.Objects;

// MODELAGEM DO PRODUTO
public class Produto {

    private ArrayList<Produto> produtos;

    //ATRIBUTOS
    private int codigo;
    private String nome;
    private String descrição;
    private float precoUnico;

    //CONSTRUTOR

    public Produto(String nome, String descrição, float precoUnico, int codigo) {
        this.nome = nome;
        this.descrição = descrição;
        this.precoUnico = precoUnico;
        this.codigo = codigo;
    }


    //GETTER's e SETTER's

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public float getPrecoUnico() {
        return precoUnico;
    }

    public void setPrecoUnico(float precoUnico) {
        this.precoUnico = precoUnico;
    }

    public boolean criaProduto(Produto p) {
        produtos.add(p);
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return codigo == produto.codigo &&
                Float.compare(produto.precoUnico, precoUnico) == 0 &&
                Objects.equals(nome, produto.nome) &&
                Objects.equals(descrição, produto.descrição);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nome, descrição, precoUnico);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", descrição='" + descrição + '\'' +
                ", precoUnico=" + precoUnico +
                '}';
    }
}
