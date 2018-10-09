package com.ifpb.projetopoo.Exception;
/** Classe de exceção de uma data valida.
 * * @author Antonio
 *  * @since 09-10-2018
 *  * @version 1.1
 */

public class DataInvalidaException extends Exception {
    public DataInvalidaException (String mensage){
        super(mensage);
    }
}
