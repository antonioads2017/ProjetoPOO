package com.ifpb.projetopoo.model;

import java.util.List;

public class Cardapio {

    private List<Produto> produtos;

    public Cardapio(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
