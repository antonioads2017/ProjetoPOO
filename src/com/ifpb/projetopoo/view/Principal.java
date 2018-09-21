package com.ifpb.projetopoo.view;

import com.ifpb.projetopoo.dao.ProdutoDao;
import com.ifpb.projetopoo.dao.UsuarioDao;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        UsuarioDao usuarios = null;
        ProdutoDao produtos = null;
        try {
            produtos = new ProdutoDao();
            usuarios = new UsuarioDao();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean inicio = true;
        int selecaoMenu;
        int selecao;
        Scanner scanner = new Scanner(System.in);

        while (inicio){
            System.out.println("|--------------PRINCIPAL--------------|\n\n\n");
            System.out.println("1 - Autenticar Conta");
            System.out.println("2 - Criar Conta");
            selecao = scanner.nextInt();
            if(selecao<0||selecao>2){
                System.out.println("ERRO - Pressione Enter para tentar novamente");
                scanner.nextLine();
            }
            else {
                switch (selecao){
                    case 1:
                        System.out.println("|--------------LOGIN--------------|\n\n");
                        System.out.println("Email:");
                        String email = scanner.next();
                        System.out.println("Senha:");
                        String senha = scanner.next();
                        if(usuarios.autenticarUsuario(email,senha)){
                            System.out.println("Usuario logado");
                            boolean inicio2 = true;
                            while (inicio2){
                                System.out.println("|-------LOGADO-------|");
                                System.out.println("1 - Gerencia\n2 - Atualizar Conta\n3- Deletar Conta\n4- Logout:\n");
                                selecaoMenu=scanner.nextInt();
                                if(selecaoMenu<1||selecaoMenu>4){
                                    System.out.println("ERRO - Pressione Enter para tentar novamente");
                                    scanner.nextLine();
                                }
                                else{
                                    switch (selecaoMenu){
                                        case 1:
                                            gerencia(produtos);
                                            break;

                                        case 2:
                                            System.out.println("-------Atualização deste Usuario-------");
                                            System.out.println("Digite o seu email:");
                                            email = scanner.next();
                                            System.out.println("Digite sua senha");
                                            senha=scanner.next();
                                            if(usuarios.atualizarUsuario(email,senha)){
                                                System.out.println("Usuario Atualizado");
                                            }
                                            else {
                                                System.out.println("ERRO");
                                            }break;

                                        case 3:
                                            System.out.println("Deseja realmente excluir?\n");
                                            System.out.println("1 - SIM\n2 - NÃO");
                                            int escolha = scanner.nextInt();
                                            if(escolha<1||escolha>2){
                                                System.out.println("ERRO - COMANDA INVALIDO");
                                            }
                                            else{
                                                if(escolha==1){
                                                    usuarios.excluirUsuario(email,senha);
                                                    System.out.println("Usuario Excluido com Sucesso");
                                                    inicio2=false;
                                                }
                                            }break;

                                        case 4: inicio2=false; break;
                                    }
                                }
                            }limpaTelaRTA();

                        }else System.out.println("Usuario não existe!");
                        break;
                    case 2:
                        System.out.println("|-------CADASTRO-------|");
                        usuarios.cadastrarUsuario();
                        break;

                    case 0: inicio=false;

                }
            }limpaTelaRTA();
        }

    }
    public static void limpaTelaRTA(){
        for(int i =0; i<20;i++){
            System.out.println("\n");
        }
    }
    public static  void gerencia(ProdutoDao produtos) throws IOException, ClassNotFoundException {
        boolean continua = true;
        int codigo;
        while (continua){
            System.out.println("|----------GERÊNCIA----------|");
            Scanner scanner = new Scanner(System.in);
            System.out.println("1 - Menu de Produtos\n2 - Cadastrar Produtos\n3 - Atualizar Produtos\n4 - Deletar" +
                    "Produtos\n5 - Voltar ao Principal\n");
            int selecao = scanner.nextInt();
            if(selecao<1||selecao>6){
                System.out.println("ERRO - Pressione Enter para tentar novamente");
                scanner.nextLine();
            }
            else{
                switch (selecao){
                    case 1:
                        System.out.println(produtos);
                        break;

                    case 2:
                        produtos.AddProduto();
                        break;

                    case 3:
                        System.out.println("Qual produto deseja atualizar?\n");
                        System.out.println(produtos);
                        codigo=scanner.nextInt();
                        if(produtos.atualizarProduto(codigo)){
                            System.out.println("Produto atualizado com sucesso!");
                        }else System.out.println("ERRO!");
                        break;
                    case 4:
                        System.out.println("Qual produto deseja excluir?\n");
                        System.out.println(produtos);
                        codigo=scanner.nextInt();
                        if(produtos.deletarProduto(codigo)){
                            System.out.println("Produto excluido com sucesso!");
                        }else System.out.println("ERRO!");
                        break;
                    case 5:
                        continua=false;
                        break;
                }
            }

        }
    }
}
