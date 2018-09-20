package com.ifpb.projetopoo.dao;

import com.ifpb.projetopoo.model.Produto;
import java.io.*;
import java.util.*;

public class ProdutoDao {

    private File arquivoProduto;
    private static int codigo;

    public ProdutoDao() throws IOException {
        arquivoProduto = new File("Produto");
        if(!arquivoProduto.exists()){
            arquivoProduto.createNewFile();
        }
    }

    public Set<Produto> getProdutos() throws IOException, ClassNotFoundException {
        if(arquivoProduto.length()>0){
            try(ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivoProduto))){
                return (Set<Produto>) in.readObject();
            }
        }else{
            return new HashSet<>();
        }
    }

    public boolean AddProduto() throws IOException, ClassNotFoundException {
        Produto produto = lerDadosProduto();
        Set<Produto> produtos = getProdutos();
        for(Produto produto1:produtos){
            if(Objects.equals(produto.getCodigo(),produto1.getCodigo())){
                System.out.println("Código "+produto.getCodigo()+" já existe, Tente com outro codigo!");
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

    public Produto ConsultaProduto(int codigo) throws IOException, ClassNotFoundException {
        Set<Produto> produtos = getProdutos();
        for(Produto produto: produtos){
            if(Objects.equals(codigo,produto.getCodigo())){
                return produto;
            }
        }return null;
    }

    public boolean deletarProduto(int codigo) throws IOException, ClassNotFoundException {
        Set<Produto> produtos = getProdutos();
        for(Produto produto: produtos){
            if(Objects.equals(codigo,produto.getCodigo())){
                if(produtos.remove(produto)) {
                    atualizarArquivo(produtos);
                    return true;
                }
            }
        }return false;
    }
    public boolean atualizarProduto(int codigo) throws IOException, ClassNotFoundException {
        Set<Produto> produtos = getProdutos();
        for (Produto produto : produtos){
            if(Objects.equals(codigo,produto.getCodigo())){
                deletarProduto(codigo);
                AddProduto();
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



    public Produto lerDadosProduto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o codigo");
        int codigo=scanner.nextInt();
        System.out.println("Infome o nome:");
        String nome = scanner.next();
        System.out.println("Descreva:");
        String descrição = scanner.next();
        System.out.println("Informe o preço");
        float preço = scanner.nextFloat();
        Produto produtonovo = new Produto(codigo,nome, descrição,preço);
        return produtonovo;
    }

    @Override
    public String toString() {

        String s = "|---------PRODUTOS---------| \n";
        Set<Produto> produtos = null;
        try {
            produtos = getProdutos();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(produtos.isEmpty()){
            return "Menu Vazio";
        }
        for(Produto produto:produtos){
                s+=produto.toString();
            }
            return s;

    }
}
