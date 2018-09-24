package com.ifpb.projetopoo.dao;

import com.ifpb.projetopoo.Exception.PrecoInvalidoException;
import com.ifpb.projetopoo.model.Produto;
import com.ifpb.projetopoo.model.Usuario;

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


    public boolean AddProduto() throws IOException, PrecoInvalidoException {
        Produto produto = lerDadosProduto();
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
                try {
                    AddProduto();
                } catch (PrecoInvalidoException e) {
                    e.printStackTrace();
                }
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



    public Produto lerDadosProduto() throws PrecoInvalidoException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o codigo");
        int codigo=scanner.nextInt();
        System.out.println("Infome o nome:");
        String nome = scanner.next();
        System.out.println("Descreva:");
        String descrição = scanner.next();
        System.out.println("Informe o preço");
        float preço = scanner.nextFloat();
        if(preço<=0){
            throw new PrecoInvalidoException("Preço invalido, insera um valor positivo!");
        }
        Produto produtonovo = new Produto(codigo,nome, descrição,preço);
        return produtonovo;
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
