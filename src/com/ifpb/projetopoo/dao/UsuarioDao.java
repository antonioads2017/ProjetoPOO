package com.ifpb.projetopoo.dao;

import com.ifpb.projetopoo.model.Produto;
import com.ifpb.projetopoo.model.Setor;
import com.ifpb.projetopoo.model.Usuario;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class UsuarioDao {

    private File arquivoUsuario;

    public UsuarioDao() throws IOException {
        arquivoUsuario = new File("Usuario");
        if(!arquivoUsuario.exists()){
            arquivoUsuario.createNewFile();
        }
    }

    public Set<Usuario> getUsuarios() throws IOException, ClassNotFoundException {
        if(arquivoUsuario.length()>0){
            try(ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(arquivoUsuario))){
                return (Set<Usuario>) in.readObject();
            }
        }return new HashSet<>();
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

    public boolean cadastrarUsuario() throws IOException, ClassNotFoundException {
        Usuario usuario = lerDadosUsuario();
        Set<Usuario> usuarios = getUsuarios();
        for (Usuario u: usuarios){
            if(Objects.equals(usuario,u)){
                System.out.println("Email ou senha existentes! Tente novamente");
                return false;
            }
        }
        if(usuarios.add(usuario)){
            atualizarArquivo(usuarios);
            return true;
        }return false;
    }

    public boolean autenticarUsuario(String email, String senha) throws IOException, ClassNotFoundException {
        Set<Usuario> usuarios = getUsuarios();
        for(Usuario usuario:usuarios){
            if(usuario.autenticUsuario(email,senha)){
                return true;
            }
        };
        return false;
    }

    public Usuario consultarUsuario(String email, String senha) throws IOException, ClassNotFoundException {
        Set<Usuario> usuarios = getUsuarios();
        for(Usuario usuario:usuarios){
            if(usuario.autenticUsuario(email,senha)){
                System.out.println(usuario);
                return usuario;
            }
        }
        System.out.println("Usuario não existe!");
        return null;

    }

    public boolean atualizarUsuario(String email, String senha) throws IOException, ClassNotFoundException {
        Set<Usuario> usuarios = getUsuarios();
        usuarios.remove(consultarUsuario(email,senha));
        return cadastrarUsuario();
    }

    public boolean excluirUsuario(String emai, String senha) throws IOException, ClassNotFoundException {
        Set<Usuario> usuarios = getUsuarios();
        return usuarios.remove(consultarUsuario(emai, senha));
    }


    private void atualizarArquivo(Set<Usuario> usuarios) throws IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivoUsuario))){
            out.writeObject(usuarios);
        }
    }

    @Override
    public String toString() {
        String s = "|-------------Usuarios-------------|\n";
        Set<Usuario> usuarios = null;
        try {
            usuarios = getUsuarios();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(usuarios.isEmpty()){
            return "Nao existe usuários cadastrados";
        }
        int controle = 0;
        for(Usuario usuario: usuarios){
            controle++;
            s+=controle+":    \n"+usuario.toString();
        }return s;
    }
}
