package com.ifpb.projetopoo.Exception;
/** Classe de exceção de um produto valido.
 * * @author Antonio
 *  * @since 09-10-2018
 *  * @version 1.1
 */
public class ProdutoInvalidoException extends Exception{
    public ProdutoInvalidoException(String mensage){
        super(mensage);
    }
}
