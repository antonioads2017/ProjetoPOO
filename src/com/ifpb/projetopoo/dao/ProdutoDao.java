package com.ifpb.projetopoo.dao;


import com.ifpb.projetopoo.model.Produto;

import java.io.*;
import java.util.*;

public class ProdutoDao {

    private File arquivoProduto;
    private Set<Produto> produtos;

    public ProdutoDao() throws IOException, ClassNotFoundException {
        arquivoProduto = new File("Produto");
        if(!arquivoProduto.exists()){
            arquivoProduto.createNewFile();
        }
        else{
            if(arquivoProduto.length()>0) {
                try (ObjectInputStream in = new ObjectInputStream(
                        new FileInputStream(arquivoProduto))) {
                    produtos = (Set<Produto>) in.readObject();
                }
            }else produtos = new HashSet<>();
        }
    }
    public Set<Produto> pegarProdutos() throws IOException, ClassNotFoundException {
        if(arquivoProduto.length()>0) {
            try (ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivoProduto))) {
                return  (Set<Produto>) in.readObject();
            }
        }else return new HashSet<>();
    }


    public boolean AddProduto(Produto produto) throws IOException{
        for(Produto produto1:produtos){
            if(Objects.equals(produto.getCodigo(),produto1.getCodigo())){
                return false;
            }
        }
        if(produtos.add(produto)){
            atualizarArquivo(produtos);
            return true;
        }else {
            return false;
        }
    }

    public Produto ConsultaProduto(int codigo){
        for(Produto produto: produtos){
            if(Objects.equals(codigo,produto.getCodigo())){
                return produto;
            }
        }return null;
    }

    public boolean deletarProduto(int codigo) throws IOException{
        for(Produto produto: produtos){
            if(Objects.equals(codigo,produto.getCodigo())){
                if(produtos.remove(produto)) {
                    atualizarArquivo(produtos);
                    return true;
                }
            }
        }return false;
    }
    public boolean atualizarProduto(int codigo) throws IOException{
        for (Produto produto : produtos){
            if(Objects.equals(codigo,produto.getCodigo())){
                deletarProduto(codigo);
                return true;
            }
        }return false;
    }

    private void atualizarArquivo(Set<Produto> produtos) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivoProduto))){
            out.writeObject(produtos);
        }
    }


    @Override
    public String toString() {

        String s = "|---------PRODUTOS---------| \n";
        if(produtos.isEmpty()){
            return "Menu Vazio";
        }
        for(Produto produto:produtos){
                s+=produto.toString();
            }
            return s;

    }
}
