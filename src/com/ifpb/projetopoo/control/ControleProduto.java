package com.ifpb.projetopoo.control;
import com.ifpb.projetopoo.model.*;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
/** A classe Pedido contêm a modelagem de controle de produtos.
 * @author Antonio Miguel
 * @author Laires Pereira
 * @version 1.0
 * @since 29-07-2018
 */
public class ControleProduto {


    private Menu menu;

    /**
     * CONSTRUTOR
     */
    public ControleProduto(){
        menu = new Menu();
    }

    public Menu getMenu(){
        if(menu.isVazio()){
            System.out.println("Não existe produtos cadastrados");
        }
        return menu;
    }

    /** Metodo para ler os dados e armazenar o produto.
     * @return retorna um objeto de Produto.
     */


    /**Metodo que pega o objeto produto e armazena na lista.
     * @return retorna um boolean que diz que foi cadastrado o produto.
     */
    /*public boolean cadastrarNovoProduto(){
        Produto p = lerDadosProduto();
        menu.addProduto(p);
        System.out.println("Produto Cadastrado");
        return true;
    }

    /**Metodo que consulta um Produto.
     * @param codigo variavel que recebe o codigo do produto.
     * @return retorna um string de produto.
     */
  /*  public String consultarProduto(int codigo){
        List<Produto> produtos = menu.getProdutos();
        for(Produto produto:produtos){
            if(Objects.equals(codigo, produto.getCodigo())){
                return produto.toString();
            }
        }return "Nao existe esse produto";

    }

    /**Metodo que atualiza um produto.
     * @param indice recebe o indice do produto na lista.
     * @return retorna um boolean se atualizou ou nao o produto.
     */
    /*public boolean atualizarProduto(int indice){
        if(indice>menu.tamanho()-1){
            return false;
        }
        Produto p = lerDadosProduto();
        menu.editarProduto(indice,p);
        return true;
    }

    /**Metodo que deleta o Produto.
     * @param indice recebe um indice do produto na lista.
     * @return retorna um boolean se deletou ou nao um Produto.
     */
    public boolean deletarProduto(int indice){
        if(indice>menu.tamanho()-1){
            return false;
        }
        menu.removeProduto(indice);
        return true;
    }

    @Override
    public String toString() {
        if(menu.isVazio()){
            return "Menu Vazio";
        }
        String s = "|---------PRODUTOS---------| \n";
        List<Produto> produtos = menu.getProdutos();
        int controle = 0;
        for(Produto produto:produtos){
            controle++;
            s+=controle+"= "+produto.toString();
        }
        return s;
    }
}
