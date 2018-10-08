package com.ifpb.projetopoo.dao;


import com.ifpb.projetopoo.Exception.ProdutoInvalidoException;
import com.ifpb.projetopoo.model.Produto;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class ProdutoDao {

    private static File arquivoProduto = new File("Produto");



    public static List<Produto> pegarProdutos() throws IOException, ClassNotFoundException {
        List<Produto> produtos = new ArrayList<>();
        if (!arquivoProduto.exists()) {
            arquivoProduto.createNewFile();
        } else {
            if (arquivoProduto.length() > 0) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivoProduto))) {
                   return  produtos = (List<Produto>) in.readObject();
                }
            }
        }return new ArrayList<>();
    }

    public static boolean AddProduto(Produto produto) throws IOException, ClassNotFoundException {
        List<Produto> produtos = pegarProdutos();
        if(ConsultaProduto(produto.getCodigo())==null){
            produtos.add(produto);
            atualizarArquivo(produtos);
            return true;
        }return false;
    }

    public static Produto ConsultaProduto(int codigo) throws IOException, ClassNotFoundException {
        List<Produto> produtos = pegarProdutos();
        if(produtos.size()==0){
            return null;
        }else{
            for(Produto produto: produtos){
                if(produto.getCodigo()==codigo){
                    return produto;
                }
            }return null;

        }
    }

    public static boolean deletarProduto(int codigo) throws IOException, ClassNotFoundException {
        List<Produto> produtos = pegarProdutos();
        if(produtos.size()==0){
            return false;
        }else{
            Produto produto = ConsultaProduto(codigo);
            if(produto!=null){
                produtos.remove(produto);
                atualizarArquivo(produtos);
                return true;
            }
        }return false;

    }
    public static boolean atualizarProduto(int codigo, Produto newP) throws IOException, ClassNotFoundException, ProdutoInvalidoException {
        List<Produto> produtos = pegarProdutos();
        Produto old = ConsultaProduto(codigo);
        if(old!=null){
            produtos.set(produtos.indexOf(old),newP);
            atualizarArquivo(produtos);
            return true;
        }throw new ProdutoInvalidoException("Produto Invalido");
    }

    private static void atualizarArquivo(List<Produto> produtos) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivoProduto))){
            out.writeObject(produtos);
        }
    }

}
