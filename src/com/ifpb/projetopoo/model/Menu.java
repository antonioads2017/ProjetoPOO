package com.ifpb.projetopoo.model;

import java.util.List;

public class Menu {

    private List<Produto> produtos;

    public Menu(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
