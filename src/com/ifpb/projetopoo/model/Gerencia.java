package com.ifpb.projetopoo.model;

import java.util.ArrayList;
import java.util.List;

public class Gerencia {
    private List<Comanda> comandas;

    private void addComanda(Comanda comanda){
        comandas.add(comanda);
    }

}
