package com.ifpb.projetopoo.dao;


import com.ifpb.projetopoo.Exception.ProdutoInvalidoException;
import com.ifpb.projetopoo.model.Produto;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

/** Classe Dao que arquiva os produtos do sistema.
 * Contem um arquivo Produto.
 * @author Antonio
 *  @since 09-10-2018
 *  @version 1.1
 */


public class ProdutoDao {

    private static File arquivoProduto = new File("Produto");


    /**
     * Metodo estatico que pega ou cria um arquivo e salva os objetos produtos.
     * @return uma lista de produtos.
     * @throws IOException trata a exceção de estrutura do arquivo.
     * @throws ClassNotFoundException trata a exceção da classe do objeto recebido.
     */
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

    /**
     * Metodo para adicionar o produto no arquivo.
     * @param produto um objeto de produto.
     * @return true se o produto foi adicionado.
     * @return false se o produto não foi adicionado.
     * @throws IOException trata a exceção de estrutura do arquivo.
     * @throws ClassNotFoundException trata a exceção da classe do objeto recebido.
     */
    public static boolean AddProduto(Produto produto) throws IOException, ClassNotFoundException {
        List<Produto> produtos = pegarProdutos();
        if(ConsultaProduto(produto.getCodigo())==null){
            produtos.add(produto);
            atualizarArquivo(produtos);
            return true;
        }return false;
    }

    /**Metodo para consultar um produto pelo seu codigo.
     * @param codigo recebe o codigo do produto.
     * @return o produto consultado
     * @throws IOException trata a exceção de estrutura do arquivo.
     * @throws ClassNotFoundException trata a exceção da classe do objeto recebido.
     */
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

    /**
     * Metodo para exlcuir o produto pelo seu codigo.
     * @param codigo recebe um codigo do produto.
     * @return true se o produto foi deletado.
     * @return false se o produto não foi deletado.
     * @throws IOException trata a exceção de estrutura do arquivo.
     * @throws ClassNotFoundException trata a exceção da classe do objeto recebido.
     */
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

    /**
     * Metodo que atualiza o produto pelo seu codigo.
     * @param codigo recebe o codigo do produto a ser substituido.
     * @param newP recebe o produto substituto.
     * @return true se o produto foi atualizado.
     * @return false se o produto não foi atualizado.
     * @throws IOException trata a exceção de estrutura do arquivo.
     * @throws ClassNotFoundException trata a exceção da classe do objeto recebido.
     * @throws ProdutoInvalidoException trata a exceção se o produto existe.
     */
    public static boolean atualizarProduto(int codigo, Produto newP) throws IOException, ClassNotFoundException, ProdutoInvalidoException {
        List<Produto> produtos = pegarProdutos();
        Produto old = ConsultaProduto(codigo);
        if(old!=null){
            produtos.set(produtos.indexOf(old),newP);
            atualizarArquivo(produtos);
            return true;
        }throw new ProdutoInvalidoException("Produto Invalido");
    }

    /** Metodo para atualizar o arquivo de Produtos.
     * @param produtos recebe uma lista de produtos.
     * @throws IOException trata a exceção de estrutura do arquivo.
     */
    private static void atualizarArquivo(List<Produto> produtos) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivoProduto))){
            out.writeObject(produtos);
        }
    }

}
