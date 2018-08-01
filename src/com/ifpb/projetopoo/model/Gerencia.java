package com.ifpb.projetopoo.model;

import java.util.ArrayList;
import java.util.List;

public class Gerencia {
    private static List<Comanda> comandas;

    public static void addComanda(Comanda comanda){
        comandas.add(comanda);
    }

}
