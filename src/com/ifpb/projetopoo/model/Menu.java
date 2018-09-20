package com.ifpb.projetopoo.model;

import java.util.ArrayList;
import java.util.List;


/** A classe Menu contêm a modelagem de um menu para o dominio.
 * A Classe contêm um ArrayList de Produtos.
 * Recebe, deleta ou altera um produto instanciado.
 * @author Antonio Miguel
 * @author Laires Pereira
 * @version 1.0
 * @since 29-07-2018
 */

public class Menu {

    private List<Produto> produtos;

    /**
     * Construtor
     * */

    public Menu() {
        produtos = getProdutos();
    }

    /** Retorno de um objeto inteiro de produtos
     *   @return produtos
     */

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    /** Metodos da desta classe */

    public void addProduto(Produto produto){
        produtos.add(produto);
    }

    public void removeProduto (int indice){
        produtos.remove(indice);
    }

    public void editarProduto(int index, Produto produto){
        produtos.set(index, produto);
    }

    public int tamanho(){
        return produtos.size();
    }

    public boolean isVazio(){
        return produtos.isEmpty();
    }


}
