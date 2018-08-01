package com.ifpb.projetopoo.view;


import com.ifpb.projetopoo.model.GerenciarMesa;
import com.ifpb.projetopoo.model.Pedido;
import com.ifpb.projetopoo.model.Produto;
import com.ifpb.projetopoo.model.Cozinha;

import java.time.LocalDate;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Produto pao = new Produto(1,"pão","massa",0.5f);
        Produto carneHamburguer = new Produto(2,"carne de hamburguer","carne",1.0f);
        System.out.println(pao);
        System.out.println(carneHamburguer);
        GerenciarMesa m = new GerenciarMesa();
        m.abrirComanda(1);
        Cozinha z = new Cozinha();
        m.pedir(2,new Pedido(pao,1));
        m.viewPedido(1);
        z.atenderPedido(1);
        m.viewPedido(1);
    }
}
