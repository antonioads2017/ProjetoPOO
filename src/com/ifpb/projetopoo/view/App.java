package com.ifpb.projetopoo.view;


import com.ifpb.projetopoo.model.*;

import java.time.LocalDate;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
    // Criar novo usuário
        Usuario usuario = new Usuario("lairoca", "123", "Lairoca", LocalDate.now(), Setor.COZINHA, "4002-8922");

        // Adicionando uns produtin nessa budega
        Produto[] produtos = {
                new Produto("Bolo", "chocolate", 28, 25236),
                new Produto("dogão", "salxixa", 5,58),
        };
        // Testando se adicionou meixmo


        // Manter a interface funcionando.
        boolean sistemaLigado = true;
        int opçãoDoUsuario;
        Scanner scan = new Scanner(System.in);
        // Aqui é o sistema
        while(sistemaLigado) {
            System.out.println("Digite 1 para adicionar um produto!");
            System.out.println("Digite 0 para sair do sistema.");
            opçãoDoUsuario = scan.nextInt();

            if (opçãoDoUsuario > 1) {
                System.out.println("Erro: Digite um comando válido");
            } else {
                if (opçãoDoUsuario == 0) {
                    sistemaLigado = false;
                } else if(opçãoDoUsuario == 1) {
                    String nome;
                    String descrição;
                    float preco;
                    int codigo;
                    System.out.println("Digite o nome do produto:");
                    nome = scan.next();
                    System.out.println("Digite a descrição:");
                    descrição = scan.next();
                    System.out.println("Digite o preço: ");
                    preco = scan.nextFloat();
                    System.out.println("Digite o código: ");
                    codigo = scan.nextInt();
                    Produto[] produto ={ new Produto(nome,descrição,preco,codigo)};
                    // DAQUI TEM QUE ADICIONAR ARRAY LIST DE PRODUTOSSSSSS, TENTA AIII
                    for(Produto p :  produto){
                        System.out.println(p);
                    }


                }
            }
        }




//        Produto pao = new Produto(1,"pão","massa",0.5f);
//        Produto carneHamburguer = new Produto(2,"carne de hamburguer","carne",1.0f);
//        System.out.println(pao);
//        System.out.println(carneHamburguer);
//        GerenciarMesa m = new GerenciarMesa();
//        m.abrirComanda(1);
//        Cozinha z = new Cozinha();
//        m.pedir(2,new Pedido(pao,1));
//        m.viewPedido(1);
//        z.atenderPedido(1);
//        m.viewPedido(1);
    }
}
