package com.ifpb.projetopoo.Exception;
/** Classe de exceção de uma comanda valida.
 * * @author Antonio
 *  * @since 09-10-2018
 *  * @version 1.1
 */
public class ComandaInvalidaException extends Exception{
    public ComandaInvalidaException(String mensage){
        super(mensage);
    }
}
