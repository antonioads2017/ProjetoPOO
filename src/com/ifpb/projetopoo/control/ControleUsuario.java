package com.ifpb.projetopoo.control;

import com.ifpb.projetopoo.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControleUsuario {

    private List<Usuario> cadastros;

    public ControleUsuario(){
        cadastros=new ArrayList<>();
    }

    public List<Usuario> getCadastros(){
        return cadastros;
    }

    public Usuario lerDadosUsuario(){
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Informe o CPF");
        String CPF = scanner.next();
        System.out.println("Informe seu email");
        String email=scanner.next();
        System.out.println("Informe o seu nome:");
        String nome = scanner.next();
        System.out.println("Informe seu telefone");
        String telefone = scanner.next();
        System.out.println("Informe sua data de nascimento:");
        String data = scanner.next();
        LocalDate nascimento = LocalDate.parse(data,formatter);
        System.out.println("Selecione o seu setor pelo numero: 1 - Atendimento\n2 - Cozinha\n3 - Caixa\n4- Gerencia");
        int setorUser=scanner.nextInt();
        Setor setor = null;
        switch (setorUser) {
                case 1:
                    setor = Setor.ATENDIMENTO;
                    break;

                case 2:
                    setor = Setor.COZINHA;
                    break;

                case 3:
                    setor = Setor.CAIXA;
                    break;

                case 4:
                    setor = Setor.GERENCIA;
                    break;
                default: setor = Setor.ATENDIMENTO;
            }
        System.out.println("Informe sua senha:");
        String senha = scanner.next();
        Usuario newUser = new Usuario(email,senha,CPF,nome,nascimento,setor,telefone);
        return newUser;
    }

    public boolean cadastrarUsuario(){
        Usuario u = lerDadosUsuario();
        cadastros.add(u);
        return true;
    }

    public boolean autenticarUsuario(String email, String senha){
        for(Usuario usuario:cadastros){
            if(usuario.autenticUsuario(email,senha)){
                return true;
            }
        }return false;
    }

    public Usuario consultarUsuario (String email, String senha){
        for(Usuario usuario : cadastros){
            if(usuario.autenticUsuario(email,senha)){
                System.out.println(usuario);
                return usuario;
            }
        }
        System.out.println("Usuario inexistente");
        return null;
    }

    public boolean atualizarUsuario(int indice){
        if(indice>cadastros.size()-1){
            return false;
        }
        else{
            Usuario substituto = lerDadosUsuario();
            cadastros.add(indice,substituto);
            return true;
        }
    }

    public boolean atualizarEsseUsuario(String email, String senha){
        Usuario antigo = consultarUsuario(email,senha);
        return atualizarUsuario(cadastros.indexOf(antigo));
    }

    public boolean excluirUsuario(int indice){
        if(indice>cadastros.size()-1){
            return false;
        }
        else cadastros.remove(indice);
        return true;
    }

    public boolean excluirEsseUsuario(String email, String senha){
        Usuario excluido = consultarUsuario(email,senha);
        return excluirUsuario(cadastros.indexOf(excluido));
    }

    @Override
    public String toString() {
        if (cadastros.isEmpty()){
            return "Cadastros Vazios!";
        }
            String s = "|-------------Usuarios-------------|\n";
            int controle = 0;
            for(Usuario usuario: cadastros){
                controle++;
                s+=controle+":    \n"+usuario.toString();
            }return s;
        }
}
