package com.ifpb.projetopoo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Gerencia {

    private static List<Comanda> comandas = new ArrayList<>();

    public static void addComanda(Comanda comanda){
        comandas.add(comanda);
    }

    public static String betweenn (LocalDate comeco, LocalDate fim){
        String resposta = "";
        for (Comanda comanda: comandas){
            if((comanda.getData().compareTo(comeco)>=0)&&(comanda.getData().compareTo(fim)<=0)){
                resposta+=comanda;
            }
        }return resposta;
    }

}
