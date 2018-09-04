package com.ifpb.projetopoo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** A classe Gerencia contêm a modelagem de comanda para o dominio.
 * A Classe contêm um ArrayList de Pedidos.
 * Adiciona Pedidos e verifica o atendimento.
 * @author Antonio Miguel
 * @author Laires Pereira
 * @version 1.0
 * @since 29-07-2018
 */

public class Gerencia {


    /**
     * CONSTRUTOR
     */
    private static List<Comanda> comandas = new ArrayList<>();

    public static void addComanda(Comanda comanda){
        comandas.add(comanda);
    }

    /**Metodo que recupera as comandas num certo periodo.
     * @param comeco recebe um data inicial.
     * @param fim recebe uma data final.
     * @return retorna uma String das comandas do certo periodo.
     */
    public static String betweenn (LocalDate comeco, LocalDate fim){
        String resposta = "";
        for (Comanda comanda: comandas){
            if((comanda.getData().compareTo(comeco)>=0)&&(comanda.getData().compareTo(fim)<=0)){
                resposta+=comanda;
            }
        }return resposta;
    }

}
