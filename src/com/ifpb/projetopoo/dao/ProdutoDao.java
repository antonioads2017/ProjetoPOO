package com.ifpb.projetopoo.dao;


import com.ifpb.projetopoo.model.Produto;

import java.io.*;
import java.util.*;

public class ProdutoDao {

    private static File arquivoProduto = new File("Produto");



    public static Set<Produto> pegarProdutos() throws IOException, ClassNotFoundException {
        Set<Produto> produtos = new HashSet<>();
        if (!arquivoProduto.exists()) {
            arquivoProduto.createNewFile();
        } else {
            if (arquivoProduto.length() > 0) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivoProduto))) {
                    produtos = (Set<Produto>) in.readObject();
                }
            }
        }return new HashSet<>();
    }

    public static boolean AddProduto(Produto produto) throws IOException, ClassNotFoundException {
        Set<Produto> produtos = pegarProdutos();
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

    public static Produto ConsultaProduto(int codigo) throws IOException, ClassNotFoundException {
        Set<Produto> produtos = pegarProdutos();
        for(Produto produto: produtos){
            if(produto.getCodigo()==codigo){
                return produto;
            }
        }return null;
    }

    public static boolean deletarProduto(int codigo) throws IOException, ClassNotFoundException {
        Set<Produto> produtos = pegarProdutos();
        for(Produto produto: produtos){
            if(produto.getCodigo()==codigo){
                if(produtos.remove(produto)) {
                    atualizarArquivo(produtos);
                    return true;
                }
            }
        }return false;
    }
    public static boolean atualizarProduto(int codigo) throws IOException, ClassNotFoundException {
        Set<Produto> produtos = pegarProdutos();
        for (Produto produto : produtos){
            if(produto.getCodigo()==codigo){
                deletarProduto(codigo);
                return true;
            }
        }return false;
    }

    private static void atualizarArquivo(Set<Produto> produtos) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivoProduto))){
            out.writeObject(produtos);
        }
    }


    @Override
    public String toString() {
        Set<Produto> produtos = null;
        try {
            produtos = pegarProdutos();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
