package com.ifpb.projetopoo.control;
import com.ifpb.projetopoo.model.*;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ControleProduto {

    private Menu menu;

    public ControleProduto(){
        menu = new Menu();
    }

    public Menu getMenu(){
        if(menu.isVazio()){
            System.out.println("Não existe produtos cadastrados");
        }
        return menu;
    }

    public Produto lerDadosProduto(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o codigo");
        int codigo = scanner.nextInt();
        System.out.println("Infome o nome:");
        String nome = scanner.next();
        System.out.println("Descreva:");
        String descrição = scanner.next();
        System.out.println("Informe o preço");
        float preço = scanner.nextFloat();
        Produto produtonovo = new Produto(nome, descrição,preço,codigo);
        return produtonovo;
    }

    public boolean cadastrarNovoProduto(){
        Produto p = lerDadosProduto();
        menu.addProduto(p);
        System.out.println("Produto Cadastrado");
        return true;
    }

    public String consultarProduto(int codigo){
        List<Produto> produtos = menu.getProdutos();
        for(Produto produto:produtos){
            if(Objects.equals(codigo, produto.getCodigo())){
                return produto.toString();
            }
        }return "Nao existe esse produto";

    }

    public boolean atualizarProduto(int indice){
        if(indice>menu.tamanho()-1){
            return false;
        }
        Produto p = lerDadosProduto();
        menu.editarProduto(indice,p);
        return true;
    }

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
            s+=controle+"=   \n"+produto.toString();
        }
        return s;
    }
}
