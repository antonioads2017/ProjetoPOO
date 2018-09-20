package com.ifpb.projetopoo.view;


import com.ifpb.projetopoo.dao.ProdutoDao;
import com.ifpb.projetopoo.model.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
    // Criar novo usuário
        GerenciarMesa mesinha = new GerenciarMesa();
        Cozinha cozinha = new Cozinha();
        ProdutoDao produtos = null;
        Gerencia gerencia = new Gerencia();
        try {
            produtos = new ProdutoDao();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Manter a interface funcionando.
        boolean sistemaLigado = true;
        int opçãoDoUsuario;
        Scanner scan = new Scanner(System.in);
        // Aqui é o sistema
        while(sistemaLigado) {
            System.out.println("|--------------ATENDIMENTO-------------|\n\n\n");
            System.out.println("Digite 1 para fazer um pedido!");
            System.out.println("Digite 2 para abrir uma comanda para uma mesa: ");
            System.out.println("Digite 3 para fechar uma comanda: ");
            System.out.println("Digite 4 para atender um pedido");
            System.out.println("Digite 5 para ver os pedidos de uma mesa");
            System.out.println("Digite 6 para ver comandas num intervalo de tempo");
            System.out.println("Digite 7 para ver pedidos não atendidos.");
            System.out.println("Digite 0 para sair do sistema.");
            opçãoDoUsuario = scan.nextInt();

            if(opçãoDoUsuario>7){
                System.out.println("Erro - Opção Inválida, pressione enter");
                scan.nextLine();
            }

            switch (opçãoDoUsuario){
                case 1: System.out.println("Faça um pedido:");
                    System.out.println(produtos.getProdutos());
                    System.out.println("Digite o numero do produto.");
                    int numProduto = scan.nextInt();
                    System.out.println("Você selecionou: " + produtos.ConsultaProduto(numProduto));
                    System.out.println("Informe a quantidade: ");
                    int quantidadePedido = scan.nextInt();
                    System.out.println("Informe a mesa: ");
                    int numMesa = scan.nextInt();
                    Pedido p = new Pedido(produtos.ConsultaProduto(numProduto), quantidadePedido);
                    if(mesinha.pegaComanda(numMesa) == null) {
                        mesinha.abrirComanda(numMesa);
                        System.out.println("Foi aberta uma comanda para a mesa: " + numMesa);
                        mesinha.fazPedido(numMesa,p, cozinha);
                    } else {
                        mesinha.fazPedido(numMesa, p, cozinha);
                    }
                    System.out.println(mesinha.pegaComanda(numMesa));
                    break;

                case 2: System.out.println("Digite o número da mesa: ");
                    int numeroMesa = scan.nextInt();
                    if(mesinha.abrirComanda(numeroMesa)){
                        System.out.println("Comanda aberta!");
                    } else {
                        System.out.println("Erro");
                    }
                    break;

                case 3: System.out.println("Digite o numero da mesa para fechar a comanda: ");
                    numeroMesa = scan.nextInt();
                    if(mesinha.fecharComanda(numeroMesa)){
                        gerencia.addComanda(mesinha.pegaComanda(numeroMesa));
                        System.out.println("Comanda fechada!!!");
                    } else {
                        System.out.println("Erro");
                    }
                    break;

                case 4: System.out.println("Digite a ID do pedido");
                    int pedidoID = scan.nextInt();
                    cozinha.atenderPedido(pedidoID);
                    break;

                case 5: System.out.println("Digite o numero da mesa");
                    numeroMesa = scan.nextInt();
                    mesinha.viewPedido(numeroMesa);
                    break;
                case 6: // precisa testar isso aqui

                    System.out.println("informe o ano");
                    int ano = scan.nextInt();
                    System.out.println("Informe o mes:");
                    int mes = scan.nextInt();
                    System.out.println("Informe o dia:");
                    int dia = scan.nextInt();
                    LocalDate inicio = LocalDate.of(ano,mes,dia);
                    System.out.println("inicio:"+inicio);
                    System.out.println("Informe o ano:");
                    ano = scan.nextInt();
                    System.out.println("Informe o mes:");
                    mes = scan.nextInt();
                    System.out.println("Informe o dia:");
                    dia = scan.nextInt();
                    LocalDate fim = LocalDate.of(ano,mes,dia);
                    System.out.println("Fim:"+ fim);
                    System.out.println(gerencia.betweenn(inicio, fim));
                    break;

                case 7:
                    System.out.println("Digite o número da mesa:");
                    numeroMesa = scan.nextInt();
                    System.out.println(mesinha.pegaComanda(numeroMesa).pedidosNaoAtendidos());
                    break;

                case 0: sistemaLigado=false;
                    break;
            }

        }limpaTelaRTA(); // RTA = Recurso Tecnico Alternativo - GAMBIARRA
    }
    public static void limpaTelaRTA(){
        for(int i =0; i<50;i++){
            System.out.println("\n");
        }
    }
}
