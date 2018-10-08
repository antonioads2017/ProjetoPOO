package com.ifpb.projetopoo.model;

import com.ifpb.projetopoo.Exception.CodigoInvalidoException;
import com.ifpb.projetopoo.Exception.PrecoInvalidoException;

import java.io.*;
import java.util.Objects;
/** A classe Pedido contêm a modelagem de pedidos dos clientes
 * @author Antonio Miguel
 * @author Laires Pereira
 * @version 1.0
 * @since 29-07-2018
 */
public class Produto implements Serializable {

    private int codigo;
    private String nome;
    private String descrição;
    private float precoUnico;

    /**CONSTRUTOR
     */

    public Produto(int codigo,String nome, String descrição, float precoUnico) throws CodigoInvalidoException, PrecoInvalidoException {
        if(codigo<=0){
            throw new CodigoInvalidoException("O código informado é inválido!");
        }
        if(precoUnico<=0){
            throw  new PrecoInvalidoException("O valor esta incorreto");
        }
        this.nome = nome;
        this.descrição = descrição;
        this.precoUnico = precoUnico;
        this.codigo=codigo;
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) throws CodigoInvalidoException {
        if(codigo<=0){
            throw new CodigoInvalidoException("O código informado é inválido");
        }
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public float getPrecoUnico() {
        return precoUnico;
    }

    public void setPrecoUnico(float precoUnico) throws PrecoInvalidoException {
        if(precoUnico<=0){
            throw  new PrecoInvalidoException("O valor esta incorreto");
        }
        this.precoUnico = precoUnico;
    }

    public boolean criaProduto(Produto p) {
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return codigo == produto.codigo &&
                Float.compare(produto.precoUnico, precoUnico) == 0 &&
                Objects.equals(nome, produto.nome) &&
                Objects.equals(descrição, produto.descrição);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nome, descrição, precoUnico);
    }

    @Override
    public String toString() {
        return "Codigo: "+codigo+" Nome: "+nome+"| Descrição: "+descrição+"| Preço: "+precoUnico+"\n";
    }
}
