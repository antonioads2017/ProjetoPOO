package com.ifpb.projetopoo.dao;

import com.ifpb.projetopoo.Exception.CPFInvalidoException;
import com.ifpb.projetopoo.Exception.DataInvalidaException;
import com.ifpb.projetopoo.model.Produto;
import com.ifpb.projetopoo.model.Setor;
import com.ifpb.projetopoo.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class UsuarioDao {

    private File arquivoUsuario;
    private Set<Usuario> usuarios;

    public UsuarioDao() throws IOException, ClassNotFoundException {
        arquivoUsuario = new File("Usuario");
        if(!arquivoUsuario.exists()){
            arquivoUsuario.createNewFile();
        }
        else{
            if(arquivoUsuario.length()>0) {
                try (ObjectInputStream in = new ObjectInputStream(
                        new FileInputStream(arquivoUsuario))) {
                    usuarios = (Set<Usuario>) in.readObject();
                }
            }else usuarios = new HashSet<>();
        }
    }



    public boolean cadastrarUsuario(Usuario usuario) throws IOException, CPFInvalidoException {
        for (Usuario u: usuarios){
            if(Objects.equals(usuario.getCPF(),u.getCPF())){
                throw new CPFInvalidoException("CPF já existe!");
            }else if(Objects.equals(usuario,u)){
                return false;
            }
        }
        if(usuarios.add(usuario)){
            atualizarArquivo(usuarios);
            return true;
        }return false;
    }

    public boolean autenticarUsuario(String email, String senha){
        for(Usuario usuario:usuarios){
            if(usuario.autenticUsuario(email,senha)){
                return true;
            }
        }
        return false;
    }

    public Usuario consultarUsuario(String email, String senha){
        for(Usuario usuario:usuarios){
            if(usuario.autenticUsuario(email,senha)){
                return usuario;
            }
        }
        return null;

    }

    public boolean atualizarUsuario(String email, String senha) throws IOException, CPFInvalidoException {
        return usuarios.remove(consultarUsuario(email,senha));

    }

    public boolean excluirUsuario(String emai, String senha) throws IOException {
        if(usuarios.remove(consultarUsuario(emai, senha))){
            atualizarArquivo(usuarios);
            return true;
        }return false;

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
